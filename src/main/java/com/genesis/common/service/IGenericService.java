package com.genesis.common.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 
 * @author palmurugan
 *
 * @param <E>
 * @param <K>
 */
public interface IGenericService<D, K> {

    public D saveOrUpdate(D dto);

	public void saveOrUpdateAll(List<D> dtos);

    public Page<D> getAll(Pageable pageable);

    public Optional<D> get(K id);

    public void remove(K id);

	public void removeAll(List<D> entities);
}
