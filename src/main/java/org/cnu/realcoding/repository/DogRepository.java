package org.cnu.realcoding.repository;

import org.cnu.realcoding.domain.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class DogRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Dog findDog(String name, String ownerName, String ownerPhoneNumber){
        return mongoTemplate.findOne(Query.query(Criteria.where("name").is(name).and("ownerName").is(ownerName).and("ownerPhoneNumber").is(ownerPhoneNumber)), Dog.class);
    }

    public void insertDog(Dog dog) {
        mongoTemplate.insert(dog);
    }
}
