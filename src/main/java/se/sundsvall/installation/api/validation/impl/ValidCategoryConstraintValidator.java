package se.sundsvall.installation.api.validation.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;
import se.sundsvall.installation.api.validation.ValidCategory;

public class ValidCategoryConstraintValidator implements ConstraintValidator<ValidCategory, String> {

	private static final List<String> VALID_CATEGORIES = List.of("ELECTRICITY", "WASTE_MANAGEMENT", "ELECTRICITY_TRADE", "DISTRICT_COOLING", "DISTRICT_HEATING");

	@Override
	public boolean isValid(final String category, final ConstraintValidatorContext context) {
		if (category == null) {
			return true;
		}
		return VALID_CATEGORIES.contains(category.toUpperCase());
	}
}
