package edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos;
/**
 * POJO base class, for Vet Users
 */
public class VetPOJO extends UserAppPOJO {

   private  String name;
   private  String address;
   private  String neighborhood;
   private Integer vetId;

    public VetPOJO(String username, String password, String email, String role) {
        super(username, password, email, role);
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

    public VetPOJO(String username, String password, String email,String role, Integer vetId, String name, String address, String neighborhood) {
        super(username,password,email,role);
        this.name = name;
        this.address = address;
        this.neighborhood = neighborhood;
        this.vetId = vetId;
    }

    /**
     * Constructor Method
     */


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
