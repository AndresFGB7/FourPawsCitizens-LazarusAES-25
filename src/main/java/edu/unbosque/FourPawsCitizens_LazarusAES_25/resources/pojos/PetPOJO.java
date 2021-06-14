package edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Case;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Owner;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Visit;

import java.util.List;

/**
 * POJO base class, for Pets (4 Citizens)
 */
public class PetPOJO {

    private Integer pet_id;

    private String microchip;

    private String name;

    private String species;

    private String race;

    private String size;

    private String sex;

    private String picture;

    private String owner_id;
    /**
     * Constructor Method
     */
    public PetPOJO(){

    }

    /**
     * Second constructor method  of PetPOJO with params
     * @param pet_id: Integer
     * @param microchip: String
     * @param name: String
     * @param species: String
     * @param race: String
     * @param size: String
     * @param sex: String
     * @param picture: String
     * @param owner_id: String
     */
    public PetPOJO(Integer pet_id, String microchip, String name, String species, String race, String size, String sex, String picture, String owner_id) {
        this.pet_id = pet_id;
        this.microchip = microchip;
        this.name = name;
        this.species = species;
        this.race = race;
        this.size = size;
        this.sex = sex;
        this.picture = picture;
        this.owner_id = owner_id;
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

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }
}
