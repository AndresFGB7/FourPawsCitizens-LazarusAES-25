package edu.unbosque.FourPawsCitizens_LazarusAES_25.services;



import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Official;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories.OfficialRepository;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories.OfficialRepositoryImpl;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.resources.pojos.OfficialPOJO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 * The service of Official, use Repository of official
 */
public class OfficialService {

    OfficialRepository officialRepository;

    /**
     * Get all the officials of DB
     * @return List of Official
     */
    public List<OfficialPOJO> listOfficial(){

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        officialRepository = new OfficialRepositoryImpl(entityManager);
        List<Official> officials = officialRepository.findAll();

        entityManager.close();
        entityManagerFactory.close();

        List<OfficialPOJO> officialPOJOS = new ArrayList<>();
        for (Official official : officials){
            officialPOJOS.add(new OfficialPOJO());
        }
        return officialPOJOS;
    }


    public Official saveOfficial(){

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        officialRepository = new OfficialRepositoryImpl(entityManager);

        Official official = new Official();
        Official persistedOfficial = officialRepository.save(official).get();

        entityManager.close();
        entityManagerFactory.close();

        return persistedOfficial;
    }

    /**
     * Delete a Official of the DB
     * @param id: Integer -> ID to delete a Official
     */
    public void deleteOfficial(Integer id){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        officialRepository = new OfficialRepositoryImpl(entityManager);
        officialRepository.deleteByid(id);

        entityManager.close();
        entityManagerFactory.close();
    }


    /**
     * Edit a Official of the DB
     */
    public void  editOfficial(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        officialRepository = new OfficialRepositoryImpl(entityManager);
        officialRepository.editOfficial( );

        entityManager.close();
        entityManagerFactory.close();
    }

}
