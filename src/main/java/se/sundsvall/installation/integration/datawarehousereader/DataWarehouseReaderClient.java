package se.sundsvall.installation.integration.datawarehousereader;

import static se.sundsvall.installation.integration.datawarehousereader.configuration.DataWarehouseReaderConfiguration.CLIENT_ID;

import generated.se.sundsvall.datawarehousereader.InstallationDetailsResponse;
import generated.se.sundsvall.datawarehousereader.InstallationParameters;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import se.sundsvall.installation.integration.datawarehousereader.configuration.DataWarehouseReaderConfiguration;

@FeignClient(
	name = CLIENT_ID,
	url = "${integration.datawarehousereader.url}",
	configuration = DataWarehouseReaderConfiguration.class)
@CircuitBreaker(name = CLIENT_ID)
public interface DataWarehouseReaderClient {

	@GetMapping("/{municipalityId}/installations")
	InstallationDetailsResponse getInstallationDetails(
		@PathVariable("municipalityId") String municipalityId,
		@SpringQueryMap final InstallationParameters installationParameters);
}
