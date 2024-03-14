package se.sundsvall.installation.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static se.sundsvall.installation.TestUtil.createParameterMap;
import static se.sundsvall.installation.TestUtil.createSearchParameters;

import org.junit.jupiter.api.Test;
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

	@MockBean
	private InstallationService installationServiceMock;

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void getInstallations() {
		final var searchParameters = createSearchParameters();
		final var parameterObject = createParameterMap(searchParameters);

		webTestClient.get().uri(uriBuilder -> uriBuilder.path("/installations")
				.queryParams(parameterObject).build())
			.exchange()
			.expectStatus().isOk();

		verify(installationServiceMock).getInstallations(any(SearchParameters.class));
		verifyNoMoreInteractions(installationServiceMock);
	}

}
