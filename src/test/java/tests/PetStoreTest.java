package tests;

import animals.AnimalType;
import animals.petstore.pet.Pet;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.PetType;
import animals.petstore.pet.attributes.Skin;
import animals.petstore.pet.types.Bird;
import animals.petstore.pet.types.Cat;
import animals.petstore.pet.types.Dog;
import animals.petstore.store.DuplicatePetStoreIdException;
import animals.petstore.store.DuplicatePetStoreRecordException;
import animals.petstore.store.PetNotFoundSaleException;
import animals.petstore.store.PetStore;
import number.Numbers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class PetStoreTest
{
    private static PetStore petStore;
    Pet pet = new Pet(PetType.BIRD, new BigDecimal("0.0"), Gender.MALE);

    @BeforeEach
    public void loadThePetStoreInventory() throws DuplicatePetStoreIdException {
        petStore = new PetStore();
        petStore.init();
        petStore.printInventoryShort();
    }

    @Test
    @DisplayName("Inventory Count Test")
    public void validateInventory()
    {
        assertEquals(6, petStore.getPetsForSale().size(),"Inventory counts are off!");
    }

    @Test
    @DisplayName("Print Inventory Test")
    public void printInventoryTest()
    {
        petStore.printInventory();
    }

    @Test
    @DisplayName("Add HAWK to Pet Store")
    public void hawkAddedTest() throws DuplicatePetStoreIdException {
        int inventorySize = petStore.getPetsForSale().size() + 1;
        Bird hawk = new Bird(AnimalType.DOMESTIC, Skin.FEATHERS, Gender.MALE, Breed.HAWK,
                new BigDecimal("222.00"), 7);

        petStore.printInventoryShort();

        petStore.addPetInventoryItem(hawk);

        petStore.printInventoryShort();

        String errStr = "Inventory size is different than expected.";
        assertEquals(inventorySize, petStore.getPetsForSale().size(), errStr);
    }

    @Test
    @DisplayName("Sale of Cardinal Remove Item Test")
    public void cardinalSoldTest() throws DuplicatePetStoreRecordException, PetNotFoundSaleException {
        int inventorySize = petStore.getPetsForSale().size() - 1;
        Bird cardinal = new Bird(AnimalType.WILD, Skin.FEATHERS, Gender.FEMALE, Breed.CARDINAL,
                new BigDecimal("111.00"),6);

        petStore.printInventoryShort();

        petStore.soldPetItem(cardinal);

        petStore.printInventoryShort();

        String errStr = "Inventory size is different than expected.";
        assertEquals(inventorySize, petStore.getPetsForSale().size(), errStr);
    }

    @Test
    @DisplayName("Sale of Poodle Remove Item Test")
    public void poodleSoldTest() throws DuplicatePetStoreRecordException, PetNotFoundSaleException {
        int inventorySize = petStore.getPetsForSale().size() - 1;
        Dog poodle = new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.POODLE,
                new BigDecimal("650.00"), 1);

        // Validation
        petStore.soldPetItem(poodle);
        assertEquals(inventorySize, petStore.getPetsForSale().size(), "Expected inventory does not match actual");
    }

    @Test
    @DisplayName("Poodle Duplicate Record Exception Test")
    public void poodleDupRecordExceptionTest() throws DuplicatePetStoreIdException {
        petStore.printInventoryShort();
        petStore.addPetInventoryItem(new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.POODLE,
                new BigDecimal("650.00"), 7));
        Dog poodle = new Dog(AnimalType.DOMESTIC, Skin.FUR, Gender.MALE, Breed.POODLE,
                new BigDecimal("650.00"), 7);

        // Validation
        String expectedMessage = "Duplicate Dog record store id [1]";
        // No more duplicate ids in store so this test will fail
        Exception exception = assertThrows(DuplicatePetStoreRecordException.class, () ->{
            petStore.soldPetItem(poodle);});
        assertEquals(expectedMessage, exception.getMessage(), "DuplicateRecordExceptionTest was NOT encountered!");
        petStore.printInventoryShort();

    }

    @Test
    @DisplayName("Bird Duplicate Pet Store Id Test")
    public void birdDuplicateIdExceptionTest() throws DuplicatePetStoreIdException{
        petStore.printInventoryShort();
        Bird hawk = new Bird(AnimalType.DOMESTIC, Skin.FEATHERS, Gender.MALE, Breed.HAWK,
                new BigDecimal("222.00"), 6);

        String expectedMessage = "HAWK id: 6\nCARDINAL id: 6\nCan not have two pets with same id.";
        Exception exception = assertThrows(DuplicatePetStoreIdException.class, () -> {
            petStore.addPetInventoryItem(hawk);
        });
        assertEquals(expectedMessage, exception.getMessage(), "DuplicatePetStoreIdException NOT encountered.");
        petStore.printInventoryShort();
    }

    @Test
    @DisplayName("Cardinal Duplicate Record Exception Test")
    public void cardinalDuplicateRecordExceptionTest() {
        // Print inventory
    }

    @Test
    @DisplayName("Sale of Sphynx Remove Item Test")
    public void sphynxSoldTest() throws DuplicatePetStoreRecordException, PetNotFoundSaleException {
        int inventorySize = petStore.getPetsForSale().size() - 1;

        Cat sphynx = new Cat(AnimalType.DOMESTIC, Skin.UNKNOWN, Gender.FEMALE, Breed.SPHYNX,
                new BigDecimal("100.00"),2);
        Cat removedItem = (Cat) petStore.soldPetItem(sphynx);

        // Validation
        assertEquals(inventorySize, petStore.getPetsForSale().size(), "Expected inventory does not match actual");
        assertEquals(sphynx.getPetStoreId(), removedItem.getPetStoreId(), "The cat items are identical");
    }

    @Test
    @DisplayName("First Pet Constructor")
    public void petConstructor() {
        assertNotNull(pet, "The pet was not created.");
    }

    @TestFactory
    @DisplayName("Pet Set and Get Tests")
    public Stream<DynamicTest> petGetSetTests() {
//        this.addPetInventoryItem(new Bird(AnimalType.WILD, Skin.FEATHERS, Gender.FEMALE, Breed.CARDINAL,
//                new BigDecimal("111.00"), 6));
        Pet bird = petStore.getPetsForSale().get(5);

        bird.setPetStoreId(10);

        //List<DynamicNode> nodes = new ArrayList<>();
        List<DynamicTest> tests = Arrays.asList(
                dynamicTest("Get Pet Gender", () -> assertEquals(Gender.FEMALE, bird.getGender())),
                dynamicTest("Get Breed", () -> assertEquals(Breed.CARDINAL, bird.getBreed())),
                dynamicTest("Get Cost", () -> assertEquals(new BigDecimal("111.00"), bird.getCost())),
                dynamicTest("Set Pet Store Id", () -> assertEquals(10, bird.getPetStoreId()))
        );
        return tests.stream();
    }

    @Test
    @DisplayName("Pet toString With Id 0")
    public void petToStringNoId() {
        String testStr = "The type of pet is BIRD!\n" +
                "The BIRD gender is MALE!\n" +
                "The BIRD cost is $0.0!\n";
        assertEquals(testStr, pet.toString(), "toString doesn't match");
    }

    @Test
    @DisplayName("Pet Not Found Sale Exception")
    public void petNotFoundSaleExceptionTest() throws DuplicatePetStoreIdException {
        petStore.addPetInventoryItem(pet);
        assertThrows(PetNotFoundSaleException.class, () ->
            petStore.soldPetItem(pet));
    }

    /**
     * Limitations to test factory as it does not instantiate before all
     * @return list of {@link DynamicNode} that contains the test results
     * @throws DuplicatePetStoreRecordException if duplicate pet record is found
     * @throws PetNotFoundSaleException if pet is not found
     */
    @TestFactory
    @DisplayName("Sale of Sphynx Remove Item Test2")
    public Stream<DynamicNode> sphynxSoldTest2() throws DuplicatePetStoreRecordException, PetNotFoundSaleException {
        int inventorySize = petStore.getPetsForSale().size() - 1;

        Cat sphynx = new Cat(AnimalType.DOMESTIC, Skin.UNKNOWN, Gender.FEMALE, Breed.SPHYNX,
                new BigDecimal("100.00"),2);
        Cat removedItem = (Cat) petStore.soldPetItem(sphynx);

        // Validation
        List<DynamicNode> nodes = new ArrayList<>();
        List<DynamicTest> dynamicTests = Arrays.asList(
                dynamicTest("Inventory Check Size Test ", () -> assertEquals(inventorySize,
                        petStore.getPetsForSale().size())),
                dynamicTest("The cat objects match ", () -> assertEquals(sphynx.toString(),
                        removedItem.toString()))
                );
        nodes.add(dynamicContainer("Cat Item 2 Test", dynamicTests));//dynamicNode("", dynamicContainers);

        return nodes.stream();
    }

    /**
     * Example of parameterized test
     * @param number to be tested
     */
    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, -10, 128, Integer.MIN_VALUE}) // six numbers
    void isNumberEven(int number)
    {
        assertTrue(Numbers.isEven(number));
    }

}
