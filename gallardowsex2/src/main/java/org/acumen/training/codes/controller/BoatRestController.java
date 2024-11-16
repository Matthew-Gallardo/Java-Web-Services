package org.acumen.training.codes.controller;

import org.acumen.training.codes.dao.BoatDao;
import org.acumen.training.codes.model.data.Boat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bms/boat")
public class BoatRestController {
    public static final Logger LOGGER = LogManager.getLogger(BoatRestController.class);

    @Autowired
    private BoatDao boatDao;

    @PostMapping("/add")
    public Boat addBoat(@RequestBody Boat boat) {
        LOGGER.info("Adding new boat: {}", boat);
        boatDao.addBoat(boat);
        LOGGER.info("Boat added successfully: {}", boat);
        return boat;
    }

    @GetMapping("/list/all")
    public List<Boat> listAllBoats() {
        LOGGER.info("Listing all boats");
        List<Boat> boats = boatDao.getAllBoats();
        LOGGER.info("Number of boats found: {}", boats.size());
        return boats;
    }
}
