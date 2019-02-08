package com.genesis.common.service.impl;

import com.genesis.common.mapper.GenericMapper;
import com.genesis.common.service.IGenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

/**
 * @param <E>
 * @param <K>
 * @author palmurugan
 */
public class GenericServiceImpl<E, D, K> implements IGenericService<D, K> {

    private PagingAndSortingRepository<E, K> repository;

    private GenericMapper<E, D> mapper;

    public GenericServiceImpl(PagingAndSortingRepository<E, K> repository, GenericMapper<E, D> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public D saveOrUpdate(D dto) {
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public Page<D> getAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDTO);
    }

    @Override
    public Optional<D> get(K id) {
        return repository.findById(id).map(mapper::toDTO);
    }

    @Override
    public void remove(K id) {
        repository.deleteById(id);
    }

    @Override
    public void saveOrUpdateAll(List<D> dtos) {
        dtos.forEach(dto -> repository.save(mapper.toEntity(dto)));
    }

    @Override
    public void removeAll(List<D> dtos) {
        dtos.forEach(dto -> repository.delete(mapper.toEntity(dto)));
    }
}
