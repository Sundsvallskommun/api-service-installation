package se.sundsvall.installation.api.model;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
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

		final var metaData = new MetaData();

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
			.withMetaData(List.of(metaData))
			.build();

		assertThat(installation).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(installation.getCompany()).isEqualTo("company");
		assertThat(installation.getType()).isEqualTo("type");
		assertThat(installation.getFacilityId()).isEqualTo("facilityId");
		assertThat(installation.getPlacementId()).isEqualTo(12345);
		assertThat(installation.getCareOf()).isEqualTo("careOf");
		assertThat(installation.getStreet()).isEqualTo("street");
		assertThat(installation.getPostCode()).isEqualTo("postCode");
		assertThat(installation.getCity()).isEqualTo("city");
		assertThat(installation.getPropertyDesignation()).isEqualTo("propertyDesignation");
		assertThat(installation.getDateFrom()).isEqualTo(now);
		assertThat(installation.getDateTo()).isEqualTo(now);
		assertThat(installation.getDateLastModified()).isEqualTo(now);
		assertThat(installation.getMetaData()).isEqualTo(List.of(metaData));
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(Installation.builder().build())
			.hasAllNullFieldsOrProperties();

		assertThat(new Installation())
			.hasAllNullFieldsOrProperties();
	}
}
