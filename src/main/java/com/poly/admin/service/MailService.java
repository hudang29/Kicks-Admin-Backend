package com.poly.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    public boolean sendPasswordToEmployee(String toEmail, String rawPassword) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("Tài khoản nhân viên của bạn");
            message.setText("Chào bạn,\n\nMật khẩu tạm thời của bạn là: " + rawPassword +
                    "\nVui lòng đăng nhập và đổi mật khẩu ngay.\n\nTrân trọng!");

            mailSender.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
