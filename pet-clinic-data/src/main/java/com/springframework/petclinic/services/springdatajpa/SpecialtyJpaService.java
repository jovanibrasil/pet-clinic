package com.springframework.petclinic.services.springdatajpa;

import com.springframework.petclinic.model.Specialty;
import com.springframework.petclinic.repositories.SpecialtyRepository;
import com.springframework.petclinic.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("springdatajpa")
@Service
public class SpecialtyJpaService extends AbstractJpaService<Specialty, Long, SpecialtyRepository> implements SpecialtyService {
    public SpecialtyJpaService(SpecialtyRepository repository) {
        super(repository);
    }
}
