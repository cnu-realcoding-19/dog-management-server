package org.cnu.realcoding.service;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.domain.DogUpdateRequestDto;
import org.cnu.realcoding.exception.DogBadRequestException;
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


    public void updateDog(DogUpdateRequestDto dogUpdateRequestDto){

        Dog dog = dogUpdateRequestDto.getDogToBeUpdated();
        Dog dogUpdate = dogUpdateRequestDto.getDogToUpdate();

        if (dogRepository.findDog(dog.getName(),dog.getOwnerName(),dog.getOwnerPhoneNumber()) == null){
            throw new DogNotFoundException();
        }
        if(dogRepository.findDog(dogUpdate.getName(), dogUpdate.getOwnerName(), dogUpdate.getOwnerPhoneNumber()) != null){
            throw new DogConflictException();
        }
        if(!(dog.getMedicalRecords().equals(dogRepository.findDog(dog.getName(), dog.getOwnerName(), dog.getOwnerPhoneNumber()).getMedicalRecords())))
            throw new DogBadRequestException();


        dogRepository.updateDog(dog, dogUpdate);

    }

    public void updateKind(Dog dog, String kind){
        if (dogRepository.findDog(dog.getName(),dog.getOwnerName(),dog.getOwnerPhoneNumber())== null){
            throw new DogNotFoundException();
        }
        dogRepository.updateKind(dog, kind);
    }


}
