package com.sharmokh.sfpetclinic.repositories;

import com.sharmokh.sfpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
