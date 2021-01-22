package com.example.demo.worker;

import com.malliina.http.OkClient;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OkClientHelper {


    public static String httpMethod(String url) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url).build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }

    public static void main(String[] args) {
        String url = "http://127.0.0.1:8080/index/ok.json";
        try {
            String s = httpMethod(url);
            System.out.println("结果:"+s);
        } catch (IOException e) {
            System.out.println("异常信息:"+e.getMessage());
            e.printStackTrace();
        }
    }
}
