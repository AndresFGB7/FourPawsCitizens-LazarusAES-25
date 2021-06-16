package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Pet")
/**
 *  Class for Pets
 */
public class Pet implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "pet_id", nullable = false)
    private Integer pet_id;

    @Column(name = "microship",unique = true)
    private String microchip;

    @Column(name = "name")
    private String name;

    @Column(name = "species")
    private String species;

    @Column(name = "race")
    private String race;

    @Column(name = "size")
    private String size;

    @Column(name = "sex")
    private String sex;

    @Column(name = "picture")
    private String picture;

    @ManyToOne
    @JoinColumn(name = "person_Id")
    private Owner owner;

    @OneToMany(mappedBy = "pet", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Case> cases = new HashSet<>();

    @OneToMany(mappedBy = "pet_id", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Visit> visits = new HashSet<>();


    /**
     * Constructor Method
     */
    public Pet(){

    }

    /**
     * This is the second method constructor of Pet with params
     * @param pet_id: Integer
     * @param microchip: String
     * @param name: String
     * @param species: String
     * @param race: String
     * @param size: String
     * @param sex: String
     * @param picture: String
     */
    public Pet(Integer pet_id, String microchip, String name, String species, String race, String size, String sex, String picture) {
        this.pet_id = pet_id;
        this.microchip = microchip;
        this.name = name;
        this.species = species;
        this.race = race;
        this.size = size;
        this.sex = sex;
        this.picture = picture;
    }
    public Integer getPet_id() {
        return pet_id;
    }

    public void setPet_id(Integer pet_id) {
        this.pet_id = pet_id;
    }

    public String getMicrochip() {
        return microchip;
    }

    public void setMicrochip(String microchip) {
        this.microchip = microchip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Owner getOwner_id() {
        return owner;
    }

    public void setOwner_id(Owner owner_id) {
        this.owner = owner_id;
    }

    public Set<Case> getCases() {
        return cases;
    }

    public void addCases(Case aCase) {
        cases.add(aCase);
        aCase.setPet_id(this);

    }

    public void setCases(Set<Case> cases) {
        this.cases = cases;
    }

    public Set<Visit> getVisits() {

        return visits;
    }

    public void addVisits(Visit visit) {
        visits.add(visit);
        visit.setPet_id(this);
    }

}
