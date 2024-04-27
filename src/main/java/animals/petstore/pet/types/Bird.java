package animals.petstore.pet.types;

import animals.AnimalType;
import animals.petstore.pet.Pet;
import animals.petstore.pet.attributes.Breed;
import animals.petstore.pet.attributes.Gender;
import animals.petstore.pet.attributes.PetType;
import animals.petstore.pet.attributes.Skin;

import java.math.BigDecimal;

public class Bird extends Pet implements PetImpl {
    // Properties
    private int numberOfLegs;
    private Breed breed;

    /**
     * Constructor
     * @param animalType {@link AnimalType} that defines if it domesibirded or wild bird
     * @param skin The {@link Skin} of the bird
     * @param gender The {@link Gender} of the bird
     * @param breed The type of bird {@link Breed}
     * @param cost The cost of the bird
     * @param petStoreId The pet store id
     */
    public Bird(AnimalType animalType, Skin skin, Gender gender, Breed breed, BigDecimal cost, int petStoreId) {
       super(PetType.BIRD, cost, gender, petStoreId);
       super.skinType = skin;
       super.animalType = animalType;
       this.numberOfLegs = 2;
       this.breed = breed;
    }

    /**
     * Constructor
     * @param animalType {@link AnimalType} that defines if it domesticated or wild dog
     * @param skinType The {@link Skin} of the dog
     * @param gender The {@link Gender} of the dog
     * @param breed The type of dog {@link Breed}
     */
    public Bird(AnimalType animalType, Skin skinType, Gender gender, Breed breed)
    {
        this(animalType, skinType, gender, breed, new BigDecimal(0), 0);
    }

    /**
     * Is the bird allergy friendly determined by skin type
     * @return A message that tells if the bird is hypoallergenic
     */
    public String birdHypoallergenic() {
        return super.petHypoallergenic(this.skinType).replaceAll("pet", "bird");
    }

    /**
     * Depending if the bird is domestic, wild, or neither what can the say
     * @return what birds would speak
     */
    public String speak() {
        String language;
        switch(this.animalType) {
            case DOMESTIC:
                language = "The bird goes tweet.";
                break;
            case WILD:
                language = "The bird goes swigity swoogity.";
                break;
            default:
                language = "The bird goes " + super.getPetType().speak + ".";
        }
        return language;
    }

    public Breed getBreed() {return this.breed;}

    public int getNumberOfLegs() {return numberOfLegs;}

    public void setNumberOfLegs(int numberOfLegs) {this.numberOfLegs = numberOfLegs;}

    public String typeOfPet() {return "The type of pet is " + super.petType + ".";}

    public AnimalType getAnimalType() {return super.animalType;}

    public String toString() {
        return super.toString() +
                "The bird is " + this.animalType + ".\n" +
                "The breed is " + this.breed + ".\n" +
                this.birdHypoallergenic() + "\n" +
                this.speak() + "\n" +
                "Birds have " + this.numberOfLegs + " legs!";
    }
}






































