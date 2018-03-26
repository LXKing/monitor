package com.edata.godp.dao;

import java.util.HashMap;

public class DaoFactory {
    private static HashMap<Object, Object> daoMap = new HashMap<Object, Object>();

    @SuppressWarnings("unchecked")
    public static <T> T getAdo(Class<T> c) {
        Object dao = daoMap.get(c);
        if (dao == null) {
            String className = "com.edata.godp.dao.mysql." + c.getSimpleName().substring(1);
            try {
                dao = (T) Class.forName(className).newInstance();
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
