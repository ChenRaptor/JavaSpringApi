package fr.iut.td01.models;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("User")
@TypeAlias("UserData")
public class UserData {
    private String login;
    private String password;
    private boolean isAdmin;

    public UserData(String login, String password, boolean isAdmin){
        this.login=login;
        this.password=password;
        this.isAdmin=isAdmin;
    }

    public String getLogin(){
        return login;
    }

    public String getPassword(){
        return password;
    }

    public boolean getIsAdmin(){
        return isAdmin;
    }
}
