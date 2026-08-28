package se.sundsvall.installation.service.mapper;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort.Direction;

import static generated.se.sundsvall.datawarehousereader.Category.ELECTRICITY;
import static org.assertj.core.api.Assertions.assertThat;
import static se.sundsvall.installation.TestUtil.createInstallationDetails;
import static se.sundsvall.installation.TestUtil.createInstallationDetailsResponse;
import static se.sundsvall.installation.TestUtil.createInstallationMetaData;
import static se.sundsvall.installation.TestUtil.createPagingAndSortingMetaData;
import static se.sundsvall.installation.service.mapper.Mapper.toPagingAndSortingMetaData;

class MapperTest {

	@Test
	void toCategory() {
		assertThat(Mapper.toCategory("ELECTRICITY")).isEqualTo(ELECTRICITY);
	}

	@Test
	void toCategoryFromNull() {
		assertThat(Mapper.toCategory(null)).isNull();
	}

	@Test
	void toDirection() {
		assertThat(Mapper.toDirection(Direction.DESC)).isEqualTo(generated.se.sundsvall.datawarehousereader.Direction.DESC);
	}

	@Test
	void toDirectionFromNull() {
		assertThat(Mapper.toDirection(null)).isNull();
	}

	@Test
	void toInstallationsResponse() {
		final var installationDetailsResponse = createInstallationDetailsResponse();

		final var installationsResponse = Mapper.toInstallationsResponse(installationDetailsResponse);

		assertThat(installationsResponse.getInstallationDetails()).hasSize(1);
		assertThat(installationsResponse.getMeta()).isNull();
		assertThat(installationDetailsResponse.getMeta()).isNull();
	}

	@Test
	void toInstallationsResponseFromNull() {
		assertThat(Mapper.toInstallationsResponse(null)).isNull();
	}

	@Test
	void toInstallationDetails() {
		final var dwrDetails = List.of(createInstallationDetails());

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
		final var dwrDetails = createInstallationDetails();

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
	void toMetaDataTest() {
		final var installationMetaData = createInstallationMetaData();

		final var meta = Mapper.toMetaData(installationMetaData);

		assertThat(meta.getKey()).isEqualTo(installationMetaData.getKey());
		assertThat(meta.getDisplayName()).isEqualTo(installationMetaData.getDisplayName());
		assertThat(meta.getType()).isEqualTo(installationMetaData.getType());
		assertThat(meta.getValue()).isEqualTo(installationMetaData.getValue());
	}

	@Test
	void toMetaDataFromNull() {
		assertThat(Mapper.toMetaData(null)).isNull();
	}

	@Test
	void toPagingAndSortingMetaDataTest() {

		final var pagingAndSorting = createPagingAndSortingMetaData();

		final var result = toPagingAndSortingMetaData(pagingAndSorting);

		assertThat(result.getPage()).isEqualTo(pagingAndSorting.getPage());
		assertThat(result.getLimit()).isEqualTo(pagingAndSorting.getLimit());
		assertThat(result.getCount()).isEqualTo(pagingAndSorting.getCount());
		assertThat(result.getTotalRecords()).isEqualTo(pagingAndSorting.getTotalRecords());
		assertThat(result.getTotalPages()).isEqualTo(pagingAndSorting.getTotalPages());
		assertThat(result.getSortBy()).isEqualTo(pagingAndSorting.getSortBy());
	}

}
