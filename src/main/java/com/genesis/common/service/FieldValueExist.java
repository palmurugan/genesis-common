package com.genesis.common.service;

/**
 * 
 * @author palmurugan
 *
 */
public interface FieldValueExist {

	/**
	 * 
	 * @param value
	 * @param fieldName
	 * @return
	 * @throws UnsupportedOperationException
	 */
	boolean fieldValueExists(Long id, Object value, String fieldName);
}
