package se.sundsvall.installation.api.model;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
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

class InstallationTest {

	@BeforeAll
	static void setup() {
		BeanMatchers.registerValueGenerator(() -> LocalDate.now().plusDays(new Random().nextInt()), LocalDate.class);
	}

	@Test
	void testBean() {
		assertThat(Installation.class, allOf(
			hasValidBeanConstructor(),
			hasValidBeanToString(),
			hasValidGettersAndSetters()));
	}

	@Test
	void testBuilder() {
		final var now = LocalDate.now();

		final var metaData = new MetaDataEmbeddable();

		final var installation = Installation.builder()
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
			.withMetaDataEmbeddables(List.of(metaData))
			.build();

		Assertions.assertThat(installation).isNotNull().hasNoNullFieldsOrProperties();
		Assertions.assertThat(installation.getCompany()).isEqualTo("company");
		Assertions.assertThat(installation.getType()).isEqualTo("type");
		Assertions.assertThat(installation.getFacilityId()).isEqualTo("facilityId");
		Assertions.assertThat(installation.getPlacementId()).isEqualTo(12345);
		Assertions.assertThat(installation.getCareOf()).isEqualTo("careOf");
		Assertions.assertThat(installation.getStreet()).isEqualTo("street");
		Assertions.assertThat(installation.getPostCode()).isEqualTo("postCode");
		Assertions.assertThat(installation.getCity()).isEqualTo("city");
		Assertions.assertThat(installation.getPropertyDesignation()).isEqualTo("propertyDesignation");
		Assertions.assertThat(installation.getDateFrom()).isEqualTo(now);
		Assertions.assertThat(installation.getDateTo()).isEqualTo(now);
		Assertions.assertThat(installation.getDateLastModified()).isEqualTo(now);
		Assertions.assertThat(installation.getMetaDataEmbeddables()).isEqualTo(List.of(metaData));
	}

	@Test
	void testNoDirtOnCreatedBean() {
		Assertions.assertThat(Installation.builder().build())
			.hasAllNullFieldsOrProperties();

		Assertions.assertThat(new Installation())
			.hasAllNullFieldsOrProperties();
	}
}
