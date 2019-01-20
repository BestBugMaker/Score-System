import entity.GetMailInfoThread;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class MailTest
{

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception
    {
        try
        {
            String host = "pop.qq.com";
            String username = "100127604@qq.com";
            String password = "vfilbnewknaxbhcb";

            Properties p = new Properties();
            p.setProperty("mail.pop3.host", "pop.qq.com"); // 按需要更改
            p.setProperty("mail.pop3.port", "995");
            // SSL安全连接参数
            p.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            p.setProperty("mail.pop3.socketFactory.fallback", "true");
            p.setProperty("mail.pop3.socketFactory.port", "995");

            Session session = Session.getDefaultInstance(p, null);
            Store store = session.getStore("pop3");
            store.connect(host, username, password);

            Folder folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);
            Message message[] = folder.getMessages();
            GetMailInfoThread b = new GetMailInfoThread(message);
            b.start();
            b.join();
            String result = b.getResult();
            System.out.print(result);

        }
        catch (NoSuchProviderException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (MessagingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
