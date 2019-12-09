package com.springframework.petclinic.services.springdatajpa;

import com.springframework.petclinic.model.Owner;
import com.springframework.petclinic.repositories.OwnerRepository;
import com.springframework.petclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("springdatajpa")
@Service
public class OwnerJpaService  extends  AbstractJpaService<Owner, Long, OwnerRepository>  implements OwnerService {

    public OwnerJpaService(OwnerRepository repository) {
        super(repository);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return repository.findByLastName(lastName);
    }
}
