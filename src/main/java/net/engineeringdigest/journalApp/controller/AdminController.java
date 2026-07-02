package net.engineeringdigest.journalApp.controller;


import java.util.List;
import java.util.Optional;
import net.engineeringdigest.journalApp.cache.AppCache;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {


  @Autowired
  private UserService userService;

  @Autowired
  private AppCache appCache;

  @GetMapping("/all-users")
  public ResponseEntity<?> getAllUsers() {
    List<User> all = userService.getAll();
    if (all != null && !all.isEmpty()) {
      return new ResponseEntity<>(all, HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PostMapping("/create-admin-user")
  public void createUser(@RequestBody User user) {
    userService.saveAdmin(user);
  }
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable ObjectId id) {

    Optional<User> userById = userService.getUserById(id);

    if (userById.isPresent()) {
      userService.deleteUserById(id);
      return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

    return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
  }

  @GetMapping("clear-app-cache")
  public void clearAppCache() {
    appCache.init();
  }
}