package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Official")
@PrimaryKeyJoinColumn
public class Official extends UserApp {

    @Column(name = "name", nullable = false)
    private String name;

    public Official() {
    }

    public Official(String username, String password, String email, String role, String name) {
        super(username, password, email, "official");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
