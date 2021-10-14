package com.demo.controller;

import com.demo.entity.Seance;
import com.demo.exception.ResourceNotFoundException;
import com.demo.repository.SeanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seances")
public class SeanceController {
    @Autowired
    public SeanceRepository seanceRepository;

    //create seance
    @PostMapping
    public Seance createSeance(@RequestBody Seance seance){
        return this.seanceRepository.save(seance);
    }
    //get all seances
    @GetMapping
    public List<Seance> getAllSeance(){return  this.seanceRepository.findAll();}
    //get seance by id
    @GetMapping("/{id}")
    public Seance getSeanceById(@PathVariable(value = "id") long seanceId){
         return this.seanceRepository.findById(seanceId)
                .orElseThrow(()->new ResourceNotFoundException("seance not found : " + seanceId));
    }
    @PutMapping("/{id}")
    public Seance updateSeance(@PathVariable(value = "id") long seanceId,@RequestBody Seance seance){
        Seance existingSeance=this.seanceRepository.findById(seanceId)
                .orElseThrow(()->new ResourceNotFoundException("seance not found : " + seanceId));
        existingSeance.setFormateur(seance.getFormateur());
        existingSeance.setSalle(seance.getSalle());
        return this.seanceRepository.save(existingSeance);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Seance> deleteSeance(@PathVariable ("id") long seanceId){
        Seance existingSeance=this.seanceRepository.findById(seanceId)
                .orElseThrow(()->new ResourceNotFoundException("seance not found : "+seanceId));
        return ResponseEntity.ok().build();
    }
}
