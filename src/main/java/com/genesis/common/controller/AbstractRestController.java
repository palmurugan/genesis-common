package com.genesis.common.controller;

import com.genesis.common.service.IGenericService;
import com.genesis.common.validator.GenericValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author palmurugan
 */
public abstract class AbstractRestController<D, K> {

    private IGenericService<D, K> genericService;

    private GenericValidator<D> validator;

    public AbstractRestController(IGenericService<D, K> genericService, GenericValidator<D> validator) {
        this.genericService = genericService;
        this.validator = validator;
    }

    @RequestMapping(method = POST)
    public ResponseEntity<D> create(@Valid @RequestBody D dto) {
        validator.validate(dto);
        return new ResponseEntity<>(genericService.saveOrUpdate(dto), HttpStatus.CREATED);
    }

    @RequestMapping(method = GET)
    public ResponseEntity<Page<D>> getAll(Pageable pageable) {
        return new ResponseEntity<>(genericService.getAll(pageable), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<D> get(@PathVariable K id) {
        D resultDto = null;
        Optional<D> result = genericService.get(id);
        if(result.isPresent()){
            resultDto = result.get();
        }
        return new ResponseEntity<>(resultDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = PUT)
    public ResponseEntity<D> update(@PathVariable K id, @Valid @RequestBody D dto) {
        validator.validate(dto);
        return new ResponseEntity<>(genericService.saveOrUpdate(dto), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = DELETE)
    public ResponseEntity<String> delete(@PathVariable K id) {
        genericService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
