package net.engineeringdigest.journalApp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTests {

  @Autowired
  private UserRepository userRepository;

//  @Disabled
//  @Test
//  public void testFindByUsername() {
////    assertEquals(4,2+2);
//    User user = userRepository.findByUsername("Ranveer");
////    assertNotNull(userRepository.findByUsername("Ranveer"));
//    assertTrue(!user.getJournalEntries().isEmpty());
////    assertTrue(5>3);
//  }
//@ParameterizedTest
//  @ValueSource(strings = {
//      "Anshuman",
//      "Ranveer"
//  })
//  public void testFindByUsername(String name) {
//    assertNotNull(userRepository.findByUsername(name));
//  }
//
////  @ParameterizedTest
////  @CsvSource({
////      "1,1,2",
////      "2,10,12",
////      "3,3,9",
//  })
//  public void test(int a , int b , int expected){
//    assertEquals(expected,a+b);
//  }

}
