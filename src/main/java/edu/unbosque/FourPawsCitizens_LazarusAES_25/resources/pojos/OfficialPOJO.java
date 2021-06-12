package edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos;

/**
 * POJO base class, for official users
 */
public class OfficialPOJO {

    private String password;
    private String email;
    private String username;

    private String name;

    /**
     * Constructor Method
     */
    public OfficialPOJO() {

    }
    /**
     * Second constructor method  of OfficialPOJO with UserApp params
     * @param username: String
     * @param password: String
     * @param email: String
     */
    public OfficialPOJO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     * Third constructor method of OfficialPOJO with all params
     * @param password: String
     * @param email: String
     * @param username: String
     * @param name: String
     */
    public OfficialPOJO(String password, String email, String username, String name) {
        this.password = password;
        this.email = email;
        this.username = username;
        this.name = name;
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