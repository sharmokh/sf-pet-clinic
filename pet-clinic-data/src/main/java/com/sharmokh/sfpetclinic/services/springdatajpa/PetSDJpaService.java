package com.sharmokh.sfpetclinic.services.springdatajpa;

import com.sharmokh.sfpetclinic.model.Pet;
import com.sharmokh.sfpetclinic.repositories.PetRepository;
import com.sharmokh.sfpetclinic.services.PetService;

import java.util.HashSet;
import java.util.Set;

public class PetSDJpaService implements PetService {

    private PetRepository petRepository;

    public PetSDJpaService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<>();
        petRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet findByID(Long aLong) {
        return petRepository.findById(aLong).orElse(null);
    }

    @Override
    public Pet save(Pet object) {
        return petRepository.save(object);
    }

    @Override
    public void delete(Pet object) {
        petRepository.delete(object);
    }

    @Override
    public void deleteByID(Long aLong) {
        petRepository.deleteById(aLong);
    }
}
