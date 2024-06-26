package se.sundsvall.installation.integration.datawarehousereader;

import static se.sundsvall.installation.integration.datawarehousereader.configuration.DataWarehouseReaderConfiguration.CLIENT_ID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

import se.sundsvall.installation.integration.datawarehousereader.configuration.DataWarehouseReaderConfiguration;

import generated.se.sundsvall.datawarehousereader.InstallationDetailsResponse;
import generated.se.sundsvall.datawarehousereader.InstallationParameters;


@FeignClient(
	name = CLIENT_ID,
	url = "${integration.datawarehousereader.url}",
	configuration = DataWarehouseReaderConfiguration.class
)
public interface DataWarehouseReaderClient {

	@GetMapping("/installations")
	InstallationDetailsResponse getInstallationDetails(@SpringQueryMap final InstallationParameters installationParameters);

}
