package se.sundsvall.installation.api.model;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import se.sundsvall.dept44.models.api.paging.PagingAndSortingMetaData;

class InstallationsResponseTest {

	@Test
	void testBean() {
		assertThat(InstallationsResponse.class, allOf(
			hasValidBeanConstructor(),
			hasValidBeanToString(),
			hasValidGettersAndSetters()));
	}

	@Test
	void testBuilder() {
		final var installationDetails = List.of(new Installation());

		final var response = InstallationsResponse.builder()
			.withInstallationDetails(installationDetails)
			.withMeta(new PagingAndSortingMetaData())
			.build();

		Assertions.assertThat(response).isNotNull().hasNoNullFieldsOrProperties();
		Assertions.assertThat(response.getInstallationDetails()).isEqualTo(installationDetails);
	}

	@Test
	void testNoDirtOnCreatedBean() {
		Assertions.assertThat(InstallationsResponse.builder().build())
			.hasAllNullFieldsOrProperties();

		Assertions.assertThat(new InstallationsResponse())
			.hasAllNullFieldsOrProperties();
	}
}
