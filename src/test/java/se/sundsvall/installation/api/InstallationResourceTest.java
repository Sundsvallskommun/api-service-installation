package se.sundsvall.installation.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static se.sundsvall.installation.TestUtil.createParameterMap;
import static se.sundsvall.installation.TestUtil.createSearchParameters;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import se.sundsvall.installation.Application;
import se.sundsvall.installation.api.model.SearchParameters;
import se.sundsvall.installation.service.InstallationService;

@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
@ActiveProfiles("junit")
class InstallationResourceTest {

	private static final String PATH = "/{municipalityId}/installations";

	@MockitoBean
	private InstallationService installationServiceMock;

	@Captor
	private ArgumentCaptor<SearchParameters> searchParametersCaptor;

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void getInstallations() {

		final var municipalityId = "2281";
		final var searchParameters = createSearchParameters();
		final var parameterObject = createParameterMap(searchParameters);

		webTestClient.get().uri(uriBuilder -> uriBuilder.path(PATH)
			.queryParams(parameterObject).build(municipalityId))
			.exchange()
			.expectStatus().isOk();

		verify(installationServiceMock).getInstallations(eq(municipalityId), searchParametersCaptor.capture());

		assertThat(searchParametersCaptor.getValue()).satisfies(bean -> {
			assertThat(bean.getCategory()).isEqualTo(searchParameters.getCategory());
			assertThat(bean.getDateFrom()).isEqualTo(searchParameters.getDateFrom());
			assertThat(bean.getFacilityId()).isEqualTo(searchParameters.getFacilityId());
			assertThat(bean.getInstalled()).isEqualTo(searchParameters.getInstalled());

			assertThat(bean.getPage()).isEqualTo(searchParameters.getPage());
			assertThat(bean.getLimit()).isEqualTo(searchParameters.getLimit());
			assertThat(bean.getSortBy()).isEqualTo(searchParameters.getSortBy());
			assertThat(bean.getSortDirection()).isEqualTo(searchParameters.getSortDirection());
		});

		verifyNoMoreInteractions(installationServiceMock);
	}
}
