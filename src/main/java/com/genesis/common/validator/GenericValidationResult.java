package com.genesis.common.validator;

import java.util.Optional;

/**
 * @author PalMurugan C
 */
public class GenericValidationResult {

    private boolean valid;

    private GenericValidationResult(boolean valid) {
        this.valid = valid;
    }

    public static GenericValidationResult ok() {
        return new GenericValidationResult(true);
    }

    public static GenericValidationResult fail() {
        return new GenericValidationResult(false);
    }

    public Optional<String> getFieldErrorIfInValid(String message) {
        return this.valid ? Optional.empty() : Optional.of(message);
    }

    public boolean isValid() {
        return valid;
    }

}
