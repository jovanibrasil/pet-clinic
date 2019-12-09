package com.springframework.petclinic.services.springdatajpa;

import com.springframework.petclinic.model.Vet;
import com.springframework.petclinic.repositories.VetRepository;
import com.springframework.petclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("springdatajpa")
@Service
public class VetJpaService extends AbstractJpaService<Vet, Long, VetRepository> implements VetService {

    public VetJpaService(VetRepository repository) {
        super(repository);
    }

}
