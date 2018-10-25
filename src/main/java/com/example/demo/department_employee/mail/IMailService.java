package com.example.demo.department_employee.mail;

public interface IMailService {
	
    /**
     * 发送html格式邮件
     * @param email         接受者(邮件地址)
     * @param account       员工账号
     * @param password      员工密码
     * @param employeeName  员工姓名
     */
    public void sendHtmlEmail(String email, String account, String password, String employeeName);
}
