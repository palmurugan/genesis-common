package com.genesis.common.validator;

import com.genesis.common.annotation.Unique;
import com.genesis.common.service.FieldValueExist;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author palmurugan
 */
@Component
public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    @Autowired
    private ApplicationContext applicationContext;

    private FieldValueExist service;

    private String fieldName;

    @Override
    public void initialize(Unique unique) {
        Class<? extends FieldValueExist> clazz = unique.service();
        String serviceQualifier = unique.serviceQualifier();
        this.fieldName = unique.fieldName();
        if (applicationContext != null) {
            this.service = (serviceQualifier.isEmpty() ? applicationContext.getBean(clazz)
                    : applicationContext.getBean(serviceQualifier, clazz));
        }
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        Object idValue = new BeanWrapperImpl(obj).getPropertyValue("id");
        Object value = new BeanWrapperImpl(obj).getPropertyValue(this.fieldName);
        Long id = idValue != null ? Long.parseLong(idValue.toString()) : null;

        boolean result = this.service == null ? Boolean.TRUE : Boolean.FALSE;
        return (!result) ? !this.service.fieldValueExists((id), value, this.fieldName) : Boolean.TRUE;
    }
}
