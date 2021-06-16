package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Official")
@NamedQueries({
        @NamedQuery(name = "Official.findAll",
                query = "SELECT b FROM Official b")
})
/**
 *  Class for Official Users extends UserApp
 */
public class Official implements Serializable {

    @Id
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "username")
    private UserApp userApp;

    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Constructor Method
     */
    public Official() {
    }

    /**
     * Second constructor method  of Official with params
     * @param name: String name
     */
    public Official(String name) {
        this.name = name;
    }

    public UserApp getUserApp() {
        return userApp;
    }

    public void setUserApp(UserApp userApp) {
        this.userApp = userApp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
