package fr.iut.td01.services;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import fr.iut.td01.models.PersonData;
import fr.iut.td01.repositories.PersonRepository;

@Service
public class PersonService {
    private PersonRepository repository;
    public PersonService(PersonRepository repository){
        this.repository = repository;
    }
    public PersonData add(String name, int age, String photo){
        PersonData pdata = new PersonData(name,age,photo);
        repository.insert(pdata);
        return pdata;
    }

    public PersonData[] list(){
        return repository.findAll().toArray(new PersonData[0]);
    }

    public PersonData get(ObjectId trace){
        return repository.findById(trace).orElse(null);
    }

    public void delete(ObjectId trace){
        repository.deleteById(trace);
    }

    public void erase(){
        repository.deleteAll();
    }

    public PersonData[] findByName(String name){
        return repository.findPersonByName(name).toArray(new PersonData[0]);
    }

    public PersonData[] findByAgeBetween(int min, int max){
        return repository.findPersonByAgeBetween(min, max).toArray(new PersonData[0]);
    }

    public void updateAgeAll(){
        repository.updateAgeAll();
    }
}
