package com.example.demo.department_employee.employee.util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * 发送邮件
 * @author midMoonNight
 *
 */

@Component
public class SendEMail {
	@Autowired
    private JavaMailSender mailSender;

    @Value("${mail.fromMail.address}")
    private String from;
	/**
                     发送thymeleaf模板的简单邮件
     * @param to 接受者
     * @param subject 主题
     * @param content 内容
     */
    public void sendTemplateMail(String to, String subject, String content) {
        MimeMessage message=mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content,true);
            mailSender.send(message);
            System.out.println("html格式邮件发送成功");
        }catch (Exception e){
            System.out.println("html格式邮件发送失败");
        }
    }
}
