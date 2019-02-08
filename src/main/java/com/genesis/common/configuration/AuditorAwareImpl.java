package com.genesis.common.configuration;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

/**
 * 
 * @author PalMurugan C
 *
 */

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Admin");
    }
}
