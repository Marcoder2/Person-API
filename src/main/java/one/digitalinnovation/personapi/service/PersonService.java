package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.dto.PersonDTO;
import one.digitalinnovation.personapi.entidade.Person;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.stream.Collectors;


@Service
public class PersonService {
    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = Person.Mapper.toModel(personDTO)

        Person savedPerson = personRepository.save(personToSave);
        return MessageReponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();
    }

    public List<PersonDTO> listAll(){
        List<Person> allPeople = personRepository.findAll();
        return allPeolple.stream()
                .map(personMapper::toDTO);
        .collect(Collectors.toList());
    }
    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = personRepository.findById(id)
            .orElseThrow(() -> new PersonNotFoundException(id))
        return personMapper.toDTO(person);
    }
    
    public void delete(Long id) throws PersonNotFoundException{
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }
    
    private Person verifyIfExists(Long id) throws PersonNotFoundException{
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }
    
    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);
        
        Person personToUpdate = personMapper.toModel(personDTO);

        Person UpdatedPerson = Person.Mapper.toModel(personDTO)

        Person savedPerson = personRepository.save(personToUpdate);
        return createdMessageResponse(savedPerson);
    }

    private MessageResponseDTO createdMessageResponse(Person savedPerson) {
        return createMessageResponse(UpdatedPerson.getId()),
    }

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageReponseDTO
                .builder()
                .message(message + id)
                .build();
    }
}
