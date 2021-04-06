package org.cnu.realcoding.service;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.exception.DogConflictException;
import org.cnu.realcoding.exception.DogNotFoundException;
import org.cnu.realcoding.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogManagementService {
    @Autowired
    private DogRepository dogRepository;

    public void insertDog(Dog dog) {
        if (dogRepository.findDog(dog.getName(),dog.getOwnerName(),dog.getOwnerPhoneNumber()) == null){
            dogRepository.insertDog(dog);
        }else {
            throw new DogConflictException();
        }
    }

    public List<Dog> getDogsByName(String name) {
        List<Dog> dogs = dogRepository.findDogsByName(name);
        if (dogs.size()==0){
            throw new DogNotFoundException();
        }
        return dogs;
    }

    public List<Dog> getDogsByOwnerName(String ownerName) {
        List<Dog> dogs = dogRepository.findDogsByOwnerName(ownerName);
        if (dogs.size()==0){
            throw new DogNotFoundException();
        }
        return dogs;
    }

    public List<Dog> getDogsByOwnerPhoneNumber(String ownerPhoneNumber) {
        List<Dog> dogs = dogRepository.findDogsByOwnerPhoneNumber(ownerPhoneNumber);
        if (dogs.size()==0){
            throw new DogNotFoundException();
        }
        return dogs;
    }

    public Dog getDog(String name, String ownerName, String ownerPhoneNumber) {
        Dog dog = dogRepository.findDog(name, ownerName, ownerPhoneNumber);
        if (dog==null){
            throw new DogNotFoundException();
        }
        return dog;
    }
}
