package se.sundsvall.installation.api.model;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import generated.se.sundsvall.datawarehousereader.PagingAndSortingMetaData;

public class MetaDataEmbeddable {

	@Test
	void testBean() {
		assertThat(InstallationsResponse.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters()));
	}

	@Test
	void testBuilder() {
		final var installationDetails = List.of(new InstallationDetails());

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
