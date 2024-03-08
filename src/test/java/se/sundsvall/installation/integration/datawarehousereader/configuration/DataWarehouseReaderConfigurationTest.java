package se.sundsvall.installation.integration.datawarehousereader.configuration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static se.sundsvall.installation.integration.datawarehousereader.configuration.DataWarehouseReaderConfiguration.CLIENT_ID;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

import se.sundsvall.dept44.configuration.feign.FeignMultiCustomizer;
import se.sundsvall.dept44.configuration.feign.decoder.ProblemErrorDecoder;

import feign.codec.ErrorDecoder;

@ExtendWith(MockitoExtension.class)
class DataWarehouseReaderConfigurationTest {

	@Mock
	private ClientRegistrationRepository clientRepositoryMock;

	@Mock
	private ClientRegistration clientRegistrationMock;

	@Mock
	private DataWarehouseReaderProperties propertiesMock;

	@Spy
	private FeignMultiCustomizer feignMultiCustomizerSpy;

	@Captor
	private ArgumentCaptor<ErrorDecoder> errorDecoderCaptor;

	@InjectMocks
	private DataWarehouseReaderConfiguration configuration;

	@Test
	void testFeignBuilderHelper() {
		final var connectTimeout = 123;
		final var readTimeout = 321;

		when(propertiesMock.connectTimeout()).thenReturn(connectTimeout);
		when(propertiesMock.readTimeout()).thenReturn(readTimeout);
		when(clientRepositoryMock.findByRegistrationId(CLIENT_ID)).thenReturn(clientRegistrationMock);

		try (MockedStatic<FeignMultiCustomizer> feignMultiCustomizerMock = Mockito.mockStatic(FeignMultiCustomizer.class)) {
			feignMultiCustomizerMock.when(FeignMultiCustomizer::create).thenReturn(feignMultiCustomizerSpy);
			configuration.feignBuilderCustomizer(clientRepositoryMock, propertiesMock);
			feignMultiCustomizerMock.verify(FeignMultiCustomizer::create);
		}

		verify(propertiesMock).connectTimeout();
		verify(propertiesMock).readTimeout();
		verify(clientRepositoryMock).findByRegistrationId(CLIENT_ID);
		verify(feignMultiCustomizerSpy).withErrorDecoder(errorDecoderCaptor.capture());
		verify(feignMultiCustomizerSpy).withRequestTimeoutsInSeconds(connectTimeout, readTimeout);
		verify(feignMultiCustomizerSpy).withRetryableOAuth2InterceptorForClientRegistration(clientRegistrationMock);
		verify(feignMultiCustomizerSpy).composeCustomizersToOne();

		assertThat(errorDecoderCaptor.getValue())
			.isInstanceOf(ProblemErrorDecoder.class)
			.hasFieldOrPropertyWithValue("bypassResponseCodes", List.of(NOT_FOUND.value()))
			.hasFieldOrPropertyWithValue("integrationName", CLIENT_ID);
	}
}
