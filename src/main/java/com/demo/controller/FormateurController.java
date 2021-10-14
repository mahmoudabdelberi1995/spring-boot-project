package com.demo.controller;

import com.demo.entity.Formateur;
import com.demo.exception.ResourceNotFoundException;
import com.demo.repository.FormateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin ( origins = "http://localhost:4200" )
@RequestMapping("/api/formateurs")
public class FormateurController {


    @Autowired
    private FormateurRepository formateurRepository;


    // get all formateurs
    @GetMapping
    public List<Formateur> getAllFormateurs() {
        return this.formateurRepository.findAll();
    }

    // get formateur by id
    @GetMapping("/{id}")
    public Formateur getUserById(@PathVariable(value = "id") long formateurId) {
        return this.formateurRepository.findById(formateurId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + formateurId));
    }

    // create formateur
    @PostMapping
    public Formateur createFormateur(@RequestBody  Formateur formateur) {
        return this.formateurRepository.save(formateur);
    }

    // update formateur
    @PutMapping("/{id}")
    public Formateur updateFormateur(@RequestBody Formateur formateur, @PathVariable("id") long formateurId) {
        Formateur existingFormateur = this.formateurRepository.findById(formateurId)
                .orElseThrow(() -> new ResourceNotFoundException("formateur not found with id :" + formateurId));
        existingFormateur.setFirstName(formateur.getFirstName());
        existingFormateur.setLastName(formateur.getLastName());
        existingFormateur.setEmail(formateur.getEmail());
        existingFormateur.setPhone(formateur.getPhone());
        existingFormateur.setSpecialty(formateur.getSpecialty());
        return this.formateurRepository.save(existingFormateur);
    }

    // delete formateur by id
    @DeleteMapping("/{id}")
    public ResponseEntity< Formateur > deleteFormateur(@PathVariable("id") long formateurId) {
        Formateur existingUser = this.formateurRepository.findById(formateurId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + formateurId));
        this.formateurRepository.delete(existingUser);
        return ResponseEntity.ok().build();
    }

}
