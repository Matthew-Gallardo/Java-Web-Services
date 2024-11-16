package org.acumen.training.codes.controller;

import java.util.List;

import org.acumen.training.codes.dao.SailorDao;
import org.acumen.training.codes.model.data.Sailor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bms/sailor")
public class SailorRestController {
    public static final Logger LOGGER = LogManager.getLogger(SailorRestController.class);

    @Autowired
    private SailorDao sailorDao;

    @PostMapping(path ="/add")
    public Sailor addSailor(@RequestBody Sailor sailor) {
        LOGGER.info("Adding new sailor: {}", sailor);
        sailorDao.addSailor(sailor);
        LOGGER.info("Sailor added successfully: {}", sailor);
        return sailor;
    }

    @DeleteMapping(path ="/delete/{id}")
    public String deleteSailor(@PathVariable Integer id) {
        LOGGER.info("Deleting sailor with ID {}", id);
        sailorDao.deleteSailor(id);
        LOGGER.info("Sailor with ID {} deleted successfully", id);
        return "Sailor deleted";
    }

    @GetMapping(path="/list/all")
    public List<Sailor> listAllSailors() {
        LOGGER.info("Listing all sailors");
        List<Sailor> sailors = sailorDao.getAllSailors();
        LOGGER.info("Number of sailors found: {}", sailors.size());
        return sailors;
    }


    @PatchMapping(path = "/update/name")
    public Sailor updateSailorName(@RequestParam Integer id, @RequestParam String name) {
        LOGGER.info("Updating sailor with ID {} to new name: {}", id, name);
        Sailor sailor = sailorDao.getSailorById(id);
        if (sailor != null) {
            sailor.setName(name);
            sailorDao.updateSailor(sailor);
            LOGGER.info("Sailor name updated successfully: {}", sailor);
        } else {
            LOGGER.warn("Sailor with ID {} not found", id);
        }
        return sailor;
    }

    @PutMapping(path = "/update/all")
    public Sailor updateSailor(@RequestBody Sailor sailor) {
        LOGGER.info("Updating sailor: {}", sailor);
        Sailor existingSailor = sailorDao.getSailorById(sailor.getId());
        if (existingSailor != null) {
            existingSailor.setName(sailor.getName());
            existingSailor.setAge(sailor.getAge());
            existingSailor.setRating(sailor.getRating());
            sailorDao.updateSailor(existingSailor);
            LOGGER.info("Sailor updated successfully: {}", existingSailor);
        } else {
            LOGGER.warn("Sailor with ID {} not found", sailor.getId());
        }
        return existingSailor;
    }
}
