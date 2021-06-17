package edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos;
/**
 * POJO base class, for Owners Users
 */
public class OwnerPOJO extends UserAppPOJO {

    private Long personId;

    private String name;

    private String address;

    private String role;

    private String neighborhood;


    public OwnerPOJO(String username, String password, String email, String role, Long personId, String name, String address, String neighborhood) {
        super(username, password, email, role);
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

    @Override
    public String getRole() {
        return role;
    }

    @Override
    public void setRole(String role) {
        this.role = role;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }
}
