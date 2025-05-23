package se.sundsvall.installation.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;
import static org.zalando.problem.Status.BAD_REQUEST;
import static se.sundsvall.installation.TestUtil.createParameterMap;
import static se.sundsvall.installation.TestUtil.createSearchParameters;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.zalando.problem.violations.ConstraintViolationProblem;
import org.zalando.problem.violations.Violation;
import se.sundsvall.installation.Application;
import se.sundsvall.installation.service.InstallationService;

@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
@ActiveProfiles("junit")
class InstallationResourceFailureTest {

	private static final String PATH = "/{municipalityId}/installations";

	@MockitoBean
	private InstallationService installationServiceMock;

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void getInstallationsPageLessThanMinimum() {

		final var municipalityId = "2281";
		final var searchParameters = createSearchParameters(sp -> sp.setPage(-1));
		final var parameterObject = createParameterMap(searchParameters);

		final var response = webTestClient.get().uri(uriBuilder -> uriBuilder.path(PATH).queryParams(parameterObject).build(municipalityId))
			.exchange()
			.expectStatus().isBadRequest()
			.expectHeader().contentType(APPLICATION_PROBLEM_JSON_VALUE)
			.expectBody(ConstraintViolationProblem.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getTitle()).isEqualTo("Constraint Violation");
		assertThat(response.getStatus()).isEqualTo(BAD_REQUEST);
		assertThat(response.getViolations()).extracting(Violation::getField, Violation::getMessage)
			.containsExactlyInAnyOrder(tuple("page", "must be greater than or equal to 1"));

		verifyNoInteractions(installationServiceMock);
	}

	@Test
	void getInstallationsLimitLessThanMinimum() {

		final var municipalityId = "2281";
		final var searchParameters = createSearchParameters(sp -> sp.setLimit(0));
		final var parameterObject = createParameterMap(searchParameters);

		final var response = webTestClient.get().uri(uriBuilder -> uriBuilder.path(PATH).queryParams(parameterObject).build(municipalityId))
			.exchange()
			.expectStatus().isBadRequest()
			.expectHeader().contentType(APPLICATION_PROBLEM_JSON_VALUE)
			.expectBody(ConstraintViolationProblem.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getTitle()).isEqualTo("Constraint Violation");
		assertThat(response.getStatus()).isEqualTo(BAD_REQUEST);
		assertThat(response.getViolations()).extracting(Violation::getField, Violation::getMessage)
			.containsExactlyInAnyOrder(tuple("limit", "must be greater than or equal to 1"));

		verifyNoInteractions(installationServiceMock);
	}

	@Test
	void getInstallationsInvalidCategory() {

		final var municipalityId = "2281";
		final var searchParameters = createSearchParameters(sp -> sp.setCategory("invalid"));
		final var parameterObject = createParameterMap(searchParameters);

		final var response = webTestClient.get().uri(uriBuilder -> uriBuilder.path(PATH).queryParams(parameterObject).build(municipalityId))
			.exchange()
			.expectStatus().isBadRequest()
			.expectHeader().contentType(APPLICATION_PROBLEM_JSON_VALUE)
			.expectBody(ConstraintViolationProblem.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getTitle()).isEqualTo("Constraint Violation");
		assertThat(response.getStatus()).isEqualTo(BAD_REQUEST);
		assertThat(response.getViolations()).extracting(Violation::getField, Violation::getMessage)
			.containsExactlyInAnyOrder(tuple("category", "category is not valid"));

		verifyNoInteractions(installationServiceMock);

	}

	@Test
	void getInstallationsInvalidMunicipalityId() {

		final var municipalityId = "invalid";
		final var searchParameters = createSearchParameters();
		final var parameterObject = createParameterMap(searchParameters);

		final var response = webTestClient.get().uri(uriBuilder -> uriBuilder.path(PATH).queryParams(parameterObject).build(municipalityId))
			.exchange()
			.expectStatus().isBadRequest()
			.expectHeader().contentType(APPLICATION_PROBLEM_JSON_VALUE)
			.expectBody(ConstraintViolationProblem.class)
			.returnResult()
			.getResponseBody();

		assertThat(response).isNotNull();
		assertThat(response.getTitle()).isEqualTo("Constraint Violation");
		assertThat(response.getStatus()).isEqualTo(BAD_REQUEST);
		assertThat(response.getViolations()).extracting(Violation::getField, Violation::getMessage)
			.containsExactlyInAnyOrder(tuple("getInstallations.municipalityId", "not a valid municipality ID"));

		verifyNoInteractions(installationServiceMock);
	}
}
