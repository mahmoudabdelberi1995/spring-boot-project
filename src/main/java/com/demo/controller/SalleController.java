package com.demo.controller;

import com.demo.entity.Salle;
import com.demo.exception.ResourceNotFoundException;
import com.demo.repository.SalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salles")

public class SalleController {
@Autowired
    public SalleRepository salleRepository;

    //create salle
    @PostMapping
    public Salle createSalle(@RequestBody Salle salle){ return this.salleRepository.save(salle);}

    //get all salles
    @GetMapping
    public List<Salle> getAllSalle(){return this.salleRepository.findAll();}

    //get salle by id
    @GetMapping("/{id}")
    public Salle getSalleById(@PathVariable(value = "id") long salleId){
       return this.salleRepository.findById(salleId)
               .orElseThrow(()-> new ResourceNotFoundException("salle not found:"+salleId));
    }
    @PutMapping("/{id}")
    public Salle updateSalle(@PathVariable(value = "id") long salleId,@RequestBody Salle salle){
       Salle existingSalle=this.salleRepository.findById(salleId)
               .orElseThrow(()->new ResourceNotFoundException("salle not found : "+salleId));
    existingSalle.setName(salle.getName());
    existingSalle.setBlock(salle.getBlock());
    existingSalle.setCapacity(salle.getCapacity());
    return this.salleRepository.save(existingSalle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity< Salle > deleteSalle(@PathVariable (value = "id") long salleId){
        Salle existingSalle=this.salleRepository.findById(salleId)
                .orElseThrow(()->new ResourceNotFoundException("salle not found : "+salleId));
        this.salleRepository.delete(existingSalle);
        return ResponseEntity.ok().build();
    }

}
