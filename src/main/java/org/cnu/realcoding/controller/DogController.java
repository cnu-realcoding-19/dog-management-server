package org.cnu.realcoding.controller;

import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.domain.DogUpdateRequestDto;
import org.cnu.realcoding.service.DogManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class DogController {

    @Autowired
    private DogManagementService dogManagementService;

    @PostMapping("/dogs")
    @ResponseStatus(HttpStatus.CREATED)
    public void createDogs(@RequestBody Dog dog){
        dogManagementService.insertDog(dog);
    }

    @GetMapping("/dogs/name/{name}")
    public List<Dog> getDogsByName(@PathVariable String name){
        return dogManagementService.getDogsByName(name);
    }

    @GetMapping("/dogs/ownerName/{ownerName}")
    public List<Dog> getDogsByOwnerName(@PathVariable String ownerName){
        return dogManagementService.getDogsByOwnerName(ownerName);
    }

    @GetMapping("/dogs/ownerPhoneNumber/{ownerPhoneNumber}")
    public List<Dog> getDogsByOwnerPhoneNumber(@PathVariable String ownerPhoneNumber){
        return dogManagementService.getDogsByOwnerPhoneNumber(ownerPhoneNumber);
    }

    @GetMapping("/dogs")
    public Dog getDog(@RequestParam("name") String name, @RequestParam("ownerName") String ownerName, @RequestParam("ownerPhoneNumber") String ownerPhoneNumber){
        return dogManagementService.getDog(name, ownerName, ownerPhoneNumber);
    }

    @PutMapping("/dogs")
    @ResponseStatus(HttpStatus.OK)
    public void updateDog(@RequestBody DogUpdateRequestDto dogUpdateRequestDto) { dogManagementService.updateDog(dogUpdateRequestDto);}

    @PatchMapping("/dogs/kind/{kind}")
    @ResponseStatus(HttpStatus.OK)
    public void updateKind(@RequestBody Dog dog, @PathVariable String kind) { dogManagementService.updateKind(dog, kind);}




}
