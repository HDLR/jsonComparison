//package com.dfjx.json.demo.main;
//
//import com.google.gson.*;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.util.List;
//import java.util.Set;
//
//public class JsonTest {
//
//    public static void main(String[] args) {
//
//        String type = args[0];
//        String path1 = args[1];
//        String path2 = args[2];
//
//        System.out.println("start-------------------------------------参数-------------------------------------start");
//        System.out.println(type);
//        System.out.println(path1);
//        System.out.println(path2);
//        System.out.println("end---------------------------------------参数---------------------------------------end");
//
////        String path1 = "C:\\Users\\Lenovo\\Desktop\\数据对比\\数据字段项比对\\数据库测试\\621 重庆网审平台统一办件库_自然人\\getDataJson老系统.txt";
////        String path2 = "C:\\Users\\Lenovo\\Desktop\\数据对比\\数据字段项比对\\数据库测试\\621 重庆网审平台统一办件库_自然人\\getDataJson老系统2.txt";
////        String path2 = "C:\\Users\\Lenovo\\Desktop\\数据对比\\数据字段项比对\\数据库测试\\621 重庆网审平台统一办件库_自然人\\getDataJson新系统.txt";
////        String path1 = "C:\\Users\\Lenovo\\Desktop\\数据对比\\数据字段项比对\\数据库测试\\621 重庆网审平台统一办件库_自然人\\structuredDatal新系统 .txt";
////        String path2 = "C:\\Users\\Lenovo\\Desktop\\数据对比\\数据字段项比对\\数据库测试\\621 重庆网审平台统一办件库_自然人\\structuredData老系统.txt";
//
////        String a = readString(path1);
////        String b = readString(path2);
//
//        DataClass dataClass1 = new DataClass();
//        DataClass dataClass2 = new DataClass();
//
//        if("1".equals(type)){
//            readData1(path1, dataClass1);
//            readData1(path2, dataClass2);
//        }else if("2".equals(type)){
//            readData2(path1, dataClass1);
//            readData2(path2, dataClass2);
//        }
//
//
//        boolean flag = JsonSameUtil.same(dataClass1.getDataJSONArray(), dataClass2.getDataJSONArray());
//        if(flag){
//            System.out.println("\033[32;4m" + "=============================================文件内容相同=============================================" + "\033[0m");
//        }else{
//            System.out.println("\033[31;4m" + "！！！！！！！！！！！！！！！！！！！！！！！！文件内容不相同！！！！！！！！！！！！！！！！！！！！！！！" + "\033[0m");
//        }
//    }
//
//
//    static class DataClass{
//
//        private JsonArray dataJSONArray;
//
//        public JsonArray getDataJSONArray() {
//            return dataJSONArray;
//        }
//
//        public void setDataJSONArray(JsonArray dataJSONArray) {
//            this.dataJSONArray = dataJSONArray;
//        }
//    }
//
//    private static void readData1(String path, DataClass dataClass){
//        String str = readString(path);
//        JsonObject jsonObject = new JsonParser().parse(str).getAsJsonObject();
//        for(String key : jsonObject.keySet()){
//            JsonObject key1JSONObject = jsonObject.get(key).getAsJsonObject();
//            for(String key1 : key1JSONObject.keySet()){
//                JsonObject key1JSONObject2 = new JsonParser().parse(key1JSONObject.get(key1).getAsString()).getAsJsonObject();
//                JsonObject key1JSONObject3 = key1JSONObject2.get("data").getAsJsonObject();
//                dataClass.setDataJSONArray(key1JSONObject3.get("result").getAsJsonArray());
//            }
//        }
//    }
//
//    private static JsonObject toJson(String str){
//        return new JsonParser().parse(str).getAsJsonObject();
//    }
//
//    private static void readData2(String path, DataClass dataClass){
//        String str = readString(path);
//        JsonObject jsonObject = new JsonParser().parse(str).getAsJsonObject();
//        if(path.indexOf("新") > -1){
//            dataClass.setDataJSONArray(new JsonParser().parse(jsonObject.get("data").getAsString()).getAsJsonArray());
//        }else if(path.indexOf("老") > -1){
//            JsonArray jsonArray = jsonObject.get("data").getAsJsonArray();
//            dataClass.setDataJSONArray(jsonArray);
//        }
//    }
//
//    private static String readString (String path) {
//        StringBuffer buffer = new StringBuffer();
//        try {
//            BufferedReader bf= new BufferedReader(new FileReader(path));
//            String s = null;
//            while((s = bf.readLine()) != null){//使用readLine方法，一次读一行
//                buffer.append(s.trim());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return buffer.toString();
//    }
//
//
//}
