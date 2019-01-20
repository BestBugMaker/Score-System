package action;

import com.opensymphony.xwork2.ActionSupport;
import dao.SubmitUpdateGetNameDao;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

public class SubmitUpdateAction extends ActionSupport {
    private String result;
    SubmitUpdateGetNameDao dao = new SubmitUpdateGetNameDao();

    @Override
    public String execute() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession session = request.getSession();
        String teacherno =  (String)session.getAttribute("TeacherNo");
        String name = dao.Sel(teacherno);
        System.out.print(name);
        String info = request.getParameter("info");
        // 配置发送邮件的环境属性
        final Properties props = new Properties();
        /*
         * 可用的属性： mail.store.protocol / mail.transport.protocol / mail.host /
         * mail.user / mail.from
         */
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.qq.com");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.debug", "true");    //遇到最多的坑就是下面这行，不加要报“A secure connection is requiered”错。
        props.put("mail.smtp.starttls.enable", "true");
        // 发件人的账号
        props.put("mail.user", "3371098397@qq.com");
        // 访问SMTP服务时需要提供的密码
        props.put("mail.password", "rfbslcraryxqcihh");
        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(
                props.getProperty("mail.user"));
        message.setFrom(form);
        // 设置收件人
        InternetAddress to = new InternetAddress("100127604@qq.com");
        message.setRecipient(RecipientType.TO, to);
        message.setSubject(name);
        message.setContent(info, "text/html;charset=UTF-8");
        // 发送邮件
        Transport.send(message);
        return SUCCESS;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
