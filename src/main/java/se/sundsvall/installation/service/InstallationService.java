package se.sundsvall.installation.service;

import static se.sundsvall.installation.service.mapper.RequestMapper.toInstallationParameters;

import org.springframework.stereotype.Service;

import se.sundsvall.installation.api.model.SearchParameters;
import se.sundsvall.installation.integration.datawarehousereader.DataWarehouseReaderClient;

import generated.se.sundsvall.datawarehousereader.InstallationDetailsResponse;

@Service
public class InstallationService {

	private final DataWarehouseReaderClient dataWarehouseReaderClient;

	public InstallationService(final DataWarehouseReaderClient dataWarehouseReaderClient) {
		this.dataWarehouseReaderClient = dataWarehouseReaderClient;
	}

	public InstallationDetailsResponse getInstallations(final SearchParameters searchParameters) {
		return dataWarehouseReaderClient.getInstallationDetails(toInstallationParameters(searchParameters));
	}
}
