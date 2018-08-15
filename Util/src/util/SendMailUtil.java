package util;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.security.Security;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author teng.long@hand-china.com
 * @Name SendMailUtil
 * @Description
 * @Date 2018/8/15
 */
public class SendMailUtil {
    public static Pattern valuePattern= Pattern.compile("#(.*?)#");

    /**
     *
     * @param host        邮箱地址
     * @param port        端口
     * @param userName    用户名
     * @param password    密码
     * @param addresses   对方地址
     * @param subject
     * @param message     发送的消息
     * @throws AddressException
     * @throws MessagingException
     */
    public static void sendEmail(String host, String port, final String userName, final String password, InternetAddress[] addresses,
                                 String subject, String message) throws AddressException, MessagingException {
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "false");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", host);
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);
        session.setDebug(true);
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(userName));
        msg.addRecipients(Message.RecipientType.TO, addresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        // set plain text message
        msg.setContent(message, "text/html;charset=UTF-8");
        // sends the e-mail
        Transport transport = session.getTransport("smtp");
        transport.connect();
        Transport.send(msg);
        transport.close();
    }
    public static void main(String[] args) throws MessagingException {
        InternetAddress internetAddress = new InternetAddress("v-chentq01@vanke.com");
        InternetAddress[] internetAddresses = new InternetAddress[1];
        internetAddresses[0]= internetAddress;
        sendEmail("mail.vanke.com","25","p-ychttest@vanke.com","27juEDZT",internetAddresses,"Test","你好");
    }

    /**
     * 邮件格式转换类把连续#****#替换成map.get("****")
     * 如传入参数为  aaa#a2#sknj#count#  map={"a2":"bbb","count":"12"}
     * 输出为aaabbbsjnj12
     * @param codeMessage
     * @param map
     * @return
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public static String transformationMessage(String codeMessage,Map<String,String> map) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Matcher matcher=valuePattern.matcher(codeMessage);
        while (matcher.find()){
            String group = matcher.group(0);
            String s = group.substring(1, group.length()-1);
            codeMessage = codeMessage.replaceAll(group, map.get(s));
        }
        return codeMessage;
    }

}
