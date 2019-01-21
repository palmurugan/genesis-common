package com.genesis.common.validator;

/**
 * @author PalMurugan C
 */
public class ValidatorUtil {

    public static final Validation<String> notNullString = GenericValidation.from(s -> s != null);

    public static final Validation<String> notEmptyString = GenericValidation.from(s -> !s.isEmpty());
}
