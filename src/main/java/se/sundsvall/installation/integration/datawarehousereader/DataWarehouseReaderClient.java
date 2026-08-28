package se.sundsvall.installation.integration.datawarehousereader;

import generated.se.sundsvall.datawarehousereader.Category;
import generated.se.sundsvall.datawarehousereader.Direction;
import generated.se.sundsvall.datawarehousereader.InstallationDetailsResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import java.time.LocalDate;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import se.sundsvall.installation.integration.datawarehousereader.configuration.DataWarehouseReaderConfiguration;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;
import static se.sundsvall.installation.integration.datawarehousereader.configuration.DataWarehouseReaderConfiguration.CLIENT_ID;

@FeignClient(
	name = CLIENT_ID,
	url = "${integration.datawarehousereader.url}",
	configuration = DataWarehouseReaderConfiguration.class)
@CircuitBreaker(name = CLIENT_ID)
public interface DataWarehouseReaderClient {

	@GetMapping(path = "/{municipalityId}/installations", produces = {
		APPLICATION_JSON_VALUE, APPLICATION_PROBLEM_JSON_VALUE
	})
	InstallationDetailsResponse getInstallationDetails(
		@PathVariable final String municipalityId,
		@RequestParam(value = "installed", required = false) final Boolean installed,
		@RequestParam(value = "lastModifiedDateFrom", required = false) @DateTimeFormat(iso = ISO.DATE) final LocalDate lastModifiedDateFrom,
		@RequestParam(value = "category", required = false) final Category category,
		@RequestParam(value = "facilityId", required = false) final String facilityId,
		@RequestParam(value = "sortBy", required = false) final List<String> sortBy,
		@RequestParam(value = "sortDirection", required = false) final Direction sortDirection,
		@RequestParam(value = "page", required = false) final Integer page,
		@RequestParam(value = "limit", required = false) final Integer limit);
}
