package se.sundsvall.installation.integration.datawarehousereader;

import static se.sundsvall.installation.integration.datawarehousereader.configuration.DataWarehouseReaderConfiguration.CLIENT_ID;

import org.springframework.cloud.openfeign.FeignClient;

import se.sundsvall.installation.integration.datawarehousereader.configuration.DataWarehouseReaderConfiguration;

@FeignClient(
	name = CLIENT_ID,
	url = "${integration.datawarehousereader.url}",
	configuration = DataWarehouseReaderConfiguration.class
)
public interface DataWarehouseReaderClient {

}
