package edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos;

/**
 * POJO base class, for Vet Users
 */
public class VetPOJO {

    private String username;

    private String password;

    private String email;

    private String name;

    private String address;

    private String neighborhood;

    /**
     * Second constructor method  of VetPOJO with params
     * @param username: String
     * @param password: String
     * @param email: String
     */
    public VetPOJO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     * Third Constructor method of VetPOJO with params
     * @param username: String
     * @param password: String
     * @param email: String
     * @param name: String
     * @param address: String
     * @param neighborhood: String
     */
    public VetPOJO(String username, String password, String email, String name, String address, String neighborhood) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.address = address;
        this.neighborhood = neighborhood;
    }

    /**
     * Constructor Method
     */
    public VetPOJO() {

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
