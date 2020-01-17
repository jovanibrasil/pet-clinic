package com.springframework.petclinic.formatters;

import com.springframework.petclinic.model.PetType;
import com.springframework.petclinic.services.PetService;
import com.springframework.petclinic.services.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetService petService;
    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetService petService, PetTypeService petTypeService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        Collection<PetType> petTypes = this.petTypeService.findAll();
        for (PetType petType : petTypes) {
            if(petType.getName().equals(text)){
                return petType;
            }
        }
        throw new ParseException("PetType not found :" + text, 0);
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }
}
