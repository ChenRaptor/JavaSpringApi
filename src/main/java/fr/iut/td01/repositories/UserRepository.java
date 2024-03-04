package fr.iut.td01.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import fr.iut.td01.models.UserData;

@Repository
    public interface UserRepository extends MongoRepository<UserData,String>{

    // Filtrer par la propriété _id
    @Query("{_id:'?0'}")
    UserData findByLogin(String login);

    }
