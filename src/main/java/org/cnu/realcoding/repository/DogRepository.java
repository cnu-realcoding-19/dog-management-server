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

    public void updateMedicalRecord(Dog dog, String medicalRecord) {
        List<String> medical_records = dog.getMedicalRecords();
        medical_records.add(medicalRecord);
        mongoTemplate.updateFirst(Query.query(Criteria.where("name").is(dog.getName()).and("ownerName").is(dog.getOwnerName()).and("ownerPhoneNumber").is(dog.getOwnerPhoneNumber())),
                Update.update("medicalRecords", medical_records),
                Dog.class
        );
    }
}
