package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Owner")
@PrimaryKeyJoinColumn
/**
 *  Class for Owner Users extends UserApp
 */
public class Owner extends UserApp {

    @Id
    @Column(name = "username", unique = true)
    private String username;

    @GeneratedValue
    @Column(name = "owner_id", nullable = false, unique = true)
    private Long personId;

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
     * @param username: String
     * @param password: String
     * @param email: String
     * @param personId: Long
     * @param name: String
     * @param address: String
     * @param neighborhood: String
     */
    public Owner(String username, String password, String email, Long personId, String name, String address, String neighborhood) {
        super(username, password, email, "Owner");
        this.personId = personId;
        this.name = name;
        this.address = address;
        this.neighborhood = neighborhood;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
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
