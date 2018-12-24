package mmp.gps.common.util;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapUtil {

    public static Map factories() {
        LinkedHashMap map = new LinkedHashMap();
        map.put("1", "VW(AUDI\\SKODA)");
        map.put("2", "OBD");
        map.put("3", "mazda");
        map.put("4", "ford");
        map.put("5", "honda");
        map.put("6", "toyota");
        map.put("7", "nissan");
        map.put("8", "KIA");
        map.put("9", "Hyundai");
        map.put("10", "GM");
        return map;
    }

    public static Map faultCodeLevels() {
        LinkedHashMap map = new LinkedHashMap();
        map.put("1", "可忽略");
        map.put("2", "一般");
        map.put("3", "严重");
        map.put("4", "非常严重");
        return map;
    }

    public static Map faultCodeModes() {
        LinkedHashMap map = new LinkedHashMap();
        map.put("1", "大众BOSCH CAN");
        map.put("2", "大众BOSCH");
        map.put("3", "OBD");
        map.put("4", "UDS");
        map.put("16", "丰田");
        map.put("17", "日产");
        map.put("21", "本田");
        map.put("22", "现代,起亚");
        map.put("23", "通用-1");
        map.put("24", "通用-2");
        map.put("32", "马自达");
        map.put("33", "福特");
        return map;
    }

    public static Map faultCodeClears() {
        LinkedHashMap map = new LinkedHashMap();
        map.put("1", "直接清除");
        map.put("2", "到4S店专业清除");
        return map;
    }

    public static Map protocolKinds() {
        LinkedHashMap map = new LinkedHashMap();

        try {
            Class c = Class.forName("mmp.gps.common.util.ProtocolKinds");
            Field[] e = c.getDeclaredFields();
            Field[] var3 = e;
            int var4 = e.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                Field field = var3[var5];
                map.put(field.getInt(c) + "", field.getName());
            }
        } catch (ClassNotFoundException var7) {
            var7.printStackTrace();
        } catch (IllegalArgumentException var8) {
            var8.printStackTrace();
        } catch (IllegalAccessException var9) {
            var9.printStackTrace();
        }

        return map;
    }

    public static Map parameterTypes() {
        LinkedHashMap map = new LinkedHashMap();
        map.put("1", "普通");
        map.put("2", "单选");
        map.put("3", "字典");
        map.put("4", "开关");
        map.put("5", "列表");
        map.put("6", "复合");
        return map;
    }
}
