package com.springframework.petclinic.services.springdatajpa;

import com.springframework.petclinic.model.Pet;
import com.springframework.petclinic.repositories.PetRepository;
import com.springframework.petclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("springdatajpa")
@Service
public class PetJpaService extends AbstractJpaService<Pet, Long, PetRepository> implements PetService {
    public PetJpaService(PetRepository repository) {
        super(repository);
    }
}
