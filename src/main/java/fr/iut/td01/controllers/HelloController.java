package fr.iut.td01.controllers;

import java.io.IOException;
import java.util.ArrayList;

import java.io.FileNotFoundException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.iut.td01.models.Person;
import fr.iut.td01.services.FileService;

@RestController
class HelloController
{

FileService fileService = new FileService("C:\\Users\\antoi\\OneDrive\\Bureau\\github\\java\\mmi-dev-avance\\td01\\src\\main\\resources\\images\\");

@GetMapping("/hello")
    public String helloWorld() {
        return "Hello, world !";
    }

@GetMapping("/hi")
    public String hi(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hi %s!", name);
    }

@GetMapping("/whois")
    public Person whois() {
    return new Person("toto", 15, "shrek.jpg");
    }

@PostMapping("/IAm")
    public String getMethodName(@RequestBody Person person) {
        return "You are "+ person.getName() +" and you’re "+ person.getAge() +" years old";
    }

@GetMapping(value="/image", produces = MediaType.IMAGE_JPEG_VALUE)
public byte[] getImageWithMediaType() throws IOException {
    return fileService.readFromPath();
}

@GetMapping("/get_list_img")
    public ArrayList<String> getListImg() {
        return fileService.listFiles();
    }

@GetMapping(value="/get_img", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImg(@RequestParam(value = "file_name") String file_name) throws FileNotFoundException, IOException {
        return fileService.readFromFilename(file_name);
    }





}