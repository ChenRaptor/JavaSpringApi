package fr.iut.td01.errors;

public class PersonNotFoundException extends ObjectNotFoundException{
    public PersonNotFoundException(String id){
        super("the id " + id + " is not found");
    }
}
