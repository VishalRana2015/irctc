package com.zepto.irctc.service;

import com.zepto.irctc.dto.CoacheToCreate;
import com.zepto.irctc.exception.InvalidCoacheTypeException;
import com.zepto.irctc.model.Coache;
import com.zepto.irctc.repository.CoacheRepository;
import enums.CoacheType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class CoacheService {
    @Autowired
    CoacheRepository coacheRepository;

    public Coache createCoache(CoacheToCreate coacheData) throws InvalidCoacheTypeException{

        // check whether the type is valid?
        CoacheType type = null;
        for (CoacheType t : CoacheType.values()){
            if ( t.toString().toLowerCase(Locale.ROOT).equals(coacheData.getType().toLowerCase(Locale.ROOT))){
                type = t;
                break;
            }
        }
        if ( type == null){
            throw new InvalidCoacheTypeException(InvalidCoacheTypeException.DEFAULT_MSG + " : " + coacheData.getType());
        }
        Coache coache = new Coache(type);
        coache.setName(coacheData.getName());
        coache = coacheRepository.save(coache);
        return coache;
    }
}
