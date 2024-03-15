package se.sundsvall.installation.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static se.sundsvall.installation.TestUtil.createSearchParameters;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import se.sundsvall.installation.integration.datawarehousereader.DataWarehouseReaderClient;

import generated.se.sundsvall.datawarehousereader.InstallationParameters;

@ExtendWith(MockitoExtension.class)
class InstallationServiceTest {

	@Mock
	private DataWarehouseReaderClient dataWarehouseReaderClient;

	@Captor
	private ArgumentCaptor<InstallationParameters> installationParametersCaptor;

	@InjectMocks
	private InstallationService installationService;

	@Test
	void getInstallations() {
		final var searchParameters = createSearchParameters();

		installationService.getInstallations(searchParameters);

		verify(dataWarehouseReaderClient).getInstallationDetails(installationParametersCaptor.capture());

		assertThat(installationParametersCaptor.getValue()).satisfies(bean -> {
			assertThat(bean.getCategory()).hasToString(searchParameters.getCategory());
			assertThat(bean.getDateFrom()).isEqualTo(searchParameters.getDateFrom());
			assertThat(bean.getFacilityId()).isEqualTo(searchParameters.getFacilityId());
			assertThat(bean.getInstalled()).isEqualTo(searchParameters.getInstalled());
			assertThat(bean.getLimit()).isEqualTo(searchParameters.getLimit());
			assertThat(bean.getPage()).isEqualTo(searchParameters.getPage());
			assertThat(bean.getSortBy()).isEqualTo(searchParameters.getSortBy());
			assertThat(bean.getSortDirection()).isEqualTo(searchParameters.getSortDirection());
		});

		verifyNoMoreInteractions(dataWarehouseReaderClient);
	}

}
