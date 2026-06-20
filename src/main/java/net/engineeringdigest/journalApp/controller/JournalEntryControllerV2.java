package net.engineeringdigest.journalApp.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

  @Autowired
  private JournalEntryService journalEntryService;

  @Autowired
  private UserService userService;

  @GetMapping()
  public ResponseEntity<?> getAllJournalEntriesOfUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userName = authentication.getName();
    User user = userService.findUserByUsername(userName);
    List<JournalEntry> allJournalEntries = user.getJournalEntries();
  if(allJournalEntries!= null  && !allJournalEntries.isEmpty()) {
      return new ResponseEntity<>(allJournalEntries,HttpStatus.OK);
    }
   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PostMapping()
  public ResponseEntity createEntry(@RequestBody JournalEntry myEntry) {
    try {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String userName = authentication.getName();
//      User user = userService.findUserByUsername(userName);
      myEntry.setDate(LocalDateTime.now());
      journalEntryService.saveEntry(myEntry,userName);
      return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
    }catch(Exception e) {
      return new ResponseEntity<>(myEntry, HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId id) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userName = authentication.getName();
    User user = userService.findUserByUsername(userName);
    List<JournalEntry> collect = user.getJournalEntries().stream()
        .filter(journalEntry -> journalEntry.getId().equals(id)).collect(Collectors.toList());
    if(!collect.isEmpty()) {
      Optional<JournalEntry> journalEntry = journalEntryService.getJournalEntryById(id);
      if (journalEntry.isPresent()) {
        return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);

  }

  @PutMapping("/id/{id}")
  public ResponseEntity updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userName = authentication.getName();
    User user = userService.findUserByUsername(userName);
    List<JournalEntry> collect = user.getJournalEntries().stream()
        .filter(journalEntry -> journalEntry.getId().equals(id)).collect(Collectors.toList());
    if(!collect.isEmpty()) {
      Optional<JournalEntry> journalEntry = journalEntryService.getJournalEntryById(id);
      if (journalEntry.isPresent()) {
        JournalEntry old = journalEntry.get();
        old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("")?newEntry.getTitle():old.getTitle());
        old.setContent(newEntry.getContent()!= null && !newEntry.getContent().equals("")?newEntry.getContent():old.getContent());
        journalEntryService.saveEntry(old);
        return new ResponseEntity<>(old, HttpStatus.OK);
      }
    }

    JournalEntry old = journalEntryService.getJournalEntryById(id).orElse(null);
    if(old != null) {

    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("id/{id}")
  public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId id ) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String userName = authentication.getName();
    boolean removed = journalEntryService.deleteJournalEntryById(id, userName);
    if(removed) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }


}
