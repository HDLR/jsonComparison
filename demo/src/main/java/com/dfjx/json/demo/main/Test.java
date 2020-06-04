package com.dfjx.json.demo.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test {

    public static void main(String[] args) {
        httpURLGETCase();
    }

    private static void httpURLGETCase() {
        String methodUrl = "http://109.244.99.159:8280/services/SYZGLJ_DB_3/sqglsjk04/8cce52499233059b5ba7a4afd8f997c5_ca5ab15f892c4be53509cd60a9591d54/getDataJson";
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String line = null;
        try {
            URL url = new URL(methodUrl);
            connection = (HttpURLConnection) url.openConnection();// 根据URL生成HttpURLConnection
            connection.setRequestMethod("GET");// 默认GET请求
            connection.connect();// 建立TCP连接
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));// 发送http请求
                StringBuilder result = new StringBuilder();
                // 循环读取流
                while ((line = reader.readLine()) != null) {
//                    result.append(line).append(System.getProperty("line.separator"));// "\n"
                    result.append(line);// "\n"
                }
                System.out.println(result.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection.disconnect();
        }
    }
}
