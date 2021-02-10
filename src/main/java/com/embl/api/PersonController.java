package com.embl.api;

import com.embl.document.Person;
import com.embl.exception.PersonEntityNotFoundException;
import com.embl.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/person/{id}")
    public Person getPerson(@PathVariable String id){
        return personService.retrievePerson(id);
    }

    @GetMapping("/persons")
    public Iterable<Person> getAllPersons(){
        return personService.retrieveAllPersons();
    }

    @PostMapping("/person/add")
    public Person addPerson(@RequestBody Person person){
        return personService.addPerson(person);
    }

    @PostMapping("/persons/add")
    public List<Person> addPersons(@RequestBody List<Person> persons){
        return personService.addPersons(persons);
    }

    @PutMapping("/person/update/{id}")
    public String updatePerson(@PathVariable String id,@RequestBody Person person){
        return personService.updatePerson(id,person);
    }

    @PatchMapping("/person/patch/{id}")
    public String patchPerson(@PathVariable String id,@RequestBody Map<String,String> patch){
        return personService.patchPerson(id,patch);
    }

    @DeleteMapping("/person/delete/{id}")
    public String removePerson(@PathVariable String id){
        return personService.removePerson(id);
    }


}
