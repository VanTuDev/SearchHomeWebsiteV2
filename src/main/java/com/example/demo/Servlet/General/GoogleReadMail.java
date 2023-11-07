package com.example.demo.Servlet.General;

import com.example.demo.Service.PayingService;
import com.example.demo.Service.PostService;
import com.example.demo.Utils.JsonUtils;
import com.example.demo.beans.Paying;
import com.example.demo.dto.ErrorMessage;
import com.example.demo.dto.SuccessMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

@WebServlet("/googleReadMail")

public class GoogleReadMail extends HttpServlet {
    private final PayingService payingService = new PayingService();
    private final PostService postService = new PostService();
    String successMessage = "Đã thanh toán thành công!";
    String errorMessage = "Đã có lỗi truy vấn!";
    private final boolean emailProcessedSuccessfully = false;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("payingId"));
        Optional<Paying> payingIdFromServer = payingService.getPayingById(id);
        System.out.println(payingIdFromServer);
        if (payingIdFromServer.isPresent()) {
            Paying paying = payingIdFromServer.get();
            System.out.println(paying);
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            AtomicBoolean emailProcessedSuccessfully = new AtomicBoolean(false);

            Callable<Boolean> callableTask = new Callable<Boolean>() {

                @Override
                public Boolean call() throws Exception {
                    long startTime = System.currentTimeMillis();
                    long endTime = startTime + (15 * 60 * 1000);
                    while (System.currentTimeMillis() < endTime) {
                        try {
                            Session session = createImapSession();
                            Store store = connectToImapStore(session, "imap.gmail.com", "vantu.dev@gmail.com", "ribt uizq nvpb ixey");
                            Folder inbox = openInboxFolder(store, "INBOX");
                            List<javax.mail.Message> messages = fetchMessages(inbox);
                            for (javax.mail.Message message : messages) {
                                System.out.println("Đang tìm kiếm.......");
                                boolean emailProcessed = processEmailMessage(message, paying);
                                if (emailProcessed) {
                                    System.out.println("Đã tìm thấy ! Vui lòng xem thông báo");
                                    emailProcessedSuccessfully.set(true);
                                    return true;
                                } else {
                                    System.out.println("Đang tìm");
                                }
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    return false;
                }

            };


            // Hẹn giờ cho công việc Callable
            Future<Boolean> future = executorService.submit(callableTask);

            // Tạo Runnable để dừng Callable sau 15 phút
            Runnable stopTask = () -> {
                if (!emailProcessedSuccessfully.get()) {
                    future.cancel(true);
                }
            };

            ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
            scheduledExecutorService.schedule(stopTask, 15, TimeUnit.MINUTES);

            try {
                Boolean emailProcessedResult = future.get();
                if (emailProcessedResult) {
                    payingService.update(paying);
                    postService.updateStatus(payingIdFromServer.get().getPostId());
                    JsonUtils.out(resp, new SuccessMessage(200, successMessage), HttpServletResponse.SC_OK);
                } else {
                    JsonUtils.out(resp, new ErrorMessage(404, errorMessage), HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            } finally {
                executorService.shutdown();
                scheduledExecutorService.shutdown();
            }
        } else {
            System.out.println("Không tìm thấy đơn hàng");
        }
    }


    private boolean processEmailMessage(javax.mail.Message message, Paying paying) throws MessagingException {
        if (message.getSubject() != null && message.getSubject().equalsIgnoreCase("Thông báo thay đổi số dư tài khoản")) {
            return getEmailBody(message).contains(paying.getDescription());
        }
        return false;
    }


    private Session createImapSession() {
        Properties properties = new Properties();
        properties.put("mail.store.protocol", "imaps");
        properties.put("mail.imap.starttls.enable", "true");
        return Session.getInstance(properties);
    }

    private Store connectToImapStore(Session session, String host, String username, String password) throws MessagingException {
        Store store = session.getStore("imaps");
        store.connect(host, username, password);
        return store;
    }

    private Folder openInboxFolder(Store store, String nameDir) throws MessagingException {
        Folder inbox = store.getFolder(nameDir);
        inbox.open(Folder.READ_ONLY);
        return inbox;
    }

    private List<javax.mail.Message> fetchMessages(Folder inbox) throws MessagingException {
        return List.of(inbox.getMessages());
    }


    private String getEmailBody(javax.mail.Message email) {
        try {
            Object content = email.getContent();
            if (content instanceof String) {
                return (String) content;
            } else if (content instanceof MimeMultipart) {
                MimeMultipart mimeMultipart = (MimeMultipart) content;
                return extractTextFromMimeMultipart(mimeMultipart);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Không thể lấy nội dung email.";
    }

    private String extractTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {
        StringBuilder result = new StringBuilder();
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result.append(bodyPart.getContent());
            } else if (bodyPart.isMimeType("text/html")) {
                String htmlContent = (String) bodyPart.getContent();
                result.append(extractTextFromHtml(htmlContent));
            }
        }
        return result.toString();
    }

    private String extractTextFromHtml(String htmlContent) {
        Document doc = Jsoup.parse(htmlContent);
        return doc.text();
    }

}
