package edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos;
/**
 * POJO base class, for Vet Users
 */
public class VetPOJO {
    private String username;

    private String password;

    private String email;

    private String role;
   private  String name;
   private  String address;
   private  String neighborhood;
   private Integer vetId;

    public VetPOJO() {
    }

    /**
     * Second constructor method  of VetPOJO with params
     * @param username: String
     * @param password: String
     * @param email: String
     * @param name: String
     * @param role: String
     * @param address: String
     * @param neighborhood: String
     */
    public VetPOJO(String username, String password, String email, String role, String name, String address, String neighborhood, Integer vetId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.name = name;
        this.address = address;
        this.neighborhood = neighborhood;
        this.vetId = vetId;
    }
/**
     * Constructor Method
     */

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public Integer getVetId() {
        return vetId;
    }

    public void setVetId(Integer vetId) {
        this.vetId = vetId;
    }
}
