package se.sundsvall.installation.api.model;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class InstallationDetailsTest {

	@Test
	void testBean() {
		assertThat(InstallationDetails.class, allOf(
			hasValidBeanConstructor(),
			hasValidGettersAndSetters()));
	}

	@Test
	void testBuilder() {
		final var now = LocalDate.now();

		final var installationDetails = InstallationDetails.builder()
			.withCompany("company")
			.withType("type")
			.withFacilityId("facilityId")
			.withPlacementId(12345)
			.withCareOf("careOf")
			.withStreet("street")
			.withPostCode("postCode")
			.withCity("city")
			.withPropertyDesignation("propertyDesignation")
			.withDateFrom(now)
			.withDateTo(now)
			.withDateLastModified(now)
			.withMetaData(List.of(new MetaDataEmbeddable()))
			.build();

		Assertions.assertThat(installationDetails).isNotNull().hasNoNullFieldsOrProperties();
		Assertions.assertThat(installationDetails.getCompany()).isEqualTo("company");
		Assertions.assertThat(installationDetails.getType()).isEqualTo("type");
		Assertions.assertThat(installationDetails.getFacilityId()).isEqualTo("facilityId");
		Assertions.assertThat(installationDetails.getPlacementId()).isEqualTo(12345);
		Assertions.assertThat(installationDetails.getCareOf()).isEqualTo("careOf");
		Assertions.assertThat(installationDetails.getStreet()).isEqualTo("street");
		Assertions.assertThat(installationDetails.getPostCode()).isEqualTo("postCode");
		Assertions.assertThat(installationDetails.getCity()).isEqualTo("city");
		Assertions.assertThat(installationDetails.getPropertyDesignation()).isEqualTo("propertyDesignation");
		Assertions.assertThat(installationDetails.getDateFrom()).isEqualTo(now);
		Assertions.assertThat(installationDetails.getDateTo()).isEqualTo(now);
		Assertions.assertThat(installationDetails.getDateLastModified()).isEqualTo(now);
		Assertions.assertThat(installationDetails.getMetaData()).isEqualTo(List.of(new MetaDataEmbeddable()));
	}

	@Test
	void testNoDirtOnCreatedBean() {
		Assertions.assertThat(InstallationDetails.builder().build())
			.hasAllNullFieldsOrProperties();

		Assertions.assertThat(new InstallationDetails())
			.hasAllNullFieldsOrProperties();
	}
}
