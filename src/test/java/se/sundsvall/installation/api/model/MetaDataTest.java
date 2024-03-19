package se.sundsvall.installation.api.model;

import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanConstructor;
import static com.google.code.beanmatchers.BeanMatchers.hasValidBeanToString;
import static com.google.code.beanmatchers.BeanMatchers.hasValidGettersAndSetters;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;

import org.assertj.core.api.Assertions;
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

		Assertions.assertThat(metaData).isNotNull().hasNoNullFieldsOrProperties();
		Assertions.assertThat(metaData.getKey()).isEqualTo("key");
		Assertions.assertThat(metaData.getValue()).isEqualTo("value");
		Assertions.assertThat(metaData.getCompany()).isEqualTo("company");
		Assertions.assertThat(metaData.getType()).isEqualTo("type");
		Assertions.assertThat(metaData.getDisplayName()).isEqualTo("displayName");
	}

	@Test
	void testNoDirtOnCreatedBean() {
		Assertions.assertThat(MetaData.builder().build())
			.hasAllNullFieldsOrProperties();

		Assertions.assertThat(new MetaData())
			.hasAllNullFieldsOrProperties();
	}
}
