package com.genesis.common.mapper;

import java.util.List;

/**
 * 
 * @author palmuruganc
 *
 */
public interface GenericMapper<E, D> {

	D toDTO(E entity);

	E toEntity(D dto);
	
	List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> entityList);
    
}
