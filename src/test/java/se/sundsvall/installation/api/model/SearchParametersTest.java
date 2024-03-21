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
import org.springframework.data.domain.Sort;

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


		final SearchParameters searchParameters = SearchParameters.create()
			.withInstalled(installed)
			.withCategory(category)
			.withFacilityId(facilityId)
			.withDateFrom(dateFrom)
			.withPage(1)
			.withLimit(1)
			.withSortBy(List.of("asc"))
			.withSortDirection(Sort.Direction.ASC);

		Assertions.assertThat(searchParameters).isNotNull().hasNoNullFieldsOrProperties();
		Assertions.assertThat(searchParameters.getInstalled()).isEqualTo(installed);
		Assertions.assertThat(searchParameters.getCategory()).isEqualTo(category);
		Assertions.assertThat(searchParameters.getFacilityId()).isEqualTo(facilityId);
		Assertions.assertThat(searchParameters.getDateFrom()).isEqualTo(dateFrom);
		Assertions.assertThat(searchParameters.getPage()).isEqualTo(1);
		Assertions.assertThat(searchParameters.getLimit()).isEqualTo(1);
		Assertions.assertThat(searchParameters.getSortBy()).isEqualTo(List.of("asc"));
		Assertions.assertThat(searchParameters.getSortDirection()).isEqualTo(Sort.Direction.ASC);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		Assertions.assertThat(SearchParameters.create())
			.hasAllNullFieldsOrPropertiesExcept("page", "limit", "sortDirection");

		Assertions.assertThat(new SearchParameters())
			.hasAllNullFieldsOrPropertiesExcept("page", "limit", "sortDirection");
	}

}
