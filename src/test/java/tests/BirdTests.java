package tests;

import org.junit.jupiter.api.*;
import animals.AnimalType;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.Skin;
import animals.petstore.pet.types.Bird;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BirdTests {

    private static Bird bird, bird2, bird3;
    @BeforeAll
    public static void createAnimals()
    {
        bird = new Bird(AnimalType.DOMESTIC, Skin.FEATHERS, Gender.MALE, Breed.SPARROW);
        bird2 = new Bird(AnimalType.WILD, Skin.FEATHERS, Gender.UNKNOWN, Breed.UNKNOWN);
        bird3 = new Bird(AnimalType.UNKNOWN, Skin.FEATHERS, Gender.UNKNOWN, Breed.UNKNOWN);

    }


    @Test
    @Order(1)
    @DisplayName("Animal Test Type Tests Domestic")
    public void animalTypeTests()
    {
        assertEquals(AnimalType.DOMESTIC, bird.getAnimalType(), "Animal Type Expected[" + AnimalType.DOMESTIC
                + "] Actual[" + bird.getAnimalType() + "]");
    }

//    @Test
//    @Order(1)
//    @DisplayName("Bird Speak Woof Tests")
//    public void BirdGoesWoffTest()
//    {
//        assertEquals("The Bird goes woof! woof!", bird.speak(), "I was expecting woof! woof!");
//    }

    @Test
    @Order(1)
    @DisplayName("Bird Fur is it Hyperallergetic")
    public void BirdHyperAllergeticTests()
    {
        assertEquals("The bird is not hyperallergetic!", bird.birdHypoallergenic(),
                "The Bird is not hyperallergetic!");
    }

    @Test
    @Order(1)
    @DisplayName("Bird has legs Test")
    public void legTests()
    {
        Assertions.assertNotNull(bird.getNumberOfLegs());
    }

    @Test
    @Order(2)
    @DisplayName("Bird Gender Test Male")
    public void genderTestMale()
    {
        assertEquals(Gender.MALE, bird.getGender(), "Expecting Male Gender!");
    }

    @Test
    @Order(2)
    @DisplayName("Bird Breed Test Sparrow")
    public void genderBirdBreed() {
        assertEquals(Breed.SPARROW, bird.getBreed());
    }

    @Test
    @DisplayName("Set Number of Legs")
    public void setLegs() {
        bird.setNumberOfLegs(1);
        assertEquals(1, bird.getNumberOfLegs());
    }

    @Test
    @DisplayName("Type of Pet String")
    public void typeOfPetString() {
        assertEquals("The type of pet is BIRD.", bird.typeOfPet());
    }

    @Test
    @DisplayName("toString Test")
    public void toStringTest() {
        String testStr = "The type of pet is BIRD!\n" +
                "The BIRD gender is MALE!\n" +
                "The BIRD cost is $0!\n" +
                "The bird is DOMESTIC.\n" +
                "The breed is SPARROW.\n" +
                "The bird is not hyperallergetic!\n" +
                "The bird goes tweet.\n" +
                "Birds have 2 legs!";
        assertEquals(testStr, bird.toString());
    }

    @TestFactory
    @DisplayName("Speak Wild and Default")

    public Stream<DynamicTest> speakTests() {
        List<DynamicTest> tests = Arrays.asList(
                dynamicTest("Wild speak", () -> assertEquals("The bird goes swigity swoogity.", bird2.speak())),
                dynamicTest("Default speak", () -> assertEquals("The bird goes Chirp.", bird3.speak()))
        );
        return tests.stream();
    }
}
