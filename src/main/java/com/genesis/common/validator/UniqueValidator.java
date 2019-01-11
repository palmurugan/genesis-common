package com.genesis.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanWrapperImpl;

import com.genesis.common.annotation.Unique;
import com.genesis.common.service.FieldValueExist;

/**
 * 
 * @author palmurugan
 *
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
		return (this.service == null) ? Boolean.TRUE
				: !this.service.fieldValueExists((idValue != null ? Long.parseLong(idValue.toString()) : null), value,
						this.fieldName);
	}

}
