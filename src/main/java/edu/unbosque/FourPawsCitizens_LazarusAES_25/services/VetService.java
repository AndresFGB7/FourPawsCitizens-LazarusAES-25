package edu.unbosque.FourPawsCitizens_LazarusAES_25.services;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Owner;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Vet;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories.OwnerRepositoryImpl;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories.VetRepository;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories.VetRepositoryImpl;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.OwnerPOJO;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.VetPOJO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
     * @param vetPOJO: VetPOJO
     * @return an Optional of POJO
     */

    public Optional<VetPOJO> saveVet(VetPOJO vetPOJO){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        vetRepository = new VetRepositoryImpl(entityManager);

        Vet vet = new Vet(vetPOJO.getUsername(),vetPOJO.getPassword(),vetPOJO.getEmail(),vetPOJO.getName(),vetPOJO.getAddress(),vetPOJO.getNeighborhood());
        Optional<Vet> persistedOwner = vetRepository.save(vet);

        entityManager.close();
        entityManagerFactory.close();

        if (persistedOwner.isPresent()) {
            return Optional.of(new VetPOJO(persistedOwner.get().getUsername(),
                    persistedOwner.get().getPassword(),
                    persistedOwner.get().getEmail(),
                    persistedOwner.get().getName(),
                    persistedOwner.get().getAddress(),
                    persistedOwner.get().getNeighborhood()));
        } else {
            return Optional.empty();
        }

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
