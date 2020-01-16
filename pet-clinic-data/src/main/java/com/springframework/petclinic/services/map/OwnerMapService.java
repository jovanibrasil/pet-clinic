package com.springframework.petclinic.services.map;

import com.springframework.petclinic.model.Owner;
import com.springframework.petclinic.services.CrudService;
import com.springframework.petclinic.services.OwnerService;
import com.springframework.petclinic.services.PetService;
import com.springframework.petclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Profile({"default", "map"})
@Service
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetService petService;
    private final PetTypeService petTypeService;

    public OwnerMapService(PetService petService, PetTypeService petTypeService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        if(object != null){
            if(object.getPets() != null){
                object.getPets().forEach(pet ->{
                    if(pet.getPetType() == null) throw new RuntimeException("Pet type is required.");
                    if(pet.getPetType().getId() == null) petTypeService.save(pet.getPetType());
                    if(pet.getId() == null) petService.save(pet);
                });
            }
            return super.save(object);
        }
        return null;
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        if(lastName == null) throw new IllegalArgumentException("Argument lastname must not be null.");
        return this.findAll()
                .stream()
                .filter(owner -> lastName.equals(owner.getLastName()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        // todo - impl
        return null;
    }
}
