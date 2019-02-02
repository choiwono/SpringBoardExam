package my.examples.springjdbc.service;

import my.examples.springjdbc.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class EmailServiceImpl implements EmailService{
    @Autowired
    public JavaMailSender emailSender;

    @Override
    public void sendEmail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("treasureb1220@gmail.com");
        message.setTo(user.getEmail());
        message.setSubject("주제는 무엇이냐");
        message.setText(user.getPasswd());
        emailSender.send(message);
    }
}
