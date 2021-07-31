package ua.finalproject.periodicals.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String email;
    private String login;
    private String password;
    private boolean status;
    private String language;
}
