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

public class OfficialService {

    OfficialRepository officialRepository;

    public String createOfficial(OfficialPOJO officialPOJO) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        officialRepository = new OfficialRepositoryImpl(entityManager);
        Official official = new Official(
                officialPOJO.getUsername(),
                officialPOJO.getPassword(),
                officialPOJO.getEmail(),
                officialPOJO.getRole(),
                officialPOJO.getName());
        String reply = officialRepository.save(official);

        entityManager.close();
        entityManagerFactory.close();

        return reply;

    }

    public List<OfficialPOJO> listOfficial() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        officialRepository = new OfficialRepositoryImpl(entityManager);

        List<Official> official = officialRepository.listAll();

        entityManager.close();
        entityManagerFactory.close();

        List<OfficialPOJO> officialPOJOS = new ArrayList<>();
        for (Official official1 : official) {
            officialPOJOS.add(new OfficialPOJO(
                    official1.getUsername(),
                    official1.getPassword(),
                    official1.getEmail(),
                    official1.getRole(),
                    official1.getName()));
        }
        return officialPOJOS;
    }
    public List<OfficialPOJO> getOfficial(String username) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("LazarusAES-256");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        officialRepository = new OfficialRepositoryImpl(entityManager);

        List<Official> official = officialRepository.getByUsername(username);

        entityManager.close();
        entityManagerFactory.close();

        List<OfficialPOJO> officialPOJOS = new ArrayList<>();
        for (Official official1 : official) {
            officialPOJOS.add(new OfficialPOJO(
                    official1.getUsername(),
                    official1.getPassword(),
                    official1.getEmail(),
                    official1.getRole(),
                    official1.getName()));
        }
        return officialPOJOS;
    }


}
