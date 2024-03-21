package apptest;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.OK;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import se.sundsvall.dept44.test.AbstractAppTest;
import se.sundsvall.dept44.test.annotation.wiremock.WireMockAppTestSuite;
import se.sundsvall.installation.Application;

@WireMockAppTestSuite(files = "classpath:/InstallationIT/", classes = Application.class)
@ActiveProfiles("it")
class InstallationIT extends AbstractAppTest {

	private static final String EXPECTED_FILE = "expected.json";


	@Test
	void test1_getInstallations() {
		setupCall()
			.withServicePath("/installations?installed=false&facilityId=123&dateFrom=2022-01-01&category=ELECTRICITY&page=1&size=12&sort=ASC")
			.withHttpMethod(GET)
			.withExpectedResponse(EXPECTED_FILE)
			.withExpectedResponseStatus(OK)
			.sendRequestAndVerifyResponse();
	}

}
