package com.genesis.common.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import javax.validation.Valid;

import com.genesis.common.validator.GenericValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.genesis.common.service.IGenericService;

/**
 * 
 * @author palmurugan
 *
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
	public ResponseEntity<Page<D>> getAllPartyType(Pageable pageable) {
		return new ResponseEntity<Page<D>>(genericService.getAll(pageable), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = GET)
	public ResponseEntity<D> getPartyType(@PathVariable K id) {
		return new ResponseEntity<D>(genericService.get(id).get(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = PUT)
	public ResponseEntity<D> updatePartyType(@PathVariable K id, @Valid @RequestBody D dto) {
		validator.validate(dto);
		return new ResponseEntity<D>(genericService.saveOrUpdate(dto), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = DELETE)
	public ResponseEntity<String> deletePartyType(@PathVariable K id) {
		genericService.remove(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
