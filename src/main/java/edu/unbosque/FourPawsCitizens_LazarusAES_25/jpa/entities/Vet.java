package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "VetPOJO")
@PrimaryKeyJoinColumn
public class Vet extends UserApp{

    @GeneratedValue
    @Column(name = "vet_id", nullable = false)
    private Integer vet_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "neighborhood", nullable = false)
    private String neighborhood;

    @OneToMany(mappedBy = "vet", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Visit> visits = new ArrayList<>();

    public Vet(){

    }

    public Vet(String username, String password, String email,  Integer vet_id, String name, String address, String neighborhood) {
        super(username, password, email, "VetPOJO");
        this.vet_id = vet_id;
        this.name = name;
        this.address = address;
        this.neighborhood = neighborhood;
    }

    public Integer getVet_id() {
        return vet_id;
    }

    public void setVet_id(Integer vet_id) {
        this.vet_id = vet_id;
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

    public List<Visit> getVisits() {
        return visits;
    }

    public void addVisit(Visit visit) {
        visits.add(visit);
        visit.setVet(this);
    }
}
