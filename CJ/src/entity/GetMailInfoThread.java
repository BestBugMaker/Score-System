package entity;

import org.apache.struts2.ServletActionContext;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 用于获取邮箱中邮件信息的线程
 * @author lupf
 *
 */
public class GetMailInfoThread extends Thread
{   private String result="";
    Message message[] = null;
    MailImfo re = null;

    public GetMailInfoThread(Message message[])
    {
        this.message = message;
    }

    public String getResult() {
        return this.result;
    }

    public void setResult(String result) {
        this.result = result;
    }


    @Override
    public void run()
    {
        // TODO Auto-generated method stub
        super.run();
        if (null != message)
        {
            for (int i = 1; i < message.length; i++)
            {
                try
                {
                    re = new MailImfo((MimeMessage) message[i]);


                    re.setDateFormat("yyyy年MM月dd日");
                    result += re.getSubject() + ":";
                    re.getMailContent((Part) message[i]);
                    this.result += re.getBodyText() + "\n\n";
                }

                catch (MessagingException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                catch (Exception e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }
}
