package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities;

import javax.persistence.*;

@Entity
@Table(name = "Case")
@NamedQueries({
        @NamedQuery(name = "Case.findById",
                query = "SELECT b FROM Case b WHERE b.case_id = :case_id"),
        @NamedQuery(name = "Case.findAll",
                query = "SELECT b FROM Case b")
})

/**
 * Class for pet cases
 */
public class Case {
    @Id
    @GeneratedValue
    @Column(name = "case_id", nullable = false)
    private int case_id;

    @Column(name = "created_at", nullable = false)
    private String created_at;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    /**
     * Constructor without params
     */
    public Case(){

    }
    /**
     * This is the second method constructor of Case with params
     * @param created_at: String created at x date
     * @param type: String Type of case
     * @param description: String
     */
    public Case(String created_at, String type, String description) {
        this.created_at = created_at;
        this.type = type;
        this.description = description;
    }


    public int getCase_id() {
        return case_id;
    }

    public void setCase_id(int case_id) {
        this.case_id = case_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
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

    public Pet getPet_id() {
        return pet;
    }

    public void setPet_id(Pet pet_id) {
        this.pet = pet;
    }
}
