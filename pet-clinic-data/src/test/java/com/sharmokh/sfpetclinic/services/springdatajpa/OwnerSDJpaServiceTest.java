package com.sharmokh.sfpetclinic.services.springdatajpa;

import com.sharmokh.sfpetclinic.model.Owner;
import com.sharmokh.sfpetclinic.repositories.OwnerRepository;
import com.sharmokh.sfpetclinic.repositories.PetRepository;
import com.sharmokh.sfpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    final String LAST_NAME = "Sharmokh";
    Owner owner;
    Long ownerID;

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService service;

    @BeforeEach
    void setUp() {
        owner = Owner.builder().lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(owner);
        Owner sharmokh = service.findByLastName(LAST_NAME);
        assertEquals(LAST_NAME, sharmokh.getLastName());
        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        Set<Owner> buildOwners = new HashSet<>();
        buildOwners.add(owner);
        buildOwners.add(Owner.builder().firstName("Jack").build());
        when(ownerRepository.findAll()).thenReturn(buildOwners);
        Set<Owner> findOwners = service.findAll();
        assertNotNull(findOwners);
        assertEquals(2, findOwners.size());

    }

    @Test
    void findByID() {
        owner.setId(1L);
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(owner));
        Owner owner = service.findByID(1L);
        assertNotNull(owner);
    }

    @Test
    void save() {
        when(ownerRepository.save(any())).thenReturn(owner);
        Owner savedOwner = service.save(owner);
        assertNotNull(savedOwner);
        assertEquals(LAST_NAME, savedOwner.getLastName());
        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(owner);
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteByID() {
        service.deleteByID(1L);
        verify(ownerRepository).deleteById(anyLong());
    }
}