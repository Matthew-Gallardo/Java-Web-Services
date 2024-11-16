package org.acumen.training.codes.controller;

import org.acumen.training.codes.dao.ReservationDao;
import org.acumen.training.codes.model.data.Reservation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bms/reservation")
public class ReservationRestController {
    public static final Logger LOGGER = LogManager.getLogger(ReservationRestController.class);

    @Autowired
    private ReservationDao reservationDao;

    @PostMapping("/add")
    public Reservation addReservation(@RequestBody Reservation reservation) {
        LOGGER.info("Adding new reservation: {}", reservation);
        reservationDao.addReservation(reservation);
        LOGGER.info("Reservation added successfully: {}", reservation);
        return reservation;
    }

    @GetMapping("/id/{id}")
    public Reservation getReservationById(@PathVariable Integer id) {
        LOGGER.info("Fetching reservation with ID: {}", id);
        Reservation reservation = reservationDao.getReservationById(id);
        if (reservation != null) {
            LOGGER.info("Reservation found: {}", reservation);
        } else {
            LOGGER.warn("Reservation with ID {} not found", id);
        }
        return reservation;
    }

    @GetMapping("/boat/{bid}")
    public List<Reservation> getReservationsByBoatId(@PathVariable Integer bid) {
        LOGGER.info("Fetching reservations for boat with ID: {}", bid);
        List<Reservation> reservations = reservationDao.getReservationsByBoatId(bid);
        LOGGER.info("Number of reservations found for boat ID {}: {}", bid, reservations.size());
        return reservations;
    }
}
