package com.example.demo.worker;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

public class HttpClientHelper {

    private static final RequestConfig REQUEST_CONFIG = RequestConfig.custom()
            .setConnectTimeout(500000).setSocketTimeout(500000)
            .setConnectionRequestTimeout(500000).build();

    public static String httpMethod(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(REQUEST_CONFIG);
        HttpResponse response = httpClient.execute(httpGet);
        return EntityUtils.toString(response.getEntity(), "utf-8");
    }

    public static void main(String[] args) {
        String url ="http://127.0.0.1:8080/index/ok.json";
        try {
            String s = httpMethod(url);
            System.out.println("结果:"+s);
        } catch (IOException e) {
            System.out.println("异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }
}
