package se.sundsvall.installation.service.mapper;

import static generated.se.sundsvall.datawarehousereader.Category.ELECTRICITY;
import static org.assertj.core.api.Assertions.assertThat;
import static se.sundsvall.installation.TestUtil.createInstallationDetailsResponse;
import static se.sundsvall.installation.TestUtil.createSearchParameters;

import org.junit.jupiter.api.Test;

class MapperTest {

	@Test
	void toInstallationParameters() {
		final var searchParameters = createSearchParameters();

		final var installationParameters = Mapper.toInstallationParameters(searchParameters);

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
	void toInstallationsResponse() {
		final var installationDetailsResponse = createInstallationDetailsResponse();

		final var installationsResponse = Mapper.toInstallationsResponse(installationDetailsResponse);

		assertThat(installationsResponse).isNotNull();
		assertThat(installationsResponse.getInstallationDetails()).isEqualTo(installationDetailsResponse.getInstallationDetails());
	}

}
