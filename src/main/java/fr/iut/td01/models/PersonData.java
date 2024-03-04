package fr.iut.td01.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Person")
@TypeAlias("PersonData")
public class PersonData extends Person {
// Attention à l'annotation:
    @Id
    private ObjectId id;

    public String getId(){return this.id.toHexString();}

    public PersonData(String name, int age, String photo)
    {
        super(name,age,photo);
        id = ObjectId.get();
    }
}