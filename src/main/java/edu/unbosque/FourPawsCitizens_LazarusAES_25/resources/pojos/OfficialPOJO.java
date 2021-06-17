package edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos;

/**
 * POJO base class, for official users
 */
public class OfficialPOJO extends UserAppPOJO{
    private String name;


    public OfficialPOJO(String username, String password, String email, String role, String name) {
        super(username, password, email, role);
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}