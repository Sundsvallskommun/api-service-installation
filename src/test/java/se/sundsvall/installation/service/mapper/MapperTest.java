package se.sundsvall.installation.service.mapper;

import static generated.se.sundsvall.datawarehousereader.Category.ELECTRICITY;
import static org.assertj.core.api.Assertions.assertThat;
import static se.sundsvall.installation.TestUtil.createDWRInstallationDetails;
import static se.sundsvall.installation.TestUtil.createDWRInstallationMetaDataEmbeddable;
import static se.sundsvall.installation.TestUtil.createInstallationDetailsResponse;
import static se.sundsvall.installation.TestUtil.createSearchParameters;

import java.util.List;

import org.junit.jupiter.api.Test;

import se.sundsvall.installation.api.model.SearchParameters;

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
	}

	@Test
	void toInstallationParametersFromNull() {
		assertThat(Mapper.toInstallationParameters(null)).isNull();
	}

	@Test
	void toInstallationParametersFromEmptyParameters() {
		final var bean = Mapper.toInstallationParameters(SearchParameters.create());

		assertThat(bean).hasAllNullFieldsOrPropertiesExcept("page", "limit");
	}

	@Test
	void toInstallationsResponse() {
		final var installationDetailsResponse = createInstallationDetailsResponse();

		final var installationsResponse = Mapper.toInstallationsResponse(installationDetailsResponse);

		assertThat(installationsResponse.getInstallationDetails()).hasSize(1);
		assertThat(installationsResponse.getMeta()).isEqualTo(installationDetailsResponse.getMeta());
	}

	@Test
	void toInstallationsResponseFromNull() {
		assertThat(Mapper.toInstallationsResponse(null)).isNull();
	}

	@Test
	void toInstallationDetails() {
		final var dwrDetails = List.of(createDWRInstallationDetails());

		final var installationDetails = Mapper.toInstallationDetailsList(dwrDetails);

		assertThat(installationDetails).hasSize(1);
	}

	@Test
	void toInstallationDetailsFromEmptyList() {
		final var installationDetails = Mapper.toInstallationDetailsList(List.of());

		assertThat(installationDetails).isEmpty();
	}

	@Test
	void toInstallationDetail() {
		final var dwrDetails = createDWRInstallationDetails();

		final var installationDetail = Mapper.toInstallationDetails(dwrDetails);

		assertThat(installationDetail.getCompany()).isEqualTo(dwrDetails.getCompany());
		assertThat(installationDetail.getCareOf()).isEqualTo(dwrDetails.getCareOf());
		assertThat(installationDetail.getCity()).isEqualTo(dwrDetails.getCity());
		assertThat(installationDetail.getStreet()).isEqualTo(dwrDetails.getStreet());
		assertThat(installationDetail.getDateFrom()).isEqualTo(dwrDetails.getDateFrom());
		assertThat(installationDetail.getDateTo()).isEqualTo(dwrDetails.getDateTo());
		assertThat(installationDetail.getFacilityId()).isEqualTo(dwrDetails.getFacilityId());
		assertThat(installationDetail.getType()).isEqualTo(dwrDetails.getType());
		assertThat(installationDetail.getDateLastModified()).isEqualTo(dwrDetails.getDateLastModified());
		assertThat(installationDetail.getPropertyDesignation()).isEqualTo(dwrDetails.getPropertyDesignation());
		assertThat(installationDetail.getPostCode()).isEqualTo(dwrDetails.getPostCode());
		assertThat(installationDetail.getPlacementId()).isEqualTo(dwrDetails.getPlacementId());
	}

	@Test
	void toInstallationDetailFromNull() {
		assertThat(Mapper.toInstallationDetails(null)).isNull();
	}

	@Test
	void toMetaDataEmbeddableTest() {
		final var dwrMeta = createDWRInstallationMetaDataEmbeddable();

		System.out.println(dwrMeta);

		final var metaData = Mapper.toMetaDataEmbeddable(dwrMeta);

		System.out.println(metaData);
	}


}
