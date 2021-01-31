package com.ping.md5;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author liyaoping
 * @version 1.0
 * @date 2021-1-31 22:01
 */
public class TestMd5Salt {

    public static void main(String[] args) {
        //md5加密
        Md5Hash md5Hash=new Md5Hash("123");
        System.out.println(md5Hash);
        //md5+盐
        Md5Hash md5Hash2=new Md5Hash("123","ABC");
        System.out.println(md5Hash2);
        //md5+盐+哈希散列
        Md5Hash md5Hash3=new Md5Hash("123","ABC",1024);
        System.out.println(md5Hash3);
    }

}
