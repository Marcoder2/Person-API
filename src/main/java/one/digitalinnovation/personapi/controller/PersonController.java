package one.digitalinnovation.personapi.controller;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import one.digitalinnovation.personapi.entidade.Person;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.repository.PersonRepository;
import one.digitalinnovation.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/people")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO){
        return personService.createPerson(person);
    }
    @GetMapping
    public List<PersonDTO> listALL(){
        return personService.listAll();
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable Long id){
        return personService.findById(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable id, @RequestBody PersonDTO personDTO){
        return personService.updateById(id, personDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PersonNotFoundException {
        personService.delete(id);
    }
}
