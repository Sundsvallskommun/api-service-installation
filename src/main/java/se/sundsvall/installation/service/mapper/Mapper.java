package se.sundsvall.installation.service.mapper;

import java.util.Optional;

import se.sundsvall.installation.api.model.InstallationsResponse;
import se.sundsvall.installation.api.model.SearchParameters;

import generated.se.sundsvall.datawarehousereader.Category;
import generated.se.sundsvall.datawarehousereader.InstallationDetailsResponse;
import generated.se.sundsvall.datawarehousereader.InstallationParameters;

public final class Mapper {

	private Mapper() {
		//prevent instantiation
	}

	public static InstallationParameters toInstallationParameters(final SearchParameters searchParameters) {
		return Optional.ofNullable(searchParameters).map(searchParams -> new InstallationParameters()
				.installed(searchParams.getInstalled())
				.dateFrom(searchParams.getDateFrom())
				.category(Optional.ofNullable(searchParams.getCategory()).map(Category::fromValue).orElse(null))
				.facilityId(searchParams.getFacilityId())
				.page(searchParams.getPage())
				.limit(searchParams.getLimit())
				.sortBy(searchParams.getSortBy())
				.sortDirection(searchParams.getSortDirection()))
			.orElse(null);
	}

	public static InstallationsResponse toInstallationsResponse(final InstallationDetailsResponse installationDetailsResponse) {
		return Optional.ofNullable(installationDetailsResponse).map(response -> InstallationsResponse.builder()
				.withInstallationDetails(response.getInstallationDetails())
				.build())
			.orElse(null);
	}

}
