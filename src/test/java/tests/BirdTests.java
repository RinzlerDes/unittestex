package tests;

import org.junit.jupiter.api.*;
import animals.AnimalType;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.Skin;
import animals.petstore.pet.types.Bird;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BirdTests {

    private static Bird actualBird;
    @BeforeAll
    public static void createAnimals()
    {
        actualBird = new Bird(AnimalType.DOMESTIC, Skin.FUR, Gender.UNKNOWN, Breed.UNKNOWN);
    }


    @Test
    @Order(1)
    @DisplayName("Animal Test Type Tests Domestic")
    public void animalTypeTests()
    {
        assertEquals(AnimalType.DOMESTIC, actualBird.getAnimalType(), "Animal Type Expected[" + AnimalType.DOMESTIC
                + "] Actual[" + actualBird.getAnimalType() + "]");
    }

    @Test
    @Order(1)
    @DisplayName("Bird Speak Woof Tests")
    public void BirdGoesWoffTest()
    {
        assertEquals("The Bird goes woof! woof!", actualBird.speak(), "I was expecting woof! woof!");
    }

    @Test
    @Order(1)
    @DisplayName("Bird Fur is it Hyperallergetic")
    public void BirdHyperAllergeticTests()
    {
        assertEquals("The Bird is not hyperallergetic!", actualBird.birdHypoallergenic(),
                "The Bird is not hyperallergetic!");
    }

    @Test
    @Order(1)
    @DisplayName("Bird has legs Test")
    public void legTests()
    {
        Assertions.assertNotNull(actualBird.getNumberOfLegs());
    }

    @Test
    @Order(2)
    @DisplayName("Bird Gender Test Male")
    public void genderTestMale()
    {
        actualBird = new Bird(AnimalType.WILD, Skin.UNKNOWN,Gender.MALE, Breed.UNKNOWN);
        assertEquals(Gender.MALE, actualBird.getGender(), "Expecting Male Gender!");
    }

    @Test
    @Order(2)
    @DisplayName("Bird Breed Test Maltese")
    public void genderBirdBreed() {
        actualBird = new Bird(AnimalType.WILD, Skin.UNKNOWN,Gender.FEMALE, Breed.MALTESE);
        assertEquals(Breed.MALTESE, actualBird.getBreed(), "Expecting Breed Maltese!");
    }

    @Test
    @Order(2)
    @DisplayName("Bird Speak Grr Tests")
    public void BirdGoesGrrTest()
    {
        actualBird = new Bird(AnimalType.WILD, Skin.UNKNOWN,Gender.UNKNOWN, Breed.UNKNOWN);
        assertEquals("The Bird goes Grr! Grr!", actualBird.speak(), "I was expecting Grr");
    }

    @Test
    @Order(2)
    @DisplayName("Bird Speak Bark Tests 1")
    public void BirdGoesBarkTest()
    {
        actualBird = new Bird(AnimalType.UNKNOWN, Skin.UNKNOWN,Gender.UNKNOWN, Breed.UNKNOWN);
        assertEquals("The Bird goes Bark! Bark!", actualBird.speak(), "I was expecting Bark");
    }
}
