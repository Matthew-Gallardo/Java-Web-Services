package org.acumen.training.codes.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.acumen.training.codes.model.data.Reservation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationDao {
	public static final Logger LOGGER = LogManager.getLogger(ReservationDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void addReservation(Reservation reservation) {
        entityManager.persist(reservation);
    }
    @Transactional
    public Reservation getReservationById(Integer id) {
        return entityManager.find(Reservation.class, id);
    }
    @Transactional
    public List<Reservation> getReservationsByBoatId(Integer bid) {
        return entityManager.createQuery("SELECT r FROM Reservation r WHERE r.boat.id = :bid", Reservation.class)
                .setParameter("bid", bid)
                .getResultList();
    }
}
