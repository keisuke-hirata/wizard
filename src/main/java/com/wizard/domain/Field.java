package com.wizard.domain;

import java.util.Optional;

public enum Field {
	FOREST,
	CAVE;
	
    public static Optional<Field> lowerValueOf(String lowerName) {
    	Field field = valueOf(lowerName.toUpperCase());
    	return field != null ? Optional.of(field) : Optional.empty();
    }
}
