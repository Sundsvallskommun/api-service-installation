package se.sundsvall.installation.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import se.sundsvall.installation.api.model.InstallationsResponse;
import se.sundsvall.installation.api.model.SearchParameters;
import se.sundsvall.installation.integration.datawarehousereader.DataWarehouseReaderClient;

import static se.sundsvall.installation.service.mapper.Mapper.toCategory;
import static se.sundsvall.installation.service.mapper.Mapper.toDirection;
import static se.sundsvall.installation.service.mapper.Mapper.toInstallationsResponse;

@Service
public class InstallationService {

	private final DataWarehouseReaderClient dataWarehouseReaderClient;

	public InstallationService(final DataWarehouseReaderClient dataWarehouseReaderClient) {
		this.dataWarehouseReaderClient = dataWarehouseReaderClient;
	}

	public InstallationsResponse getInstallations(final String municipalityId, final SearchParameters searchParameters) {
		final var parameters = Optional.ofNullable(searchParameters).orElseGet(SearchParameters::create);

		return toInstallationsResponse(dataWarehouseReaderClient.getInstallationDetails(
			municipalityId,
			parameters.getInstalled(),
			parameters.getDateFrom(),
			toCategory(parameters.getCategory()),
			parameters.getFacilityId(),
			parameters.getSortBy(),
			toDirection(parameters.getSortDirection()),
			parameters.getPage(),
			parameters.getLimit()));
	}
}
