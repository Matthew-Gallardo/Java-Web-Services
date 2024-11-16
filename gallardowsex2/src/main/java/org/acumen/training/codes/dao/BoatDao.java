package org.acumen.training.codes.dao;

import java.util.List;

import org.acumen.training.codes.model.data.Boat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class BoatDao {
	public static final Logger LOGGER = LogManager.getLogger(BoatDao.class);

    @PersistenceContext
    private EntityManager entityManager;
    
    @Transactional
    public void addBoat(Boat boat) {
    	try {
    		entityManager.persist(boat);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
    
    @Transactional
    public List<Boat> getAllBoats() {
        return entityManager.createQuery("SELECT b FROM Boat b", Boat.class).getResultList();
    }
}
