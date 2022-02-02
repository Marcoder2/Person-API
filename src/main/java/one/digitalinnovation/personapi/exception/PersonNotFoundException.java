package one.digitalinnovation.personapi.exception;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends Throwable {
    public PersonNotFoundException(Lond id) {
        super("Person not found with ID" + id);
    }
}
