package se.sundsvall.installation.api.validation.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import jakarta.validation.ConstraintValidatorContext;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import se.sundsvall.installation.api.model.SearchParameters;

@ExtendWith(MockitoExtension.class)
class ValidPaginationConstraintValidatorTest {

	private final ValidPaginationConstraintValidator validator = new ValidPaginationConstraintValidator();

	@Mock
	private ConstraintValidatorContext context;

	@Mock
	private ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder;

	@Mock
	private ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext nodeBuilderCustomizableContext;

	@ParameterizedTest
	@ValueSource(ints = {1, 50, 100})
	void validLimitAndPage(final int variables) {
		assertThat(validator.isValid((SearchParameters) SearchParameters.create()
			.withLimit(variables).withPage(variables), context)).isTrue();
	}

	@ParameterizedTest
	@ValueSource(ints = {-5, 0})
	void validLimitNotPage(final int page) {
		stubContext();

		assertThat(validator.isValid((SearchParameters) SearchParameters.create()
			.withLimit(5).withPage(page), context)).isFalse();
	}

	@ParameterizedTest
	@ValueSource(ints = {-5, 1001, 0})
	void validPageNotLimit(final int limit) {
		stubContext();

		assertThat(validator.isValid((SearchParameters) SearchParameters.create()
			.withLimit(limit).withPage(5), context)).isFalse();
	}

	private void stubContext() {
		when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(constraintViolationBuilder);
		when(constraintViolationBuilder.addPropertyNode(anyString())).thenReturn(nodeBuilderCustomizableContext);
		when(nodeBuilderCustomizableContext.addConstraintViolation()).thenReturn(context);
	}

}
