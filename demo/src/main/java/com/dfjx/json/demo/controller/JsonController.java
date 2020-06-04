package com.dfjx.json.demo.controller;

import com.dfjx.json.demo.main.JsonSameUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.Set;

@RestController
public class JsonController {

    @RequestMapping("/json/comparison")
    public String comparison(String type, String path1, String path2){

        path1 = "/tmp/json/" + path1;
        path2 = "/tmp/json/" + path2;

        System.out.println("");
        System.out.println("start-------------------------------------参数-------------------------------------start");
        System.out.println(type);
        System.out.println(path1);
        System.out.println(path2);
        System.out.println("end---------------------------------------参数---------------------------------------end");
        System.out.println("");

        DataClass dataClass1 = new DataClass();
        DataClass dataClass2 = new DataClass();

        readData1(path1, dataClass1);
        readData1(path2, dataClass2);

        boolean flag = false;
        Set<String> kyes = dataClass1.getJsonObject().keySet();
        if(kyes.contains("data")){
            flag = JsonSameUtil.same(dataClass1.getJsonObject().get("data").getAsJsonArray(), dataClass2.getJsonObject().get("data").getAsJsonArray());
        }else{
            for(String key : dataClass1.getJsonObject().keySet()){
                String json1 = dataClass1.getJsonObject().get(key).getAsJsonObject().get("return").getAsString();
                String json2 = dataClass2.getJsonObject().get(key).getAsJsonObject().get("return").getAsString();
                System.out.println(json2.equals(json1));
                flag = JsonSameUtil.same(new JsonParser().parse(json1).getAsJsonObject(), new JsonParser().parse(json2).getAsJsonObject());
            }
        }

        String returnMessage = "";
        if(flag){
            returnMessage = "=============================================文件内容相同=============================================";
            System.out.println("\033[32;4m" + returnMessage + "\033[0m");
        }else{
            returnMessage = "！！！！！！！！！！！！！！！！！！！！！！！！文件内容不相同！！！！！！！！！！！！！！！！！！！！！！！";
            System.out.println("\033[31;4m" + returnMessage + "\033[0m");
        }

        return returnMessage;
    }

    private static void readData1(String path, DataClass dataClass){
//        String str = readString(path).replaceAll("\\\"", "\"");
        String str = readString(path);
        JsonObject jsonObject = new JsonParser().parse(str).getAsJsonObject();
        dataClass.setJsonObject(jsonObject);
//        for(String key : jsonObject.keySet()){
//            JsonObject key1JSONObject = jsonObject.get(key).getAsJsonObject();
//            for(String key1 : key1JSONObject.keySet()){
//                JsonObject key1JSONObject2 = new JsonParser().parse(key1JSONObject.get(key1).getAsString()).getAsJsonObject();
//                JsonObject key1JSONObject3 = key1JSONObject2.get("data").getAsJsonObject();
//                dataClass.setDataJSONArray(key1JSONObject3.get("result").getAsJsonArray());
//            }
//        }
    }

    private static JsonObject toJson(String str){
        return new JsonParser().parse(str).getAsJsonObject();
    }

    private static void readData2(String path, DataClass dataClass){
        String str = readString(path);
        JsonObject jsonObject = new JsonParser().parse(str).getAsJsonObject();
        dataClass.setJsonObject(jsonObject);
//        if(path.indexOf("新") > -1){
//            dataClass.setDataJSONArray(new JsonParser().parse(jsonObject.get("data").getAsString()).getAsJsonArray());
//        }else if(path.indexOf("老") > -1){
//            JsonArray jsonArray = jsonObject.get("data").getAsJsonArray();
//            dataClass.setDataJSONArray(jsonArray);
//        }
    }

    private static String readString (String path) {
        StringBuffer buffer = new StringBuffer();
        try {
            BufferedReader bf= new BufferedReader(new FileReader(path));
            String s = null;
            while((s = bf.readLine()) != null){//使用readLine方法，一次读一行
                buffer.append(s.trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    static class DataClass{

        private JsonObject jsonObject;
        private JsonArray dataJSONArray;

        public JsonArray getDataJSONArray() {
            return dataJSONArray;
        }

        public void setDataJSONArray(JsonArray dataJSONArray) {
            this.dataJSONArray = dataJSONArray;
        }

        public JsonObject getJsonObject() {
            return jsonObject;
        }

        public void setJsonObject(JsonObject jsonObject) {
            this.jsonObject = jsonObject;
        }
    }

}
