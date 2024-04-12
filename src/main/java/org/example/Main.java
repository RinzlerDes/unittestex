package org.example;

import animals.AnimalType;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.Skin;
import animals.petstore.pet.types.Cat;
import animals.petstore.pet.types.Dog;
import animals.petstore.pet.types.Bird;
import animals.petstore.store.DuplicatePetStoreIdException;
import animals.petstore.store.PetStore;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) throws DuplicatePetStoreIdException {
        System.out.println("Hello world!");
        Dog dog = new Dog(AnimalType.DOMESTIC, Skin.HAIR,Gender.MALE, Breed.POODLE);
        System.out.println(dog.toString());
        System.out.println("\n");
        dog = new Dog(AnimalType.WILD, Skin.FUR, Gender.MALE, Breed.GERMAN_SHEPARD);
        System.out.println(dog.toString());

        Cat cat = new Cat(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.BURMESE);
        System.out.println(cat.toString());

        Bird bird = new Bird(AnimalType.WILD, Skin.FEATHERS, Gender.MALE, Breed.CARDINAL, new BigDecimal("1.01") , 1);
        System.out.println(bird.toString());

        PetStore petStore = new PetStore();
        petStore.init();
        petStore.addPetInventoryItem(bird);
        petStore.printInventoryShort();

    }
}