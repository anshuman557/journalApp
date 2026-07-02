package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import net.engineeringdigest.journalApp.service.UserService;
import net.engineeringdigest.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private WeatherService weatherService;
//  @GetMapping
//  public List<User> getAllUsers() {

//    List<User> allUsers = userService.getAllUsers();
//    if(allUsers!= null  && !allUsers.isEmpty()) {
//      return new ResponseEntity<>(allUsers, HttpStatus.OK);
//    }
//    return new ResponseEntity<>(HttpStatus.NOT_FOUND);

//    return userService.getAll();
//  }

//  @PostMapping
//  public ResponseEntity<?> addUser(@RequestBody User user) {
//    try{
//      userService.saveEntry(user);
//      return new ResponseEntity<>(HttpStatus.CREATED);
//    }catch (Exception e) {
//      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
//  }

  @PutMapping()
  public ResponseEntity<?> updateUser(@RequestBody User user) {
    try{
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String userName = authentication.getName();
      User userInDb = userService.findUserByUsername(userName);
        userInDb.setUsername(user.getUsername());
        userInDb.setPassword(user.getPassword());
        userInDb.setEmail(user.getEmail());
        userService.saveNewUser(userInDb);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping()
  public ResponseEntity<?> deleteUserById() {
    try{
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      userRepository.deleteByUsername(authentication.getName());
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping
  public ResponseEntity<?> greeting() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    WeatherResponse weatherResponse = weatherService.getWeather("Patna", "India");
    String greeting="";
    if(weatherResponse!=null) {
      greeting=" Weather feels like "+ weatherResponse.getCurrent().getTempC() + " C";
    }
    return new ResponseEntity<>("Hi " + authentication.getName()+ greeting ,HttpStatus.OK);
  }

}
