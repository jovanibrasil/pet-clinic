package com.springframework.petclinic.services.springdatajpa;

import com.springframework.petclinic.model.Visit;
import com.springframework.petclinic.repositories.VisitRepository;
import com.springframework.petclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("jpa")
@Service
public class VisitJpaService extends  AbstractJpaService<Visit, Long, VisitRepository> implements VisitService {

    public VisitJpaService(VisitRepository repository) {
        super(repository);
    }
}
