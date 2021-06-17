package edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos;
/**
 * POJO base class, for Owners Users
 */
public class OwnerPOJO {

    private Long personId;

    private String name;

    private String address;

    private String role;

    private String neighborhood;

    private String username;

    private String password;

    private String email;

    public OwnerPOJO() {
    }
    /**
     * Constructor Method
     */
    public OwnerPOJO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     * Second constructor method  of OwnerPOJO with params
     * @param username: String
     * @param password: String
     * @param email: String
     * @param personId: Ling
     * @param name: String
     * @param address: String
     * @param neighborhood: String
     */
    public OwnerPOJO(String username, String password, String email,String role, Long personId, String name, String address, String neighborhood) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
