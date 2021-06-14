package edu.unbosque.FourPawsCitizens_LazarusAES_25.services;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Owner;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories.OwnerRepository;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories.OwnerRepositoryImpl;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.OwnerPOJO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
public class OwnerService {

    OwnerRepository ownerRepository;

    public String  createOwner(OwnerPOJO ownerPOJO) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ownerRepository = new OwnerRepositoryImpl(entityManager);

        Owner owner = new Owner(
                ownerPOJO.getUsername(),
                ownerPOJO.getPassword(),
                ownerPOJO.getEmail(),
                ownerPOJO.getPersonId(),
                ownerPOJO.getName(),
                ownerPOJO.getAddress(),
                ownerPOJO.getNeighborhood());
        String reply = ownerRepository.save(owner);

        entityManager.close();
        entityManagerFactory.close();
        return reply;
    }

    public Optional<OwnerPOJO> findOwner(String id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ownerRepository = new OwnerRepositoryImpl(entityManager);

        Optional<Owner> owners = ownerRepository.findById(id);

        entityManager.close();
        entityManagerFactory.close();

        if (owners.isPresent()) {
            return Optional.of(new OwnerPOJO(owners.get().getUsername(),
                    owners.get().getPassword(),
                    owners.get().getEmail(),
                    owners.get().getPersonId(),
                    owners.get().getName(),
                    owners.get().getAddress(),
                    owners.get().getNeighborhood()));
        } else {
            System.out.println("non-existent owner");
            return Optional.empty();
        }
    }

    public List<OwnerPOJO> listOwners() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ownerRepository = new OwnerRepositoryImpl(entityManager);
        List<Owner> owners = ownerRepository.findAll();

        entityManager.close();
        entityManagerFactory.close();

        List<OwnerPOJO> ownerPOJOS = new ArrayList<>();
        for (Owner owner : owners) {
            ownerPOJOS.add(new OwnerPOJO(owner.getUsername(), owner.getPassword(), owner.getEmail(), owner.getPersonId(), owner.getName(), owner.getAddress(), owner.getNeighborhood()));
        }
        return ownerPOJOS;
    }

    public Optional<OwnerPOJO> findByUserName(String username){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ownerRepository = new OwnerRepositoryImpl(entityManager);
        Optional<Owner> owners = ownerRepository.findByUsername(username);

        entityManager.close();
        entityManagerFactory.close();

        if (owners.isPresent()) {
            return Optional.of(new OwnerPOJO(owners.get().getUsername(),
                    owners.get().getPassword(),
                    owners.get().getEmail(),
                    owners.get().getPersonId(),
                    owners.get().getName(),
                    owners.get().getAddress(),
                    owners.get().getNeighborhood()));
        } else {
            System.out.println("non-existent owner");
            return Optional.empty();
        }
    }

    public String editOwner(OwnerPOJO ownerPOJO) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ownerRepository = new OwnerRepositoryImpl(entityManager);
        String reply = ownerRepository.editOwner(ownerPOJO.getPersonId(),
                ownerPOJO.getUsername(),
                ownerPOJO.getPassword(),
                ownerPOJO.getEmail(),
                ownerPOJO.getName(),
                ownerPOJO.getAddress(),
                ownerPOJO.getNeighborhood());
        entityManager.close();
        entityManagerFactory.close();
        return reply;

    }

    public String deleteOwner(String username) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        ownerRepository = new OwnerRepositoryImpl(entityManager);
        String reply =  ownerRepository.deleteByUserName(username);
        entityManager.close();
        entityManagerFactory.close();
        return reply;
    }


}
