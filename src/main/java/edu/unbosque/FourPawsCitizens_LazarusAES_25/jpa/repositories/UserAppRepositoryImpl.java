package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.UserApp;

import javax.persistence.EntityManager;
import java.util.Optional;

/*
 *  This class have the methods for save, an find by username from the Database of users
 */
public class UserAppRepositoryImpl implements UserAppRepository {

    private EntityManager entityManager;

    public UserAppRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * find a user of DataBase
     * @param username
     * @return mull or Optional of user
     */
    @Override
    public Optional<UserApp> findByUsername(String username) {
        UserApp user = entityManager.find(UserApp.class, username);
        return user != null ? Optional.of(user) : Optional.empty();
    }

    /**
     * Save a user
     * @param user
     * @return Optional of User
     */
    @Override
    public Optional<UserApp> save(UserApp user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            return Optional.of(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
