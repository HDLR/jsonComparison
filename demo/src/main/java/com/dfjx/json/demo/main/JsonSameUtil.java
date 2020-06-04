package com.dfjx.json.demo.main;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class JsonSameUtil {

    public static final Gson gson = new Gson();
    public static final JsonParser parser = new JsonParser();

    public static boolean same(Object a, Object b){
        if(a == null){
            System.out.println("Object内容都为空");
            return b == null;
        }
        return same(gson.toJson(a), gson.toJson(b));
    }

    public static boolean same(String a, String b){
        if(a == null){
            System.out.println("String内容都为空");
            return b == null;
        }
        if(a.equals(b)){
            if(a.indexOf("[") == 0){
                return same(new JsonParser().parse(a).getAsJsonArray(), new JsonParser().parse(b).getAsJsonArray());
            }else if(a.indexOf("{") == 0){
                return same(new JsonParser().parse(a).getAsJsonObject(), new JsonParser().parse(b).getAsJsonObject());
            }
//            System.out.println("String【" + a + "】 与 【" + b + "】 相等");
//            System.out.println("String【" + a + "】");
//            System.out.println("String【" + b + "】");
            return true;
        }
        JsonElement aElement = parser.parse(a);
        JsonElement bElement = parser.parse(b);

        if(gson.toJson(aElement).equals(gson.toJson(bElement))){
            System.out.println("JsonElement【" + a + "】 与 【" + b + "】 相等");
            return true;
        }
        return same(aElement, bElement);
    }

    private static boolean same(JsonElement a, JsonElement b){
        if(a.isJsonObject() && b.isJsonObject()){

//            System.out.println("isJsonObject()");
            return same((JsonObject) a, (JsonObject) b);
        }else if(a.isJsonArray() && b.isJsonArray()){

//            System.out.println("isJsonArray()");
            return same((JsonArray) a, (JsonArray) b);
        }else if(a.isJsonPrimitive() && b.isJsonPrimitive()){

//            System.out.println("isJsonPrimitive()");
            return same((JsonPrimitive) a, (JsonPrimitive) b);
        }else if(a.isJsonNull() && b.isJsonNull()){

//            System.out.println("isJsonNull()");
            return same((JsonNull) a, (JsonNull) b);
        }else{
            printRed("JsonElement类型不相符：" + a + "------------" + b);
            return Boolean.FALSE;
        }
    }

    private static boolean same(JsonObject a, JsonObject b){
        Set<String> aSet = a.keySet();
        Set<String> bSet = b.keySet();
        if(!aSet.equals(bSet)){
            printRed("Set值不相等：" + aSet + "------------" + bSet);

            System.out.println("判断老系统的key，新系统是否都包含");
            for(String key : aSet){
                if(!bSet.contains(key)){
                    printRed("老系统的key【"+ key +"】在新系统中不存在。");
                    return false;
                }
            }
            System.out.println("老系统的key在新系统都存在。");
//            return false;
        }

        for(String aKey : aSet){
            boolean sfgl = true;

            /**
             * 判断是否过滤哪些字段
             */
            if("totalRecords".equals(aKey)){
//                sfgl = false;
            }

            if(sfgl){
                if(!same(a.get(aKey), b.get(aKey))){
                    printRed(aKey + "对应的值不相等：" + a.get(aKey) + "------------" + b.get(aKey));
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean same(JsonArray a, JsonArray b){
        if(a.size() != b.size()){
            printRed("size不相等：" + a.size() + "------------" + b.size());
            return false;
        }
        List<JsonElement> aList = toSortedList(a);
        List<JsonElement> bList = toSortedList(b);
        for(int i=0; i<aList.size(); i++){
            if(!same(aList.get(i), bList.get(i))){
                printRed("aList.get(i)不相等：" + aList.get(i) + "------------" + bList.get(i));
                return false;
            }
        }
        return true;
    }

    private static boolean same(JsonPrimitive a, JsonPrimitive b){
        boolean flag = a.equals(b);
        if(!flag){
            printRed("【"+ a +"】 不相等 【"+ b +"】");
        }
//        System.out.println("JsonPrimitive值比较：" + flag);
        return flag;
    }

    private static boolean same(JsonNull a, JsonNull b) {
        System.out.println("都为JsonNull");
        return true;
    }

    private static List<JsonElement> toSortedList(JsonArray a){
        List<JsonElement> aList = new ArrayList<>();
        a.forEach(aList::add);
        aList.sort(Comparator.comparing(gson::toJson));
        return aList;
    }

    private static void printRed(String msg){
        System.out.println("\033[31;4m" + msg + "\033[0m");
    }
}
