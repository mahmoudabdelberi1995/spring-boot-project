package com.demo.controller;

import com.demo.entity.Formation;
import com.demo.exception.ResourceNotFoundException;
import com.demo.repository.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/formation")
public class FormationController {
    @Autowired
    public FormationRepository formationRepository;
    //create formation
    @PostMapping
    public Formation createFormation( @RequestBody Formation formation){
        return this.formationRepository.save(formation);
    }
//get formation
    @GetMapping("/{id}")
    public Formation getFormationById(@PathVariable (value = "id") long idFormation){
        return this.formationRepository.findById(idFormation)
                .orElseThrow(() -> new ResourceNotFoundException("Formation not found with id :" + idFormation));
    }
    //getAllFormation
    @GetMapping
    public List <Formation>  getAllFormation (){ return this.formationRepository.findAll();}

    //update formation
    @PutMapping("/{id}")
    public Formation updateFormation(@RequestBody Formation formation,@PathVariable(value = "id") long idFormation){
        Formation existingFormation=this.formationRepository.findById(idFormation)
                .orElseThrow(() -> new ResourceNotFoundException("formation not found : "+idFormation));
        existingFormation.setDateDebut(formation.getDateDebut());
        existingFormation.setDateFin(formation.getDateFin());
        existingFormation.setName(formation.getName());
        existingFormation.setFormateur(formation.getFormateur());
        return this.formationRepository.save(existingFormation);
    }
    //delete formation
    @DeleteMapping("/{id}")
    public ResponseEntity<Formation> deleteFormation(@PathVariable(value = "id") long idFormation){
        Formation existingFormation=this.formationRepository.findById(idFormation)
                .orElseThrow(() -> new ResourceNotFoundException("formation not found : "+idFormation));
        this.formationRepository.delete(existingFormation);
        return ResponseEntity.ok().build();
    }
}
