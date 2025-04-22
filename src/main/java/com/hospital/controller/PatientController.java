package com.hospital.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.model.Patient;
import com.hospital.service.PatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable Long id) {
        logger.info("Fetching patient with id: {}", id);
        Patient patient = patientService.findPatientById(id);
        logger.info("Found patient: {}", patient);
        return ResponseEntity.ok(patient);
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        logger.info("Creating new patient: {}", patient);
        Patient createdPatient = patientService.savePatient(patient);
        logger.info("Created patient with id: {}", createdPatient.getId());
        return ResponseEntity.status(201).body(createdPatient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        logger.info("Updating patient with id: {}", id);
        patient.setId(id);
        Patient updatedPatient = patientService.savePatient(patient);
        logger.info("Updated patient: {}", updatedPatient);
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        logger.info("Deleting patient with id: {}", id);
        patientService.deletePatient(id);
        logger.info("Deleted patient with id: {}", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        logger.info("Fetching all patients");
        List<Patient> patients = patientService.findAllPatients();
        logger.info("Found {} patients", patients.size());
        return ResponseEntity.ok(patients);
    }
}