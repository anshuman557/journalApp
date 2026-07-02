package net.engineeringdigest.journalApp.repository;

import java.util.List;
import net.engineeringdigest.journalApp.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryImplTests {

  @Autowired
  private UserRepositoryImpl userRepositoryImpl;
  @Test
  public void  testSaveNewUser() {
    Assertions.assertNotNull(userRepositoryImpl.getUsersForSA());

  }
}
