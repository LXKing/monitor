package mmp.gps.gateway.domain;

import com.alibaba.fastjson.JSON;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClient.BoundRequestBuilder;
import com.ning.http.client.Response;
import mmp.gps.common.util.Charsets;
import mmp.gps.common.util.JsonMapper;
import mmp.gps.domain.app.AppRequest;
import mmp.gps.domain.gateway.*;
import mmp.gps.domain.statistics.DeviceOnlineOfflineReport;
import mmp.gps.gateway.codec.kangkaisi.beans.baidu;
import mmp.gps.gateway.config.AppSettings;
import org.apache.log4j.Logger;

public class DataSender {

    private static Logger cnsle = Logger.getLogger("cnsle");
    private static Logger cmdLog = Logger.getLogger("cmd");
    private static DataSender current;
    private AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
    private DataResolveRequest packet;
    private int capacity;
    private String godpUrl;


    public static DataSender getCurrent() {
        if (current == null) {
            init();
        }

        return current;
    }

    private static synchronized void init() {
        if (current == null) {
            current = new DataSender(AppSettings.current().getCache().getMaxDataRows());
        }
    }

    public int getCapacity() {
        return this.capacity;
    }

    private DataSender(int capacity) {
        this.capacity = capacity;
        this.packet = new DataResolveRequest(capacity);
        this.packet.setHost(AppSettings.current().getInstruct().getQueueName());
        this.godpUrl = AppSettings.current().getGodpSettings().getUrl();
    }

    public synchronized void send(RawData raw) {
        String json1 = null;

        try {
            json1 = JsonMapper.writeValueAsString(raw);
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        cmdLog.debug("json数据" + json1);
        this.packet.apped(raw);
        if (this.packet.getData().size() >= this.capacity) {
            try {
                AppRequest e = new AppRequest();
                e.setMethod("gateway.data.resolve");
                e.setParameter(this.packet);
                String json = JsonMapper.writeValueAsString(e);
                this.packet.getData().clear();
                this.send(json, this.godpUrl);
            } catch (Exception var5) {
                cnsle.debug("发送gateway.data.resolve：" + var5.getMessage());
            }
        }

    }

    public void send(String number, int type, String msn, String mcm, String raw) {
        this.send(new RawData(number, type, msn, mcm, raw));
    }

    public void send(String number, int type, String msn, String mcm, byte[] raw) {
        this.send(new RawData(number, type, msn, mcm, raw));
    }

    public void send(InstructStatusRequest status) {
        try {
            AppRequest e = new AppRequest();
            e.setMethod("gateway.instruct.status");
            e.setParameter(status);
            String json = JsonMapper.writeValueAsString(e);
            this.send(json, this.godpUrl);
        } catch (Exception var4) {
            cnsle.debug("发送gateway.instruct.status：" + var4.getMessage());
        }

    }

    public void send(MultimediaSaveRequest multimedia) {
        try {
            AppRequest e = new AppRequest();
            e.setMethod("gateway.multimedia.save");
            e.setParameter(multimedia);
            String json = JsonMapper.writeValueAsString(e);
            this.send(json, this.godpUrl);
        } catch (Exception var4) {
            cnsle.debug("发送gateway.multimedia.save：" + var4.getMessage());
        }

    }

    public void send(MultimediaDataUploadReport report) {
        try {
            AppRequest e = new AppRequest();
            e.setMethod("gateway.multimedia.upload.percent");
            e.setParameter(report);
            String json = JsonMapper.writeValueAsString(e);
            this.send(json, this.godpUrl);
        } catch (Exception var4) {
            cnsle.debug("发送gateway.multimedia.upload：" + var4.getMessage());
        }

    }

    public void send(DeviceOnlineOfflineReport report) {
        try {
            AppRequest e = new AppRequest();
            e.setMethod("gateway.statistics.device.onlineoffline");
            e.setParameter(report);
            String json = JsonMapper.writeValueAsString(e);
            this.send(json, this.godpUrl);
        } catch (Exception var4) {
            cnsle.debug("发送gateway.statistics.device.onlineoffline：" + var4.getMessage());
        }

    }

    private void send(String json, String url) {
        if (this.asyncHttpClient == null) {
            this.asyncHttpClient = new AsyncHttpClient();
        }

        BoundRequestBuilder builder = this.asyncHttpClient.preparePost(url);
        builder.addHeader("accept", "*/*").addHeader("connection", "Keep-Alive").addHeader("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)").addHeader("Content-Type", "application/json");
        builder.setBody(json.getBytes(Charsets.UTF8));

        try {
            Response e = (Response) this.asyncHttpClient.executeRequest(builder.build()).get();
            String result1 = e.getResponseBody("utf-8");
            if (e.getStatusCode() == 200) {
                String result = e.getResponseBody("utf-8");
                cmdLog.debug("发送到godp：成功" + result);
            } else {
                cmdLog.debug("发送到godp：失败");
            }
        } catch (Exception var7) {
            if (this.asyncHttpClient != null) {
                this.asyncHttpClient.close();
                this.asyncHttpClient = null;
            }
        }

    }

    public String sendbaidu(String lat, String lng) {
        try {
            String e = "http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location=" + lat + "," + lng + "&output=json&pois=1&ak=TCaOhNzLUouksG02DErrZ7bHPsVfCj5M";
            return this.sendGet(e);
        } catch (Exception var4) {
            cnsle.debug("发送gateway.statistics.device.onlineoffline：" + var4.getMessage());
            return lng;
        }
    }

    private String sendGet(String url) {
        System.out.println("url" + url);
        if (this.asyncHttpClient == null) {
            this.asyncHttpClient = new AsyncHttpClient();
        }

        BoundRequestBuilder builder = this.asyncHttpClient.preparePost(url);
        builder.addHeader("accept", "*/*").addHeader("connection", "Keep-Alive").addHeader("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)").addHeader("Content-Type", "application/json");

        try {
            Response e = (Response) this.asyncHttpClient.executeRequest(builder.build()).get();
            String result = e.getResponseBody("utf-8");
            if (e.getStatusCode() == 200) {
                result = result.substring(29, result.length() - 1);
                baidu baidu = (baidu) JSON.parseObject(result, baidu.class);
                System.out.println(baidu);
                String str = "位置:" + baidu.getResult().getAddressComponent().getProvince() + "." + baidu.getResult().getAddressComponent().getCity() + "." + baidu.getResult().getAddressComponent().getDistrict();
                cnsle.debug("发送到baidu：成功" + result);
                return str;
            } else {
                System.out.println("发送到baidu：失败");
                cnsle.debug("发送到baidu：失败");
                return "发送到baidu：失败 ";
            }
        } catch (Exception var7) {
            if (this.asyncHttpClient != null) {
                this.asyncHttpClient.close();
                this.asyncHttpClient = null;
            }

            return url;
        }
    }
}
