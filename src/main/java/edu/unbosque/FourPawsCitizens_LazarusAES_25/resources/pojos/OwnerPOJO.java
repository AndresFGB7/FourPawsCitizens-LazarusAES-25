package edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos;
/**
 * POJO base class, for Owners Users
 */
public class OwnerPOJO {

    private Integer personId;

    private String name;

    private String address;

    private String neighborhood;

    private String username;

    public OwnerPOJO() {
    }
    public OwnerPOJO( String name, String address, String neighborhood) {

        this.name = name;
        this.address = address;
        this.neighborhood = neighborhood;
    }
    /**
     * Second constructor method  of OwnerPOJO with params
     * @param personId: Integer
     * @param name: String
     * @param address: String
     * @param neighborhood: String
     */

    public OwnerPOJO(Integer personId, String name, String address, String neighborhood) {
        this.personId = personId;
        this.name = name;
        this.address = address;
        this.neighborhood = neighborhood;
    }

    /**
     * Second constructor method  of OwnerPOJO with params
     * @param username: String
     * @param personId: Integer
     * @param name: String
     * @param address: String
     * @param neighborhood: String
     */
    public OwnerPOJO(String username, Integer personId, String name, String address, String neighborhood) {
        this.username = username;
        this.personId = personId;
        this.name = name;
        this.address = address;
        this.neighborhood = neighborhood;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

}
