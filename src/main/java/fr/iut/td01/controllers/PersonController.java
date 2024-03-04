package fr.iut.td01.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.iut.td01.errors.PersonsNotFoundException;
import fr.iut.td01.models.Person;
import fr.iut.td01.models.PersonData;
import fr.iut.td01.services.FileService;
import fr.iut.td01.services.PersonService;

import java.io.IOException;

import org.bson.types.ObjectId;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class PersonController {
    private PersonService service;
    private FileService fileService;
    public PersonController(PersonService service){
        this.service = service;
        this.fileService = new FileService("C:\\Users\\antoi\\OneDrive\\Bureau\\github\\java\\mmi-dev-avance\\td01\\src\\main\\resources\\images\\");
    }

    @PostMapping("/person/add")
    public PersonData postMethodName(@RequestBody Person person) {
        PersonData p = service.add(person.getName(), person.getAge(), person.getPhoto());
        return p;
    }

    // Lister toutes les personnes de la base
    @GetMapping("/person/list")
    public PersonData[] list() {
        return service.list();
    }

    // Lister une personne à partir de son ID (à partir de sa « trace » chaine)
    @GetMapping("/person/get/{trace}")
    public PersonData get(@PathVariable ObjectId trace) {
        return service.get(trace);
    }

    // Supprimer une personne à partir de son ID
    @DeleteMapping("/person/delete/{trace}")
    public void delete(@PathVariable ObjectId trace) {
        service.delete(trace);
    }

    // Vider la base (supprimer toutes les personnes)
    @DeleteMapping("/person/erase")
    public void erase() {
        service.erase();
    }

    // Rechercher une personne à partir de son nom
    @GetMapping("/person/findByName")
    public PersonData[] findByName(@RequestParam(value = "name") String name) {
        PersonData[] result = service.findByName(name);
        if (result.length == 0) {
            throw new PersonsNotFoundException();
        }
        return result;
    }

    // Rechercher une personne qui on un age entre min et max
    @GetMapping("/person/findByAgeBetween")
    public PersonData[] findByAgeBetween(@RequestParam(value = "min") int min, @RequestParam(value = "max") int max) {
        return service.findByAgeBetween(min, max);
    }

    // Incrémenter l’âge de toutes les personnes de la base
    @PatchMapping("/person/updateAgeAll")
    public void updateAgeAll() {
        service.updateAgeAll();
    }

    // Get la photo d'une personne
    @GetMapping(value="/person/{trace}/photo", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getPhoto(@PathVariable ObjectId trace) throws IOException {
        String filename = service.get(trace).getPhoto();
        return fileService.readFromFilename(filename);
    }
}