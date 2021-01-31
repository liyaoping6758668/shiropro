import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

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
        securityManager.setRealm(new IniRealm("classpath:shiro.ini"));
        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken("lyp","111");
        try {
            subject.login(token);
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
