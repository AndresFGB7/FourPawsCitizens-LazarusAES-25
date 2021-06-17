package edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos;

/**
 * POJO base class, for official users
 */
public class OfficialPOJO {
    private String username;

    private String password;

    private String email;

    private String role;

    private String name;


    public OfficialPOJO (){

    }


    public OfficialPOJO(String username, String password, String email, String role, String name) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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