package mmp.gps.gateway.domain;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClient.BoundRequestBuilder;
import com.ning.http.client.Response;
import mmp.gps.common.util.JsonMapper;
import mmp.gps.domain.app.AppRequest;
import mmp.gps.gateway.config.AppSettings;


public class GodpPortal {

    public static Object send(Class c, Object parameter, String method) {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

        try {
            AppRequest ex = new AppRequest();
            ex.setParameter(parameter);
            ex.setMethod(method);
            String paraJson = JsonMapper.writeValueAsString(ex);
            BoundRequestBuilder builder = asyncHttpClient.preparePost(AppSettings.current().getGodpSettings().getUrl());
            builder.addHeader("accept", "*/*").addHeader("connection", "Keep-Alive").addHeader("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)").addHeader("Content-Type", "application/json").setBodyEncoding("utf-8");
            builder.setBody(paraJson);
            Response response = (Response) asyncHttpClient.executeRequest(builder.build()).get();
            if (response.getStatusCode() != 200) {
                return null;
            }

            String result = response.getResponseBody("utf-8");
            Object var10 = JsonMapper.toObject(result, c);
            return var10;
        } catch (Exception var13) {
            var13.printStackTrace();
        } finally {
            asyncHttpClient.close();
        }

        return null;
    }
}
