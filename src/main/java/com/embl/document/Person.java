package com.embl.document;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@Document
public class Person {

    @Id
    private String id;
    private String first_name;
    private String last_name;
    private String age;
    private String favourite_colour;


    public static Person clonePersonDetails(Person p, Person existingPerson) {
        existingPerson.setFirst_name(p.getFirst_name());
        existingPerson.setLast_name(p.getLast_name());
        existingPerson.setAge(p.getAge());
        existingPerson.setFavourite_colour(p.getFavourite_colour());
        return existingPerson;
    }
}
