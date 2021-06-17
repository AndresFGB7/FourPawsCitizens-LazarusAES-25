package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities;

import javax.persistence.*;

@Entity
@Table(name = "Official")
@NamedQueries({
        @NamedQuery(name = "Official.findByUsername",
                query = "SELECT b FROM Official b WHERE b.username = :username"),
        @NamedQuery(name = "Official.findAll",
                query = "SELECT b FROM Official b")
})
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
        super(username, password, email, role);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
