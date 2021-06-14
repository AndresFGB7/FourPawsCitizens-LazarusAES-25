package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.UserApp;
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
    public String save(UserApp user) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            return "The User was successfully created";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "The User was not created";
    }

    @Override
    public void edit(UserApp usera) {
        UserApp userb = entityManager.find(UserApp.class,usera.getUsername());
        if(userb != null){
            try {
                entityManager.getTransaction().begin();
                userb.setEmail(usera.getEmail());
                userb.setPassword(usera.getPassword());
                userb.setRole(usera.getRole());
                entityManager.getTransaction().commit();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

}
