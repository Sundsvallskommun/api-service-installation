package se.sundsvall.installation.api.model;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanEquals;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanHashCode;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import com.google.code.beanmatchers.BeanMatchers;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
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
			hasValidBeanEquals(),
			hasValidBeanHashCode(),
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

		assertThat(searchParameters).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(searchParameters.getInstalled()).isEqualTo(installed);
		assertThat(searchParameters.getCategory()).isEqualTo(category);
		assertThat(searchParameters.getFacilityId()).isEqualTo(facilityId);
		assertThat(searchParameters.getDateFrom()).isEqualTo(dateFrom);
		assertThat(searchParameters.getPage()).isEqualTo(1);
		assertThat(searchParameters.getLimit()).isEqualTo(1);
		assertThat(searchParameters.getSortBy()).isEqualTo(List.of("asc"));
		assertThat(searchParameters.getSortDirection()).isEqualTo(Sort.Direction.ASC);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(SearchParameters.create())
			.hasAllNullFieldsOrPropertiesExcept("page", "limit", "sortDirection");

		assertThat(new SearchParameters())
			.hasAllNullFieldsOrPropertiesExcept("page", "limit", "sortDirection");
	}
}
