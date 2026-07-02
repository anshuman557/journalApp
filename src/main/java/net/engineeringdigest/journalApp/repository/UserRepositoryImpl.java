package net.engineeringdigest.journalApp.repository;

import java.util.List;
import net.engineeringdigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class UserRepositoryImpl {

  @Autowired
  private MongoTemplate mongoTemplate;

  public List<User> getUsersForSA() {
    Query query = new Query();
    String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    query.addCriteria(Criteria.where("email").regex(regex));
    query.addCriteria(Criteria.where("sentimentAnalysis").exists(true));
    query.addCriteria(Criteria.where("roles").in("USER","ADMIN"));

//    query.addCriteria(Criteria.where("email").exists(true));
//    query.addCriteria(Criteria.where("email").ne(null).ne(""));
//    query.addCriteria(Criteria.where("sentimentAnalysis").exists(true));

////    How to Use OR Operator
//    Criteria criteria = new Criteria();
//    query.addCriteria(criteria.orOperator(
//        Criteria.where("email").exists(true),
//        Criteria.where("sentimentAnalysis").exists(true))
//    );
    List<User> users = mongoTemplate.find(query, User.class);
    return users;
  }
}
