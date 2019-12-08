package com.springframework.petclinic.services.springdatajpa;

import com.springframework.petclinic.model.PetType;
import com.springframework.petclinic.repositories.PetTypeRepository;
import com.springframework.petclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("jpa")
@Service
public class PetTypeJpaService extends AbstractJpaService<PetType, Long, PetTypeRepository> implements PetTypeService {

    public PetTypeJpaService(PetTypeRepository repository) {
        super(repository);
    }
}
