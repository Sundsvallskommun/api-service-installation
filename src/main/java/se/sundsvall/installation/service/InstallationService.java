package se.sundsvall.installation.service;

import org.springframework.stereotype.Service;
import se.sundsvall.installation.api.model.InstallationsResponse;
import se.sundsvall.installation.api.model.SearchParameters;
import se.sundsvall.installation.integration.datawarehousereader.DataWarehouseReaderClient;

import static se.sundsvall.installation.service.mapper.Mapper.toInstallationParameters;
import static se.sundsvall.installation.service.mapper.Mapper.toInstallationsResponse;

@Service
public class InstallationService {

	private final DataWarehouseReaderClient dataWarehouseReaderClient;

	public InstallationService(final DataWarehouseReaderClient dataWarehouseReaderClient) {
		this.dataWarehouseReaderClient = dataWarehouseReaderClient;
	}

	public InstallationsResponse getInstallations(final String municipalityId, final SearchParameters searchParameters) {
		return toInstallationsResponse(dataWarehouseReaderClient.getInstallationDetails(municipalityId, toInstallationParameters(searchParameters)));
	}
}
