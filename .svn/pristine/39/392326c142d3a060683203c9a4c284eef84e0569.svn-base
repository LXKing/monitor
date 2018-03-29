package com.rayton;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


/**
 * Created by Yanwu 2018/2/8.
 */
public class JsonToSqlTest {
    private static final String PATH = "D:\\authorizes.json";

    public static void main(String[] args) throws Exception {
        System.out.println("========== JSON ---> 转换成 SQL 开始 ==========");
        jsonToExcel();
        System.out.println("========== JSON ---> 转换成 SQL 结束 ==========");
    }

    private static void jsonToExcel() throws Exception {
        JsonParser jsonParser = new JsonParser();
        JsonArray asJsonArray = (JsonArray) jsonParser.parse(new FileReader(PATH));


        for (int i = 0; i < asJsonArray.size(); i++) {
            JsonElement jsonElement = asJsonArray.get(i);

            JsonObject featuresObj = jsonElement.getAsJsonObject();
            JsonElement permissionId = featuresObj.get("id");

            // System.out.println(permissionId);

            JsonElement name = featuresObj.get("name");
            JsonElement pid = featuresObj.get("pid");
            //
            String sqlStr = "insert into permission (permissionId, name, pid) values (" + permissionId + ", " + name + ", " +
                    pid + "); \r\n";
            System.out.println(sqlStr);
            File file = new File("D:\\mmp.sql");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(sqlStr);
            bufferedWriter.close();
        }
    }
}