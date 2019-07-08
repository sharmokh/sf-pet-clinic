package com.sharmokh.sfpetclinic.bootstrap;

import com.sharmokh.sfpetclinic.model.Owner;
import com.sharmokh.sfpetclinic.model.PetType;
import com.sharmokh.sfpetclinic.model.Vet;
import com.sharmokh.sfpetclinic.services.OwnerService;
import com.sharmokh.sfpetclinic.services.PetTypeService;
import com.sharmokh.sfpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Zoe");
        owner1.setLastName("Sharmokh");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiorie");
        owner2.setLastName("Kiflezghie");
        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Skyla");
        vet1.setLastName("Sharmokh");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Donovan");
        vet2.setLastName("McGrew");
        vetService.save(vet2);

        System.out.println("Loaded Vets...");

    }

}
