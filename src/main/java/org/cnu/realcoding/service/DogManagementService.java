package org.cnu.realcoding.service;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.exception.DogConflictException;
import org.cnu.realcoding.exception.DogNotFoundException;
import org.cnu.realcoding.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DogManagementService {
    @Autowired
    private DogRepository dogRepository;

    public void insertDog(Dog dog) {
        if (dogRepository.findDog(dog.getName(),dog.getOwnerName(),dog.getOwnerPhoneNumber()) == null){
            dogRepository.insertDog(dog);
        }
        throw new DogConflictException();
    }

    public Dog getDogByName(String name) {
        Dog dog = dogRepository.findDog(name);
        if (dog==null){
            throw new DogNotFoundException();
        }
        return dog;
    }
}
