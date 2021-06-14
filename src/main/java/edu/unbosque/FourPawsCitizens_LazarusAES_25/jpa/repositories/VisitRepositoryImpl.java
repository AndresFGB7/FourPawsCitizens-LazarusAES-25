package edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.repositories;

import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Owner;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Pet;
import edu.unbosque.FourPawsCitizens_LazarusAES_25.jpa.entities.Visit;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
/**
 * This class have the methods for save, find by id, find all, delete by id and edit a Visit to a vet from the Database
 */
public class VisitRepositoryImpl implements VisitRepository {

  private EntityManager entityManager;

  public VisitRepositoryImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }
  /**
   * Save a Visit
   * @param visit: Visit
   * @return Optional of Visit
   */
  @Override
  public String save(Visit visit) {
    try {
      entityManager.getTransaction().begin();
      entityManager.persist(visit);
      entityManager.getTransaction().commit();
      return "visit was successfully created";
    }catch (Exception e) {
      e.printStackTrace();
    }
    return "the visit could not be created";
  }
  /**
   * Find by id a Visit
   *
   * @param id: Integer
   * @return Optional of Visit
   */
  @Override
  public Optional<Visit> findById(Integer id) {
    Visit visit = entityManager.find(Visit.class, id);
    return visit != null ? Optional.of(visit) : Optional.empty();

  }
  /**
   * Find all the Visit
   * @return List of all Visit
   */
  @Override
  public List<Visit> findAll() {
    return entityManager.createQuery("from Visit ").getResultList();
  }
  /**
   * Delete by id a Visit
   *
   * @param id: Integer
   */
  @Override
  public String deleteById(Integer id) {
    Visit visit = entityManager.find(Visit.class, id);
    if (visit != null) {
      try {
        entityManager.getTransaction().begin();
        entityManager.remove(visit);
        entityManager.getTransaction().commit();
        return "the visit was successfully removed";
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return "the visit could not be deleted";
  }

  /**
   * Edit a Visit of the DataBase
   * @param id: Integer
   * @param created_at: String
   * @param type: String
   * @param description: String
   * @param vet_id: Integer
   * @param pet_id: Integer
   */
  @Override
  public String editVisit(Integer id, String created_at, String type, String description, Integer vet_id, Integer pet_id) {
    Visit visit = entityManager.find(Visit.class,pet_id);
    if(visit != null){
      try {
        entityManager.getTransaction().begin();
        visit.setCreated_at(created_at);
        visit.setType(type);
        visit.setDescription(description);
        entityManager.getTransaction().commit();
        return "the visit was correctly edited";
      }catch (Exception e){
        e.printStackTrace();
      }

    }
    return "the visit could not be edited";
  }
}
