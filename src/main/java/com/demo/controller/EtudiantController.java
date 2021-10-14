package com.demo.controller;

import com.demo.entity.Etudiant;
import com.demo.exception.ResourceNotFoundException;
import com.demo.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {

        @Autowired
        private EtudiantRepository etudiantRepository;

        // get all etudiants
        @GetMapping
        public List<Etudiant> getAllEtudiants() {
            return this.etudiantRepository.findAll();
        }

        // get etudiant by id
        @GetMapping("/{id}")
        public Etudiant getEtudiantById(@PathVariable(value = "id") long etudiantId) {
            return this.etudiantRepository.findById(etudiantId)
                    .orElseThrow(() -> new ResourceNotFoundException("Etudiant not found with id :" + etudiantId));
        }

        // create Etudiant
        @PostMapping
        public Etudiant createEtudiant(@RequestBody  Etudiant etudiant) {
            return this.etudiantRepository.save(etudiant);
        }

        // update Etudiant
        @PutMapping("/{id}")
        public Etudiant updateEtudiant(@RequestBody Etudiant etudiant, @PathVariable("id") long etudiantId) {
            Etudiant existingEtudiant = this.etudiantRepository.findById(etudiantId)
                    .orElseThrow(() -> new ResourceNotFoundException("etudiant not found with id :" + etudiantId));
            existingEtudiant.setFirstName(etudiant.getFirstName());
            existingEtudiant.setLastName(etudiant.getLastName());
            existingEtudiant.setEmail(etudiant.getEmail());
            existingEtudiant.setLevel(etudiant.getLevel());
            existingEtudiant.setNic(etudiant.getNic());
            existingEtudiant.setPhone(etudiant.getPhone());
            existingEtudiant.setSpeciality(etudiant.getSpeciality());

            return this.etudiantRepository.save(existingEtudiant);
        }

        // delete formateur by id
        @DeleteMapping("/{id}")
        public ResponseEntity< Etudiant > deleteEtudiant(@PathVariable("id") long etudiantId) {
            Etudiant existingEtudiant = this.etudiantRepository.findById(etudiantId)
                    .orElseThrow(() -> new ResourceNotFoundException("etudiant not found with id :" + etudiantId));
            this.etudiantRepository.delete(existingEtudiant);
            return ResponseEntity.ok().build();
        }
}
