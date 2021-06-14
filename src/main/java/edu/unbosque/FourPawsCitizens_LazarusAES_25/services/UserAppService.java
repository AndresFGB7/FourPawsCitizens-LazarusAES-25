package edu.unbosque.FourPawsCitizens_LazarusAES_25.services;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.*;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories.*;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.PetPOJO;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.UserAppPOJO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

@Stateless
public class UserAppService {

    UserAppRepository userAppRepository;

    public Optional<String> validateUser( String username, String password ) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Getting credentials from the database
        userAppRepository = new UserAppRepositoryImpl(entityManager);
        Optional<UserApp> user = userAppRepository.findByUsername(username);

        entityManager.close();
        entityManagerFactory.close();

        // Validating if credentials provided by the user are valid
        // If success, return the user role
        if (user.isPresent()) {
            if (user.get().getUsername().equals(username) && user.get().getPassword().equals(password)) {
                return Optional.of(user.get().getRole());
            }
        }

        return Optional.empty();

    }
    public Optional<UserAppPOJO> saveUserApp(UserAppPOJO userAppPOJO){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        userAppRepository = new UserAppRepositoryImpl(entityManager);

        UserApp userApp = new UserApp(userAppPOJO.getUsername(),userAppPOJO.getPassword(),userAppPOJO.getEmail(),userAppPOJO.getRole());
        Optional<UserApp> persistedOwner = userAppRepository.save(userApp);

        entityManager.close();
        entityManagerFactory.close();

        if (persistedOwner.isPresent()) {
            return Optional.of(new UserAppPOJO(
                    persistedOwner.get().getUsername(),
                    persistedOwner.get().getPassword(),
                    persistedOwner.get().getEmail(),
                    persistedOwner.get().getRole()));
        } else {
            return Optional.empty();
        }

    }

}
