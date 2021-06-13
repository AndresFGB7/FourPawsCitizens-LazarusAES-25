package edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos;

/**
 * POJO base class, for users
 */
public class UserAppPOJO {

    private String username;

    private String password;

    private String email;

    private String role;

    /**
     * Constructor Method
     */
    public UserAppPOJO() {

    }


    /**
     * Second constructor method  of UserAppPOJO with params
     * @param username
     * @param password
     * @param email
     * @param role
     */
    public UserAppPOJO(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
