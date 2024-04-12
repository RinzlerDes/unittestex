package animals.petstore.store;

public class DuplicatePetStoreIdException extends Exception{
    public DuplicatePetStoreIdException(String errorMessage) {
        super(errorMessage);
    }
}
