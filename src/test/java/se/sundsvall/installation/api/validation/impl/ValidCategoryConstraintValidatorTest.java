package se.sundsvall.installation.api.validation.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class ValidCategoryConstraintValidatorTest {

	private final ValidCategoryConstraintValidator validator = new ValidCategoryConstraintValidator();

	@ParameterizedTest
	@ValueSource(strings = {"ELECTRICITY", "WASTE_MANAGEMENT", "ELECTRICITY_TRADE", "DISTRICT_COOLING", "DISTRICT_HEATING"})
	@NullSource
	void testValidCategories(final String category) {
		assertThat(validator.isValid(category, null)).isTrue();
	}

	@ParameterizedTest
	@ValueSource(strings = {"00:00:0a:bb:28::", "00000ABB28FCFC", "00000ABB28FH", "00000ABB28F", " ", "."})
	@EmptySource
	void testInvalidCategories(final String category) {
		assertThat(validator.isValid(category, null)).isFalse();
	}
}
