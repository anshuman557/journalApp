package net.engineeringdigest.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
public class EmailServiceTests {

  @Autowired
  private EmailService emailService;

  @Test
  void testSendEmail() {
    emailService.sendEmail("anshumansingh2144@gmail.com",
        "Testing Java Mail Sender",
        "Dear Anshuman, Testing this java email when i trigger it from code for learning spring boot.");
  }

}
