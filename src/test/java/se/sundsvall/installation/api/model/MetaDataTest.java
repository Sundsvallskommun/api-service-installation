package se.sundsvall.installation.api.model;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

class MetaDataTest {

	@Test
	void testBean() {
		assertThat(MetaData.class, allOf(
			hasValidBeanConstructor(),
			hasValidBeanToString(),
			hasValidGettersAndSetters()));
	}

	@Test
	void testBuilder() {
		final var metaData = MetaData.builder()
			.withKey("key")
			.withValue("value")
			.withCompany("company")
			.withType("type")
			.withDisplayName("displayName")
			.build();

		assertThat(metaData).isNotNull().hasNoNullFieldsOrProperties();
		assertThat(metaData.getKey()).isEqualTo("key");
		assertThat(metaData.getValue()).isEqualTo("value");
		assertThat(metaData.getCompany()).isEqualTo("company");
		assertThat(metaData.getType()).isEqualTo("type");
		assertThat(metaData.getDisplayName()).isEqualTo("displayName");
	}

	@Test
	void testNoDirtOnCreatedBean() {
		assertThat(MetaData.builder().build())
			.hasAllNullFieldsOrProperties();

		assertThat(new MetaData())
			.hasAllNullFieldsOrProperties();
	}
}
