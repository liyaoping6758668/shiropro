package com.ping.md5;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * @author liyaoping
 * @version 1.0
 * @date 2021-1-31 14:45
 */
public class shiroDemo {

    public static void main(String[] args) {


        //官方代码给的已经过时
        /*Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();*/
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
//        securityManager.setRealm(new IniRealm("classpath:shiro.ini"));
        //设置密码匹配规则,看源码
        MyMd5Realm myMd5Realm=new MyMd5Realm();
        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        myMd5Realm.setCredentialsMatcher(hashedCredentialsMatcher);//默认是明文匹配，这里要设置md5密文匹配
        securityManager.setRealm(myMd5Realm);//自定义realm重写认证和授权方法，底层会自动验证调用该方法
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken("lyp","123");
        try {
            subject.login(token);
            System.out.println("登录成功！");
        } catch (UnknownAccountException uae) {
            System.out.println("用户名错误!");
        } catch (IncorrectCredentialsException ice) {
            System.out.println("密码错误！");
        } catch (LockedAccountException lae) {
            System.out.println("The account for username " + token.getPrincipal() + " is locked.  " +
                    "Please contact your administrator to unlock it.");
        }
        // ... catch more exceptions here (maybe custom ones specific to your application?
            catch (AuthenticationException ae) {
                System.out.println("6666");
            //unexpected condition?  error?
        }

    }
}
