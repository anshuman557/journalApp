package net.engineeringdigest.journalApp.entity;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import net.engineeringdigest.journalApp.enums.Sentiment;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "journal_entries")
@Data
@NoArgsConstructor
public class JournalEntry {

  @Id
  private ObjectId id;
  @NonNull
  private String title;
  private String content;
  private LocalDateTime date;
  private Sentiment sentiment;
//
//  public ObjectId getId() {
//    return id;
//  }
//
//  public void setId(ObjectId id) {
//    this.id = id;
//  }
//
//  public String getTitle() {
//    return title;
//  }
//
//  public void setTitle(String title) {
//    this.title = title;
//  }
//
//  public String getContent() {
//    return content;
//  }
//
//  public void setContent(String content) {
//    this.content = content;
//  }
//
//
//  public LocalDateTime getDate() {
//    return date;
//  }
//
//  public void setDate(LocalDateTime date) {
//    this.date = date;
//  }

}
