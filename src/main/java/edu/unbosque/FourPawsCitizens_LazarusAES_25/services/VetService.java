package edu.unbosque.FourPawsCitizens_LazarusAES_25.services;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Vet;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories.VetRepository;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories.VetRepositoryImpl;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.VetPOJO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 * The service of Vet, use Repository of vet
 */
public class VetService {

    VetRepository vetRepository;

    /**
     *
     * @return List of Vet
     */
    public List<VetPOJO> listVet(){

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        vetRepository = new VetRepositoryImpl(entityManager);
        List<Vet> vets = vetRepository.findAll();

        entityManager.close();
        entityManagerFactory.close();

        List<VetPOJO> vetPOJOS = new ArrayList<>();
        for (Vet vet : vets){
            vetPOJOS.add(new VetPOJO(vet.getUsername(),vet.getPassword(),vet.getEmail() ,vet.getName(),vet.getAddress(),vet.getNeighborhood()));
        }
        return vetPOJOS;
    }

    /**
     * Save in DB a Vet
     * @param username: String
     * @param password: String
     * @param email: String
     * @param name: String
     * @param address: String
     * @param neighborhood: String
     * @return an object (Vet)
     */
    public Vet saveVet(String username,String password, String email, String name, String address, String neighborhood){

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        vetRepository = new VetRepositoryImpl(entityManager);

        Vet vet = new Vet(username,password,email,name,address,neighborhood);
        Vet persistedVet = vetRepository.save(vet).get();

        entityManager.close();
        entityManagerFactory.close();

        return persistedVet;
    }

    /**
     * Delete a Vet of the DB
     * @param username: String -> ID to delete a Vet
     */
    public void deleteVet(String username){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        vetRepository = new VetRepositoryImpl(entityManager);
        vetRepository.deleteByUserName(username);

        entityManager.close();
        entityManagerFactory.close();
    }

    /**
     * Edit a Vet of the DB
     * @param username: String -> ID to delete a Vet
     * @param password: String
     * @param email: String
     * @param name: String
     * @param address: String
     * @param neighborhood: String
     */
    public void  editVet(String username,String password, String email, String name, String address, String neighborhood){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        vetRepository = new VetRepositoryImpl(entityManager);
        vetRepository.editVet(username, password,  email,  name, address,  neighborhood);

        entityManager.close();
        entityManagerFactory.close();
    }

}
