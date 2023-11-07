package com.example.demo.Servlet.General;

import com.example.demo.Service.PayingService;
import com.example.demo.Utils.Protector;
import com.example.demo.Utils.RandomStringGenerator;
import com.example.demo.Utils.VietQrConstants;
import com.example.demo.beans.Paying;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@WebServlet(name="vietqr",value = "/vietqr")
public class Vietqr extends HttpServlet {

    private String accountNo = "9021252677297";
    private String accountName = "NGUYEN VAN TU";
    private String acqId = "963388";

//    private String amount123 = "10";
    private String template = "print";
    private final PayingService payingService = new PayingService();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String randomDescription = RandomStringGenerator.generateRandomString();
        String amount = req.getParameter("price");
        System.out.println(amount);
        Paying paying = new Paying(
                1L,
                Protector.of(() -> Long.parseLong(req.getParameter("postId"))).get(0L),
                Protector.of(() -> Integer.parseInt(req.getParameter("userId"))).get(0),
                Double.parseDouble(amount),
                randomDescription,
                LocalDateTime.now(),
                "Chua thanh cong"
        );



       long id = payingService.insert(paying);
        if (req.getParameter("createPaying") != null){
            CloseableHttpClient httpclient = HttpClients.createDefault();
            String apiUrl = "https://api.vietqr.io/v2/generate";
            HttpPost httpPost =  httpPost(apiUrl,
                    VietQrConstants.CLIENT_ID,
                    VietQrConstants.API_KEY);
            JsonObject requestBody = createRequestBody(
                    accountNo,
                    accountName,
                    acqId,
                    randomDescription,
                    amount.substring(0,amount.length()-2),
                    template);
            StringEntity stringEntity = new StringEntity(requestBody.toString());
            httpPost.setEntity(stringEntity);
            HttpResponse response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String responseString = EntityUtils.toString(entity);
                JsonObject jsonResponse = new com.google.gson.JsonParser().parse(responseString).getAsJsonObject();
                if (jsonResponse.has("data") && jsonResponse.getAsJsonObject("data").has("qrDataURL")) {
                    String qrDataURL = jsonResponse.getAsJsonObject("data").get("qrDataURL").getAsString();
                    req.setAttribute("payingId",id);
                    req.setAttribute("userId",paying.getUserId());
                    req.setAttribute("randomDescription",randomDescription);
                    req.setAttribute("qrdataURL",qrDataURL);
                    req.getRequestDispatcher("/WEB-INF/views/client/paymentProcess.jsp").forward(req,resp);
                }
            }
            httpclient.close();
        }

    }

    public static HttpPost httpPost(String uri,String clientId,String apiKey){
        HttpPost httpPost = new HttpPost(uri);
        httpPost.addHeader("x-client-id", clientId);
        httpPost.addHeader("x-api-key", apiKey);
        httpPost.addHeader("Content-Type", "application/json");
        return httpPost;
    }
    public static JsonObject createRequestBody(String accountNo, String accountName, String acqId, String addInfo, String amount, String template) {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("accountNo", accountNo);
        requestBody.addProperty("accountName", accountName);
        requestBody.addProperty("acqId", acqId);
        requestBody.addProperty("addInfo", addInfo);
        requestBody.addProperty("amount", amount);
        requestBody.addProperty("template", template);
        return requestBody;
    }


}
