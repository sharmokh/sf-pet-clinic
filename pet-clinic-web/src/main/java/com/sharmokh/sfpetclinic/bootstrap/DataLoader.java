package com.sharmokh.sfpetclinic.bootstrap;

import com.sharmokh.sfpetclinic.model.*;
import com.sharmokh.sfpetclinic.services.OwnerService;
import com.sharmokh.sfpetclinic.services.PetTypeService;
import com.sharmokh.sfpetclinic.services.SpecialtyService;
import com.sharmokh.sfpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {

        // loads data if no data is found using PetType == 0
        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }

    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Zoe");
        owner1.setLastName("Sharmokh");
        owner1.setAddress("10 Northgate St");
        owner1.setAddress("New York");
        owner1.setTelephone("321-252-2492");
        Pet zoePet = new Pet();
        zoePet.setPetType(savedCatType);
        zoePet.setOwner(owner1);
        zoePet.setBirthDate(LocalDate.now());
        zoePet.setName("Princess");
        owner1.getPets().add(zoePet);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiorie");
        owner2.setLastName("Kiflezghie");
        owner2.setAddress("11 6th Ave");
        owner2.setAddress("New York");
        owner2.setTelephone("321-252-2492");
        Pet fioriePet = new Pet();
        fioriePet.setPetType(savedDogType);
        fioriePet.setOwner(owner2);
        fioriePet.setBirthDate(LocalDate.now());
        fioriePet.setName("Rocket");
        owner2.getPets().add(fioriePet);
        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Skyla");
        vet1.setLastName("Sharmokh");
        vet1.getSpecialties().add(savedDentistry);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Donovan");
        vet2.setLastName("McGrew");
        vet2.getSpecialties().add(savedRadiology);
        vet2.getSpecialties().add(savedSurgery);
        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }

}
