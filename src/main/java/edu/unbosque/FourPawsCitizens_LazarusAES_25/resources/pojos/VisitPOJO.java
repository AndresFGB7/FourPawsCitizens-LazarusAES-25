package edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos;


import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Vet;

public class VisitPOJO {

    private Integer visit_id;
    private String created_at;
    private String type;
    private String description;
    private Integer vet_id;

    public Visit(){

    }

    public VisitPOJO(Integer visit, String created_id, String type, String description, Vet vet) {
        this.visit = visit;
        this.created_id = created_id;
        this.type = type;
        this.description = description;
        this.vet = vet;
    }

    public Integer getVisit() {
        return visit;
    }

    public void setVisit(Integer visit) {
        this.visit = visit;
    }

    public String getCreated_id() {
        return created_id;
    }

    public void setCreated_id(String created_id) {
        this.created_id = created_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }
}