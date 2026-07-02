package net.engineeringdigest.journalApp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class JournalEntryService {

  @Autowired
  private JournalEntryRepository journalEntryRepository;
  @Autowired
  private UserService userService;

  private static final Logger logger = LoggerFactory.getLogger(JournalEntryService.class);
  @Transactional
  public void saveEntry(JournalEntry journalEntry, String userName){
    try {
      User user =   userService.findUserByUsername(userName);
      journalEntry.setDate(LocalDateTime.now());
      JournalEntry saved = journalEntryRepository.save(journalEntry);
      user.getJournalEntries().add(saved);
//      user.setUsername(null);
      userService.saveUser(user);
    }catch(Exception e){
      logger.info("hahahahahahahaha");
      throw new RuntimeException("An error occurred while saving entry",e);
    }
  }
  public void saveEntry(JournalEntry journalEntry){
    try {
      journalEntryRepository.save(journalEntry);
    }catch(Exception e){
      log.error("Exception",e.getMessage());
    }
  }

  public List<JournalEntry> getAllJournalEntries(){
    return journalEntryRepository.findAll();
  }

  public Optional<JournalEntry> getJournalEntryById(ObjectId id){
    return  journalEntryRepository.findById(id);
  }

  @Transactional
  public boolean deleteJournalEntryById(ObjectId id, String userName) {
    boolean removed = false;
    try {
      User user = userService.findUserByUsername(userName);
       removed = user.getJournalEntries()
          .removeIf(journalEntry -> journalEntry.getId().equals(id));
      if (removed) {
        userService.saveUser(user);
        journalEntryRepository.deleteById(id);
      }
    } catch (Exception e) {
      log.error("Error",e.getMessage());
      throw new RuntimeException("Exception occurred while deleting journal entry",e);
    }
    return removed;
  }
}
