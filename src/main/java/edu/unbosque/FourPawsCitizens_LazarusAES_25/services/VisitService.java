package edu.unbosque.FourPawsCitizens_LazarusAES_25.services;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Pet;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Vet;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Visit;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories.*;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.VisitPOJO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class VisitService {
    private PetRepository petRepository;
    private VetRepository vetRepository;
    private VisitRepository visitRepository;

    /**
     * @param visitPOJO visit's pojo
     * @return a string message
     */
    public String saveVisit(VisitPOJO visitPOJO) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("FootprintsSystemDS");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        petRepository = new PetRepositoryImpl(entityManager);
        vetRepository = new VetRepositoryImpl(entityManager);

        // Creating an optional pet object and find the id of the pet in the visit's pojo
        Optional<Pet> petO = petRepository.findAll(visitPOJO.getPet_id());

        //If the id doesn't exist return false

        if (!petO.isPresent()) return "No existe esa identificación de mascota";

        // Creating an optional vet object and find the id of the vet in the visit's pojo
        //Optional<Vet> vetO = vetRepository.findByUserName(visitPOJO.getVet_id());

        //If the id doesn't exist return false
       // if (!vetO.isPresent()) return "No existe esa veterinaria";

        Pet pet = petRepository.findById(visitPOJO.getPet_id()).get();

        //Vet vet = vetRepository.findById(visitPOJO.getVetUsername()).get();

        //Validating the format of the date, passing date of string to date
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date createdAt;
        try {
            createdAt = format.parse(visitPOJO.getCreated_at());
        } catch (ParseException e) {
            return "El formato de la fecha es incorrecto!";
        }

        //Creating the visit and save it in the repository
        //Visit visit = new Visit(createdAt, visitPOJO.getType(), visitPOJO.getDescription(), vet, pet);

       // pet.addVisits(visit);
        petRepository.save(pet);

        //vet.addVisit(visit);
        //vetRepository.save(vet);

        entityManager.close();
        entityManagerFactory.close();
        return "Se ha creado exitosamente la visita!";
    }
}
