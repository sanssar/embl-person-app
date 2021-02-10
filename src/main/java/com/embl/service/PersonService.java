package com.embl.service;

import com.embl.document.Person;
import com.embl.exception.PersonApplicationBadRequestException;
import com.embl.exception.PersonEntityNotFoundException;
import com.embl.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class PersonService {

    private PersonRepository personRepository;

    public Person addPerson(Person p){
        return personRepository.save(p);
    }

    public List<Person> addPersons(List<Person> p){
        return personRepository.saveAll(p);
    }

    public Person retrievePerson(String id){
        return personRepository.findById(id).orElseThrow(() -> new PersonEntityNotFoundException("Person Id :: "+id+" not found"));
    }

    public Iterable<Person> retrieveAllPersons(){
        return personRepository.findAll();
    }

    public String updatePerson(String id,Person p){
        try {
            personRepository.findById(id).ifPresentOrElse(existingPerson -> {
                Person.clonePersonDetails(p, existingPerson);
                personRepository.save(existingPerson);
            }, () -> addPerson(p));
            return "Person updated successfully";
        }catch (Exception e){
            throw new PersonApplicationBadRequestException("Person could not be updated due to :: " + e.getMessage());
        }
    }

    public String patchPerson(String id, Map<String,String> p){
        Person existingPerson = personRepository.findById(id).orElseThrow(() -> new PersonEntityNotFoundException("Person Id :: "+id+" not found"));
        p.forEach((k, v) -> {
            if (k.equalsIgnoreCase("first_name"))
                existingPerson.setFirst_name(v);
            else if (k.equalsIgnoreCase("last_name"))
                existingPerson.setLast_name(v);
            else if (k.equalsIgnoreCase("age"))
                existingPerson.setAge(v);
            else if (k.equalsIgnoreCase("favourite_colour"))
                existingPerson.setFavourite_colour(v);
            else {
                log.error("Invalid property " + k + " defined in the request");
                throw new PersonApplicationBadRequestException("Invalid property \"" + k + "\" defined in the request");
            }
        });
        personRepository.save(existingPerson);
        return "Person Id :: "+id+ " successfully patched";
    }

    public String removePerson(String id){
        personRepository.findById(id).orElseThrow(() -> new PersonEntityNotFoundException("Person Id :: "+id+" not found"));
        personRepository.deleteById(id);
        return "Person Id :: "+id+" successfully deleted";
    }


}
