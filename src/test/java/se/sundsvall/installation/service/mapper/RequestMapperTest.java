package se.sundsvall.installation.service.mapper;

import static generated.se.sundsvall.datawarehousereader.Category.ELECTRICITY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static se.sundsvall.installation.TestUtil.createSearchParameters;

import org.junit.jupiter.api.Test;

class RequestMapperTest {

	@Test
	void toInstallationParameters() {
		final var searchParameters = createSearchParameters();

		final var installationParameters = RequestMapper.toInstallationParameters(searchParameters);

		assertThat(installationParameters.getInstalled()).isFalse();
		assertThat(installationParameters.getDateFrom()).isEqualTo(searchParameters.getDateFrom());
		assertThat(installationParameters.getCategory()).isEqualTo(ELECTRICITY);
		assertThat(installationParameters.getFacilityId()).isEqualTo(searchParameters.getFacilityId());
		assertThat(installationParameters.getPage()).isEqualTo(searchParameters.getPage());
		assertThat(installationParameters.getLimit()).isEqualTo(searchParameters.getLimit());
		assertThat(installationParameters.getSortBy()).isEqualTo(searchParameters.getSortBy());
		assertThat(installationParameters.getSortDirection()).isEqualTo(searchParameters.getSortDirection());
	}

	@Test
	void toInstallationParametersInvalidCategory() {
		final var value = "INVALID";
		final var searchParameters = createSearchParameters(p -> p.setCategory(value));

		assertThatThrownBy(() -> RequestMapper.toInstallationParameters(searchParameters))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("Unexpected value '" + value + "'");
	}

}
