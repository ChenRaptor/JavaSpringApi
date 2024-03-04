package fr.iut.td01.repositories;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import fr.iut.td01.models.PersonCustomRepository;
import fr.iut.td01.models.PersonData;

@Repository
    public interface PersonRepository extends MongoRepository<PersonData,ObjectId>, PersonCustomRepository {
        @Query("{name:'?0'}")
        List<PersonData> findPersonByName(String name);
        // Faites de même une opération permettant de fournir la liste des personnes dont l’âge est compris entre deux valeurs.
        @Query("{age:{$gte:?0,$lte:?1}}")
        List<PersonData> findPersonByAgeBetween(int min, int max);
    }