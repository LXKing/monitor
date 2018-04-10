package com.rayton.gps.util;

import com.rayton.gps.dao.baseinfo.user.User;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5 {


    public static String doMD5(User user) {

        String hashAlgorithmName = "MD5";
        ByteSource salt = ByteSource.Util.bytes(user.getAccount());//以账号作为盐值
        int hashIterations = 1;
        Object obj = new SimpleHash(hashAlgorithmName, user.getPassword(), salt, hashIterations);

        return obj.toString();
    }

    public static void main(String args[]) {
        User user = new User();
        user.setAccount("admin");
        user.setPassword("123456");

        User user2 = new User();
        user2.setAccount("rayton02");
        user2.setPassword("888888");

        String admin = MD5.doMD5(user);
        String ray = MD5.doMD5(user2);

        System.out.println(admin);
        System.out.println(ray);
        System.out.println(admin.length());
        System.out.println(ray.length());
    }

}
