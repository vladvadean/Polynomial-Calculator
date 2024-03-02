package DataModels;

public class InputException extends Exception {
    public InputException(int i) {
        super("The monomial is wrong at group: " + i);
    }
}
