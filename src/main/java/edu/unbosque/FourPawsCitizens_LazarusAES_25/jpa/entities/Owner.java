package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Owner")
@NamedQueries({
        @NamedQuery(name = "Owner.findAll",
                query = "SELECT b FROM Owner b")
})
/**
 *  Class for Owner Users extends UserApp
 */
public class Owner implements Serializable {


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "username")
    private UserApp userApp;

    @Id
    @GeneratedValue
    @Column(name = "person_Id", unique = true)
    private Integer personId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "neighborhood", nullable = false)
    private String neighborhood;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Pet> pets = new ArrayList<>();



    /**
     * Constructor Method
     */
    public Owner() {
    }

    /**
     * This is the second method constructor of Case with params
     * @param name: String
     * @param address: String
     * @param neighborhood: String
     */
    public Owner(String name, String address, String neighborhood) {
        this.personId = personId;
        this.name = name;
        this.address = address;
        this.neighborhood = neighborhood;
    }

    public UserApp getUserApp() {
        return userApp;
    }

    public void setUserApp(UserApp userApp) {
        this.userApp = userApp;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void addPets(Pet pet) {
        pets.add(pet);
        pet.setOwner_id(this);
    }


}
