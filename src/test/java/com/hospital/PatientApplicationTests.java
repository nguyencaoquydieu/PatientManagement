package com.hospital;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.hospital.model.Patient;
import com.hospital.repository.PatientRepository;
import com.hospital.service.PatientService;

@SpringBootTest(classes = PatientApplication.class)
@ActiveProfiles("test")
class PatientApplicationTests {

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;

    @BeforeEach
    void setUp() {
        patientRepository.deleteAll();
    }

    @Test
    void contextLoads() {
    }

    @Test
    void testCreateAndGetPatient() {
        // Create a test patient
        Patient patient = new Patient();
        patient.setName("John Doe");
        patient.setAge(30);
        patient.setMedicalHistory("No previous conditions");

        // Save the patient
        Patient savedPatient = patientService.savePatient(patient);
        assertNotNull(savedPatient.getId());
        assertEquals("John Doe", savedPatient.getName());

        // Retrieve the patient
        Patient retrievedPatient = patientService.findPatientById(savedPatient.getId());
        assertNotNull(retrievedPatient);
        assertEquals(savedPatient.getId(), retrievedPatient.getId());
        assertEquals("John Doe", retrievedPatient.getName());
        assertEquals(30, retrievedPatient.getAge());
    }

    @Test
    void testUpdatePatient() {
        // Create a test patient
        Patient patient = new Patient();
        patient.setName("Jane Smith");
        patient.setAge(25);
        Patient savedPatient = patientService.savePatient(patient);

        // Update patient
        savedPatient.setAge(26);
        Patient updatedPatient = patientService.savePatient(savedPatient);
        assertEquals(26, updatedPatient.getAge());
    }

    @Test
    void testDeletePatient() {
        // Create a test patient
        Patient patient = new Patient();
        patient.setName("Test Patient");
        Patient savedPatient = patientService.savePatient(patient);

        // Delete patient
        patientService.deletePatient(savedPatient.getId());

        // Verify deletion
        assertThrows(RuntimeException.class, () -> {
            patientService.findPatientById(savedPatient.getId());
        });
    }
}
