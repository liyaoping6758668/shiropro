package com.ping.md5;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @author liyaoping
 * @version 1.0
 * @date 2021-1-31 21:02
 */
public class MyMd5Realm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        /**
         *
         *  看底层源码可知，
         *  1.如果用户名不正确返回空，自动判断返回相应异常，不会再去校验密码
         *  2.如果用户名正确，封装AuthenticationInfo实现类，返回底层源码会根据该对象校验密码是否正确，如果不正确返回相应异常，否则返回空
         */

        if("lyp".equals(username)){
            //封装数据库用户名和密码
              //md5加密校验
//            SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo("lyp","202cb962ac59075b964b07152d234b70",this.getName());
              //md5+盐
//            SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo("lyp","bbf2dead374654cbb32a917afd236656", ByteSource.Util.bytes("ABC"),this.getName());
              //md5+盐+哈希散列
            SimpleAuthenticationInfo simpleAuthenticationInfo=new SimpleAuthenticationInfo("lyp","7b7bfd63e6f4c082e099dc3c7f3d56c1", ByteSource.Util.bytes("ABC"),this.getName());
            return simpleAuthenticationInfo;

        }
        return null;
    }
}
