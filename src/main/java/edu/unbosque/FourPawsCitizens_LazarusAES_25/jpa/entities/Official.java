package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities;

import javax.persistence.*;

@Entity
@Table(name = "Official")
/**
 *  Class for Official Users extends UserApp
 */
public class Official  {

    @Id
    @OneToOne(cascade = CascadeType.ALL)
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
