package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Official")
@PrimaryKeyJoinColumn
/**
 *  Class for Official Users extends UserAppPOJO
 */
public class Official extends UserApp {

    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Constructor Method
     */
    public Official() {
    }

    /**
     * Second constructor method  of Official with params
     * @param username: String Key  User Name
     * @param password: String pass
     * @param email: String email
     * @param role: String role
     * @param name: String name
     */
    public Official(String username, String password, String email, String role, String name) {
        super(username, password, email, "official");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
