package mmp.gps.monitor.godp;


import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClient.BoundRequestBuilder;
import com.ning.http.client.Response;
import mmp.gps.common.util.DateFormats;
import mmp.gps.domain.app.AppRequest;
import mmp.gps.domain.app.AppResponse;
import mmp.gps.domain.security.*;
import mmp.gps.monitor.configuration.AppConfig;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GodpPortal {
    private static final Logger LOGGER = LoggerFactory.getLogger(GodpPortal.class);
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static GodpPortal godp;
    private static String token;

    static {
        // objectMapper.configure(Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(DateFormats.DateTimeFormat);// 设置自己的格式
    }

    @Autowired
    private AppConfig appConfig;

    public GodpPortal() {
        godp = this;
    }

    public static String getToken() {
        return token;
    }

    private synchronized static void login() {

        if (token != null)
            return;
        LoginRequest request = new LoginRequest();
        request.setAccount(godp.getGodpUser());
        request.setPwd(godp.getGodpPassword());

        try {
            AppResponse response = GodpPortal.go(request, "security.login");
            if (response.getCode() == 0) {
                String json = objectMapper.writeValueAsString(response.getResult());
                LoginResponse login = objectMapper.readValue(json, LoginResponse.class);
                token = login.getToken();
            }

        } catch (Exception e) {
            e.printStackTrace();
            token = null;
        }

    }

    @SuppressWarnings("unchecked")
    public static <T> T execute(Class<T> c, Object request, String serviceMethod) throws RuntimeException {
        if (token == null)
            login();
        AppResponse response = go(request, serviceMethod);
        // LOGGER.warn(response.toString());
        if (response.getCode() != 0)
            throw new RuntimeException(response.getError());

        if (c == AppResponse.class)
            return (T) response;
        try {
            String json = objectMapper.writeValueAsString(response.getResult());
            return objectMapper.readValue(json, c);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static <T> AppResponse go(T parameter, String serviceMethod) throws RuntimeException {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        try {

            // String requestJson = "";
            // if (request != null)
            // requestJson = objectMapper.writeValueAsString(request);

            AppRequest request = new AppRequest();
            request.setMethod(serviceMethod);
            request.setParameter(parameter);
            request.setToken(token);

            String paraJson = objectMapper.writeValueAsString(request);

            BoundRequestBuilder builder = asyncHttpClient.preparePost(godp.getGodpPortal());
            builder.addHeader("accept", "*/*")
                    .addHeader("connection", "Keep-Alive")
                    .addHeader("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)")
                    .addHeader("Content-Type", "application/json")
                    .setBodyEncoding("utf-8");

            builder.setBody(paraJson);

            Response response = asyncHttpClient.executeRequest(builder.build()).get();
            // LOGGER.warn(response.toString());
            if (response.getStatusCode() == 200) {
                String result = response.getResponseBody("utf-8");
                return objectMapper.readValue(result, AppResponse.class);
            }
            throw new Exception("GOP请求失败！");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            asyncHttpClient.close();
        }
    }

    public String getGodpPortal() {
        return appConfig.getGodpPortal();
    }

    public String getGodpUser() {
        return appConfig.getGodpUser();
    }

    public String getGodpPassword() {
        return appConfig.getGodpPassword();
    }
}
