package com.sharmokh.sfpetclinic.services.map;

import com.sharmokh.sfpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    final String FIRST_NAME = "Steve";
    final String LAST_NAME = "Sharmokh";
    Long ownerID;



    @BeforeEach
    void setUp() {
        Owner owner = Owner.builder().firstName(FIRST_NAME).lastName(LAST_NAME).build();
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        ownerMapService.save(owner);
        ownerID = owner.getId();
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();
        assertEquals(1, ownerSet.size());
    }

    @Test
    void findByID() {
        Owner owner = ownerMapService.findByID(ownerID);
        assertEquals(ownerID, owner.getId());
    }

    @Test
    void save() {
        Owner owner = ownerMapService.save(Owner.builder().firstName("Donovan").build());
        assertNotNull(owner);
        assertNotNull(owner.getId());
    }

    @Test
    void delete() {
        Owner owner = ownerMapService.findByID(ownerID);
        ownerMapService.delete(owner);
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteByID() {
        ownerMapService.deleteByID(ownerID);
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerMapService.findByLastName(LAST_NAME);
        assertEquals(LAST_NAME, owner.getLastName());
    }
}