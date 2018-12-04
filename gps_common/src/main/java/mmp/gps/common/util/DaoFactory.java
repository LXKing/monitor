package mmp.gps.common.util;

import java.util.HashMap;

public class DaoFactory {
    private static HashMap<Object, Object> daoMap = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static <T> T getAdo(Class<T> c) {
        Object dao = daoMap.get(c);
        if (dao == null) {
            String className = " mmp.gps.logic.dao." + c.getSimpleName().substring(1);
            try {
                dao = Class.forName(className).newInstance();
                daoMap.put(c, dao);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return (T) dao;
    }
}
