package com.example.codeclan.whiskytracker.controllers;

import com.example.codeclan.whiskytracker.models.Whisky;
import com.example.codeclan.whiskytracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class WhiskyController {
    @Autowired
    WhiskyRepository whiskyRepository;

    // GET /whiskies
    // GET /whiskies?year=2018
    // GET /whiskies?age=16&distillery=Lagavulin
    // GET /whiskies?region=Highland
    @GetMapping(value="/whiskies")
    public ResponseEntity<List<Whisky>> getAllWhiskies(
            @RequestParam(name="year", required = false) Integer year,
            @RequestParam(name="distillery", required = false) String distillery,
            @RequestParam(name="age", required = false) Integer age,
            @RequestParam(name="region", required = false) String region
    ) {
        if(year != null){
            return new ResponseEntity<>(whiskyRepository.findByYear(year), HttpStatus.OK);
        }
        if (distillery != null && age == null) {
            return new ResponseEntity<>(whiskyRepository.findWhiskiesByDistilleryName(distillery), HttpStatus.OK);
        }
        if(age != null && distillery == null){
            return new ResponseEntity<>(whiskyRepository.findByAge(age), HttpStatus.OK);
        }
        if(distillery != null && age != null){
            return new ResponseEntity<>(whiskyRepository.findWhiskiesByAgeAndDistilleryName(age, distillery), HttpStatus.OK);
        }
        if (region != null){
            return new ResponseEntity<>(whiskyRepository.findWhiskiesByDistilleryRegion(region), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/whiskies/{id}")
    public ResponseEntity<Optional<Whisky>> getWhiskyById(@PathVariable Long id) {
        return new ResponseEntity<>(whiskyRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping("/whiskies")
    public ResponseEntity<Whisky> createNewWhisky(@RequestBody Whisky whisky){
        whiskyRepository.save(whisky);
        return new ResponseEntity<>(whisky, HttpStatus.CREATED);
    }
}
