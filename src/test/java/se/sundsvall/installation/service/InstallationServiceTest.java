package se.sundsvall.installation.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.sundsvall.installation.integration.datawarehousereader.DataWarehouseReaderClient;

import static generated.se.sundsvall.datawarehousereader.Category.ELECTRICITY;
import static generated.se.sundsvall.datawarehousereader.Direction.ASC;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static se.sundsvall.installation.TestUtil.createSearchParameters;

@ExtendWith(MockitoExtension.class)
class InstallationServiceTest {

	@Mock
	private DataWarehouseReaderClient dataWarehouseReaderClient;

	@InjectMocks
	private InstallationService installationService;

	@Test
	void getInstallations() {

		final var municipalityId = "municipalityId";
		final var searchParameters = createSearchParameters();

		installationService.getInstallations(municipalityId, searchParameters);

		verify(dataWarehouseReaderClient).getInstallationDetails(
			municipalityId,
			searchParameters.getInstalled(),
			searchParameters.getDateFrom(),
			ELECTRICITY,
			searchParameters.getFacilityId(),
			searchParameters.getSortBy(),
			ASC,
			searchParameters.getPage(),
			searchParameters.getLimit());

		verifyNoMoreInteractions(dataWarehouseReaderClient);
	}

	@Test
	void getInstallationsWithNullSearchParameters() {

		final var municipalityId = "municipalityId";

		installationService.getInstallations(municipalityId, null);

		verify(dataWarehouseReaderClient).getInstallationDetails(municipalityId, null, null, null, null, null, ASC, 1, 100);

		verifyNoMoreInteractions(dataWarehouseReaderClient);
	}
}
