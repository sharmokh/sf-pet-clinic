package com.sharmokh.sfpetclinic.services.map;

import com.sharmokh.sfpetclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PetMapServiceTest {

    private PetMapService petMapService;
    private final Long PETID = 1L;

    @BeforeEach
    void setUp() {
        petMapService = new PetMapService();
        petMapService.save(Pet.builder().id(PETID).build());
    }

    @Test
    void findAll() {
        Set<Pet> petSet = petMapService.findAll();
        assertEquals(1, petSet.size());
    }

    @Test
    void findByIdFound() {
        Pet pet = petMapService.findByID(1L);
        assertEquals(PETID, pet.getId());
    }

    @Test
    void findByIdNotFound() {
        Pet pet = petMapService.findByID(2L);
        assertNull(pet);
    }

    @Test
    void saveNewId() {
        Pet pet = Pet.builder().id(2L).build();
        Pet savedPet = petMapService.save(pet);
        assertEquals(pet.getId(), savedPet.getId());
        assertEquals(2, petMapService.findAll().size());
    }

    @Test
    void saveDuplicateId() {
        Pet pet = Pet.builder().id(1L).build();
        Pet savedPet = petMapService.save(pet);
        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void saveNullId() {
        Pet pet = Pet.builder().id(null).build();
        Pet savedPet = petMapService.save(pet);
        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());
        assertEquals(2, petMapService.findAll().size());
    }

    @Test
    void delete() {
        petMapService.delete(petMapService.findByID(PETID));
        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void deleteByCorrectId() {
        petMapService.deleteByID(PETID);
        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void deleteByWrongId() {
        petMapService.deleteByID(2L);
        assertEquals(1, petMapService.findAll().size());
    }

}