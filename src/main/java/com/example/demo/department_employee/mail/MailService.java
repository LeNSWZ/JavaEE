package com.example.demo.department_employee.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService implements IMailService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	@Autowired
    private JavaMailSender mailSender;

    @Value("${mail.fromMail.address}")
    private String from;

    /**
     * 发送html格式邮件
     * @param email         接受者(邮件地址)
     * @param account       员工账号
     * @param password      员工密码
     * @param employeeName  员工姓名
     */
	@Override
	public void sendHtmlEmail(String email, String account, String password, String employeeName) {
		//创建一个MINE消息
		MimeMessage message = mailSender.createMimeMessage();
		String content="<html>\n" +
                "<body>\n" +
                "    <h1>你好 "+employeeName+"! 欢迎加入本公司!</h1>\n" +
                "    <h3>这是你的员工账号："+account+" 和密码："+password+"</h3>\n" +
                "</body>\n" +
                "</html>";
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(email);
            helper.setSubject("OA办公系统邮件");
            helper.setText(content, true);

            mailSender.send(message);
            logger.info("html邮件发送成功");
        } catch (MessagingException e) {
            logger.error("发送html邮件时发生异常！", e);
        }
	}
}
