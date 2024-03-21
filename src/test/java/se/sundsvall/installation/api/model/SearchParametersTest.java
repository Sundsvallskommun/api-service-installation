package se.sundsvall.installation.api.model;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import com.google.code.beanmatchers.BeanMatchers;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SearchParametersTest {

	@BeforeAll
	static void setup() {
		BeanMatchers.registerValueGenerator(() -> LocalDate.now().plusDays(new Random().nextInt()), LocalDate.class);
	}

	@Test
	void testBean() {
		assertThat(SearchParameters.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters()));
	}

	@Test
	void testBuilder() {
		final var installed = true;
		final var category = "ELECTRICITY";
		final var facilityId = "facilityId";
		final var dateFrom = LocalDate.MIN;


		final SearchParameters searchParameters = SearchParameters.builder()
			.withInstalled(installed)
			.withCategory(category)
			.withFacilityId(facilityId)
			.withDateFrom(dateFrom)
			.withPage(0)
			.withSize(1)
			.withSort(List.of("asc"))
			.build();

		Assertions.assertThat(searchParameters).isNotNull().hasNoNullFieldsOrProperties();
		Assertions.assertThat(searchParameters.getInstalled()).isEqualTo(installed);
		Assertions.assertThat(searchParameters.getCategory()).isEqualTo(category);
		Assertions.assertThat(searchParameters.getFacilityId()).isEqualTo(facilityId);
		Assertions.assertThat(searchParameters.getDateFrom()).isEqualTo(dateFrom);
		Assertions.assertThat(searchParameters.getPage()).isEqualTo(0);
		Assertions.assertThat(searchParameters.getSize()).isEqualTo(1);
		Assertions.assertThat(searchParameters.getSort()).isEqualTo(List.of("asc"));
	}

	@Test
	void testNoDirtOnCreatedBean() {
		Assertions.assertThat(SearchParameters.builder().build())
			.hasAllNullFieldsOrProperties();

		Assertions.assertThat(new SearchParameters())
			.hasAllNullFieldsOrProperties();
	}

}
