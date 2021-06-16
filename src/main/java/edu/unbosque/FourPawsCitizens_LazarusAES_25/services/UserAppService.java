package edu.unbosque.FourPawsCitizens_LazarusAES_25.services;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.*;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories.*;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.CasePOJO;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.PetPOJO;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.UserAppPOJO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless
public class UserAppService {

    UserAppRepository userAppRepository;

    public Optional<String> validateUser(String username, String password) {

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

    public String saveUserApp(UserAppPOJO userAppPOJO) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        userAppRepository = new UserAppRepositoryImpl(entityManager);

        UserApp userApp = new UserApp(userAppPOJO.getUsername(), userAppPOJO.getPassword(), userAppPOJO.getEmail(), userAppPOJO.getRole());
        String reply = userAppRepository.save(userApp);

        entityManager.close();
        entityManagerFactory.close();

        return reply;
    }

    public List<UserAppPOJO> ListUsers(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        userAppRepository = new UserAppRepositoryImpl(entityManager);
        List<UserApp> users = userAppRepository.findAll();
        entityManager.close();
        entityManagerFactory.close();

        List<UserAppPOJO> userApps = new ArrayList<>();
        for(UserApp user : users){
            userApps.add(new UserAppPOJO(user.getUsername(),user.getPassword(),user.getEmail(),user.getRole()));
        }
        return userApps;
    }

}
