package net.engineeringdigest.journalApp.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import net.engineeringdigest.journalApp.entity.ConfigJournalAppEntity;
import net.engineeringdigest.journalApp.repository.ConfigJournalAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppCache {

  public enum keys{
    WEATHER_API
  }

  @Autowired
  private ConfigJournalAppRepository configJournalAppRepository;

  public Map <String , String> app_Cache;

//  @PostConstruct
//  public void init(){
//    app_Cache = new HashMap<>();
//    List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
//    for(ConfigJournalAppEntity configJournalAppEntity : all){
//      app_Cache.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
//    }
//  }

  @PostConstruct
  public void init() {
    app_Cache = new HashMap<>();

    List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();

    for (ConfigJournalAppEntity configJournalAppEntity : all) {
      app_Cache.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());

      System.out.println(
          configJournalAppEntity.getKey() + " -> " +
              configJournalAppEntity.getValue()
      );
    }

    System.out.println(app_Cache);
  }
}
