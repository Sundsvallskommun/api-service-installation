package se.sundsvall.installation.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static se.sundsvall.installation.TestUtil.createSearchParameters;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import se.sundsvall.installation.integration.datawarehousereader.DataWarehouseReaderClient;

import generated.se.sundsvall.datawarehousereader.InstallationParameters;

@ExtendWith(MockitoExtension.class)
class InstallationServiceTest {

	@Mock
	private DataWarehouseReaderClient dataWarehouseReaderClient;

	@InjectMocks
	private InstallationService installationService;

	@Test
	void getInstallations() {
		final var searchParameters = createSearchParameters();

		installationService.getInstallations(searchParameters);

		verify(dataWarehouseReaderClient).getInstallationDetails(any(InstallationParameters.class));
	}

}
