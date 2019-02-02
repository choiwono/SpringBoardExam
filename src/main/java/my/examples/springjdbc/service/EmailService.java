package my.examples.springjdbc.service;

import my.examples.springjdbc.dto.User;

public interface EmailService {
    void sendEmail(User user);
}
