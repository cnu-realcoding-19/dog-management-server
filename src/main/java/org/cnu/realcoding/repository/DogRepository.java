package org.cnu.realcoding.repository;

import org.cnu.realcoding.domain.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DogRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Dog findDog(String name, String ownerName, String ownerPhoneNumber){
        return mongoTemplate.findOne(Query.query(Criteria.where("name").is(name).and("ownerName").is(ownerName).and("ownerPhoneNumber").is(ownerPhoneNumber)), Dog.class);
    }

    public List<Dog> findDogsByName(String name){
        return mongoTemplate.find(Query.query(Criteria.where("name").is(name)), Dog.class);
    }

    public List<Dog> findDogsByOwnerName(String ownerName){
        return mongoTemplate.find(Query.query(Criteria.where("ownerName").is(ownerName)), Dog.class);
    }

    public List<Dog> findDogsByOwnerPhoneNumber(String ownerPhoneNumber){
        return mongoTemplate.find(Query.query(Criteria.where("ownerPhoneNumber").is(ownerPhoneNumber)), Dog.class);
    }

    public void insertDog(Dog dog) {
        mongoTemplate.insert(dog);
    }

    public void updateDog(Dog dog, Dog dogUpdate){

        Query query = new Query(Criteria.where("name").is(dog.getName()).and("ownerName").is(dog.getOwnerName()).and("ownerPhoneNumber").is(dog.getOwnerPhoneNumber()));
        Update update = new Update();
        update.set("name", dogUpdate.getName());
        update.set("ownerName", dogUpdate.getOwnerName());
        update.set("ownerPhoneNumber", dogUpdate.getOwnerPhoneNumber());
        update.set("kind", dogUpdate.getKind());
        List<String> medical_records = dog.getMedicalRecords();
        medical_records.addAll(dogUpdate.getMedicalRecords());
        update.set("medicalRecords", medical_records);
        mongoTemplate.updateFirst(query, update, Dog.class);
    }

    public void updateKind(Dog dog, String kind){
        mongoTemplate.updateFirst(Query.query(Criteria.where("name").is(dog.getName()).and("ownerName").is(dog.getOwnerName()).and("ownerPhoneNumber").is(dog.getOwnerPhoneNumber())),
                Update.update("kind", kind),
                Dog.class
        );
    }

}
