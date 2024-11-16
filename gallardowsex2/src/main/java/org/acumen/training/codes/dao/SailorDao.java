package org.acumen.training.codes.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.acumen.training.codes.model.data.Sailor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SailorDao {
	public static final Logger LOGGER = LogManager.getLogger(SailorDao.class);

    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
    public void addSailor(Sailor sailor) {
    	try {
    		entityManager.persist(sailor);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
    @Transactional
    public void updateSailor(Sailor sailor) {
    	try {
    		 entityManager.merge(sailor);
		} catch (Exception e) {
			e.printStackTrace();
		}
       
    }
    @Transactional
    public void deleteSailor(Integer id) {
    	try {
    	Sailor sailor = entityManager.find(Sailor.class, id);
    	if (sailor != null) {
    	     entityManager.remove(sailor);
    	    }
		} catch (Exception e) {
			e.printStackTrace();
		}
     
    }
    @Transactional
    public List<Sailor> getAllSailors() {
        return entityManager.createQuery("SELECT s FROM Sailor s", Sailor.class).getResultList();
    }
    @Transactional
    public Sailor getSailorById(Integer id) {
        return entityManager.find(Sailor.class, id);
    }
}
