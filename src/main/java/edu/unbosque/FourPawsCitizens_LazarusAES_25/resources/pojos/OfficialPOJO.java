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
     * Second constructor method of OfficialPOJO with all params
     * @param name: String
     */
    public OfficialPOJO(String name) {
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