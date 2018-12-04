package mmp.gps.logic.push;

import mmp.gps.common.util.Charsets;
import mmp.gps.common.util.JsonMapper;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
import com.ning.http.client.AsyncHttpClient.BoundRequestBuilder;

import java.io.IOException;

public class Pusher {

    private AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
    private String url;
    private String userId;
    private RealtimeDataBlock realtimeDataBlock = new RealtimeDataBlock();


    public Pusher(String url, String userId) {
        this.url = url;
        this.userId = userId;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public RealtimeDataBlock getBlock() {
        return this.realtimeDataBlock;
    }

    public synchronized void pushRealtimeDataBlock() {
        try {
            if (this.asyncHttpClient == null) {
                this.asyncHttpClient = new AsyncHttpClient();
            }

            PushDataBlock e = new PushDataBlock();
            e.setKind("device.realtime.data");
            e.setData(this.realtimeDataBlock);
            String json = JsonMapper.toJson(e);
            this.realtimeDataBlock = new RealtimeDataBlock();
            BoundRequestBuilder builder = this.asyncHttpClient.preparePost(this.url);
            builder.addHeader("accept", "*/*").addHeader("connection", "Keep-Alive").addHeader("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)").addHeader("Content-Type", "application/json");
            builder.setBody(json.getBytes(Charsets.UTF8));
            Response response = (Response) this.asyncHttpClient.executeRequest(builder.build()).get();
            String result1 = response.getResponseBody("utf-8");
            if (response.getStatusCode() == 200) {
                String var6 = response.getResponseBody("utf-8");
            }
        } catch (Exception var7) {
            if (this.asyncHttpClient != null) {
                this.asyncHttpClient.close();
                this.asyncHttpClient = null;
            }

            var7.printStackTrace();
        }

    }

    public synchronized void push(String kind, Object msg) {
        try {
            if (this.asyncHttpClient == null) {
                this.asyncHttpClient = new AsyncHttpClient();
            }

            PushDataBlock e = new PushDataBlock();
            e.setKind(kind);
            e.setData(msg);
            String json = JsonMapper.toJson(e);
            BoundRequestBuilder builder = this.asyncHttpClient.preparePost(this.url);
            builder.addHeader("accept", "*/*").addHeader("connection", "Keep-Alive").addHeader("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)").addHeader("Content-Type", "application/json");
            builder.setBody(json.getBytes(Charsets.UTF8));
            this.asyncHttpClient.executeRequest(builder.build());
        } catch (IOException var6) {
            if (this.asyncHttpClient != null) {
                this.asyncHttpClient.close();
                this.asyncHttpClient = null;
            }

            var6.printStackTrace();
        }

    }
}
