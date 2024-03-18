package se.sundsvall.installation.api.validation.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import se.sundsvall.installation.api.model.SearchParameters;
import se.sundsvall.installation.api.validation.ValidPagination;

public class ValidPaginationConstraintValidator implements ConstraintValidator<ValidPagination, SearchParameters> {

	@Override
	public boolean isValid(final SearchParameters parameters, final ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();

		if (parameters.getLimit() <= 0 || parameters.getLimit() > 1000) {
			context.buildConstraintViolationWithTemplate("limit must be between 1 and 1000")
				.addPropertyNode("limit")
				.addConstraintViolation();
			return false;
		}
		if (parameters.getPage() <= 0) {
			context.buildConstraintViolationWithTemplate("page must be greater or equal to 1")
				.addPropertyNode("page")
				.addConstraintViolation();
			return false;
		}

		return true;
	}
}
