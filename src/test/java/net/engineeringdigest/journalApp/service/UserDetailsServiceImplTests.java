package net.engineeringdigest.journalApp.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsServiceImplTests {

  @InjectMocks
  private UserDetailServiceImpl userDetailsService;

  @Mock
  private UserRepository userRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);
  }

//  @Test
//  void loadUserByUsernameTest() {
//    when(userRepository.findByUsername(ArgumentMatchers.anyString())).thenReturn(
//        (net.engineeringdigest.journalApp.entity.User) User.builder().username("Anshuman").password("weijfnhn").roles(
//            String.valueOf(new ArrayList<>())).build());
//    UserDetails userDetails = userDetailsService.loadUserByUsername("Anshuman");
//  }

}
