package com.genesis.common.validator;

/**
 * @author PalMurugan C
 *
 * @param <E>
 */
public interface GenericValidator<E> {

    public void validate(E entity);
}
