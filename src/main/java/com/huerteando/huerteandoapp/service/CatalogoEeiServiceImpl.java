
package com.huerteando.huerteandoapp.service;

import com.huerteando.huerteandoapp.model.CatalogoEei;
import com.huerteando.huerteandoapp.repository.CatalogoEeiRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
Implementación del servicio de catálogo EEI.
*/
@Service
public class CatalogoEeiServiceImpl implements ICatalogoEeiService {

    private final CatalogoEeiRepository catalogoEeiRepository;

    public CatalogoEeiServiceImpl(CatalogoEeiRepository catalogoEeiRepository) {
        this.catalogoEeiRepository = catalogoEeiRepository;
    }


}
