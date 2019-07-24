package com.sharmokh.sfpetclinic.controllers;

import com.sharmokh.sfpetclinic.model.Pet;
import com.sharmokh.sfpetclinic.model.Visit;
import com.sharmokh.sfpetclinic.services.PetService;
import com.sharmokh.sfpetclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class VisitController {

    private final VisitService visitService;
    private final PetService petService;

    public VisitController(VisitService visitService, PetService petService) {
        this.visitService = visitService;
        this.petService = petService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable Long petId, Model model) {
        Pet pet = petService.findByID(petId);
        model.addAttribute("pet", pet);
        Visit visit = Visit.builder().build();
        pet.getVisits().add(visit);
        visit.setPet(pet);
        return visit;
    }

    @GetMapping("/owners/*/pets/{petId}/visits/new")
    public String createNewVisit(@PathVariable Long petId, Model model) {
        return "pets/createOrUpdateVisitForm";
    }

    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processNewVisit(@Valid Visit visit, BindingResult result) {
        if (result.hasErrors()) {
            return "pets/createOrUpdateVisitForm";
        } else {
            visitService.save(visit);
            return "redirect:/owners/{ownerId}";
        }
    }

    @GetMapping("/owners/*/pets/{petId}/visits/{visitId}/edit")
    public String editExistingVisit(@PathVariable Long petId, @PathVariable Long visitId, Model model) {
        model.addAttribute("visit", visitService.findByID(visitId));
        return "pets/createOrUpdateVisitForm";
    }

    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/{visitId}/edit")
    public String processExistingVisit(@Valid Visit visit, Pet pet, BindingResult result, Model model) {
        if (result.hasErrors()) {
            visit.setPet(pet);
            model.addAttribute("visit", visit);
            return "pets/createOrUpdateVisitForm";
        } else {
            pet.getVisits().add(visit);
            visit.setPet(pet);
            visitService.save(visit);
            return "redirect:/owners/{ownerId}";
        }
    }
}
