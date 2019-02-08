package com.genesis.common.validator;

import java.util.Objects;

/**
 * @author PalMurugan C
 */
public class ValidatorUtil {

    public static final Validation<String> notNullString = GenericValidation.from(Objects::nonNull);
    public static final Validation<String> notEmptyString = GenericValidation.from(s -> !s.isEmpty());

    private ValidatorUtil() {
        throw new IllegalStateException("Utility class");
    }
}
