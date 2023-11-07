package com.example.demo.Servlet.General;



import com.example.demo.Service.UserService;
import com.example.demo.Utils.GoogleConstants;
import com.example.demo.Utils.Protector;
import com.example.demo.Utils.Validator;
import com.example.demo.beans.User;
import com.example.demo.dto.GoogleUser;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

@WebServlet("/callback")
public class GoogleSign extends HttpServlet {
    private final UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        ///----------------------------------------Process parse access key of google-------------------------------------------------------------///
        HttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();
        GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
                transport, jsonFactory,
                "https://oauth2.googleapis.com/token",
                GoogleConstants.CLIENT_ID,
                GoogleConstants.CLIENT_SECRET,
                code,
                GoogleConstants.REDIRECT_URI)
                .execute();

        String accessToken = tokenResponse.getAccessToken();
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet("https://www.googleapis.com/oauth2/v2/userinfo");
        httpGet.addHeader("Authorization", "Bearer " + accessToken);
        HttpResponse userInfoResponse = httpClient.execute(httpGet);
        HttpEntity entity = userInfoResponse.getEntity();


        String userInfoJsonString = EntityUtils.toString(entity);


        Gson gson = new Gson();
        GoogleUser googleUser = gson.fromJson(userInfoJsonString, GoogleUser.class);
        //--------------Add value into map-----------------------//
        Map<String, String> values = new HashMap<>();
        values.put("userEmail", googleUser.getEmail());
        //--------------Validation -----------------------------//
        Map<String, List<String>> violations = new HashMap<>();
        Optional<User> emailFromServer = userService.getByEmail(googleUser.getEmail());
        System.out.println(emailFromServer);
        violations.put("usernameViolations", Validator.of(values.get("userEmail"))
                .isNotNullAndEmpty()
                .isNotBlankAtBothEnds()
                .isExistent(emailFromServer.isPresent(), "email")
                .toList());





        //--------------End  -----------------------//




        int sumOfViolations = violations.values().stream().mapToInt(List::size).sum();
        String successMessage = "Đã đăng ký thành công!";
        String errorMessage = "Đã có lỗi truy vấn!";
        Random random = new Random();
        if (sumOfViolations == 0 && emailFromServer.isPresent()) {
            User userValue = emailFromServer.get();
            if (Arrays.asList("ADMIN", "EMPLOYEE").contains(userValue.getRole())) {
                request.getSession().setAttribute("currentUser", userValue);
                response.sendRedirect(request.getContextPath() + "/admin");
            }else {
                request.getSession().setAttribute("currentUser", userValue);
                response.sendRedirect(request.getContextPath() + "/homeView");
            }
        }  else if (emailFromServer.isEmpty())  {

            String randomUsername = String.format("%010d", random.nextInt(1000000000));
            String randomPhoneNumber = String.format("%010d", random.nextInt(1000000000));
            String randomCccd = String.format("%010d", random.nextInt(1000000000));
            String randomFullName = String.format("%010d", random.nextInt(1000000000));

            User user = new User(
                    0L,
                    randomUsername,
                    "notFinishYet",
                    randomFullName,
                    0,
                    randomPhoneNumber,
                    googleUser.getEmail(),
                    randomCccd,
                    "CUSTOMER",
                    googleUser.getPicture(),
                    "",
                    "",
                    1
            );
            Protector.of(() -> userService.insert(user))
                    .done(r -> {
                        request.setAttribute("successMessage", successMessage);
                        request.getSession().setAttribute("currentUser", user);
                        try {
                            response.sendRedirect(request.getContextPath() + "/homeView");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .fail(e -> {
                        request.setAttribute("values", values);
                        request.setAttribute("errorMessage", errorMessage);
                    });
        } else {
            request.setAttribute("values", values);
            request.setAttribute("violations", violations);
            request.getRequestDispatcher("/WEB-INF/views/admin/signInAdminView.jsp").forward(request, response);
        }



    }
}
