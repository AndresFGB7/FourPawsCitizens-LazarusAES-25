package edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos;

/**
 * POJO base class, for official users
 */
public class OfficialPOJO {

    private String username;

    private String name;

    /**
     * Constructor Method
     */
    public OfficialPOJO() {

    }

    /**
     * Second constructor method  of OfficialPOJO with params
     * @param username: String
     * @param name: String
     */
    public OfficialPOJO(String username, String name) {
        this.username = username;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}