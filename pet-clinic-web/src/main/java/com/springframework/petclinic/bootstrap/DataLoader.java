package com.springframework.petclinic.bootstrap;

import com.springframework.petclinic.model.*;
import com.springframework.petclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        if(petTypeService.findAll().isEmpty()) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = this.petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCatPetType = this.petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("michel");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("Miami");
        owner1.setTelephone("1212132323");

        Pet mikesPet = new Pet();
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setName("Rosco");
        owner1.getPets().add(mikesPet);

        this.ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("123 Brickerel");
        owner2.setCity("Miami");
        owner2.setTelephone("1212132323");

        Pet fionasPet = new Pet();
        fionasPet.setBirthDate(LocalDate.now());
        fionasPet.setPetType(savedCatPetType);
        fionasPet.setOwner(owner1);
        fionasPet.setName("Just Cat");
        owner2.getPets().add(fionasPet);

        this.ownerService.save(owner2);

        Visit fionasPetVisit = new Visit();
        fionasPetVisit.setPet(fionasPet);
        fionasPetVisit.setData(LocalDate.now());
        fionasPetVisit.setDescription("Sneezy kitty");
        this.visitService.save(fionasPetVisit);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgeon");
        this.specialtyService.save(surgery);
        Specialty radiology = new Specialty();
        radiology.setDescription("Radiologist");
        this.specialtyService.save(radiology);
        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        this.specialtyService.save(dentistry);

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialties().add(surgery);
        this.vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialties().add(radiology);
        vet2.getSpecialties().add(dentistry);
        this.vetService.save(vet2);
    }

}
