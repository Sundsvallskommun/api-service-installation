package se.sundsvall.installation.service.mapper;

import java.util.Optional;

import se.sundsvall.installation.api.model.SearchParameters;

import generated.se.sundsvall.datawarehousereader.Category;
import generated.se.sundsvall.datawarehousereader.InstallationParameters;

public final class RequestMapper {

	private RequestMapper() {
		//prevent instantiation
	}

	public static InstallationParameters toInstallationParameters(final SearchParameters searchParameters) {
		return Optional.ofNullable(searchParameters).map(searchParams -> new InstallationParameters()
				.installed(searchParams.getInstalled())
				.dateFrom(searchParams.getDateFrom())
				.category(Category.fromValue(searchParams.getCategory()))
				.facilityId(searchParams.getFacilityId())
				.page(searchParams.getPage())
				.limit(searchParams.getLimit())
				.sortBy(searchParams.getSortBy())
				.sortDirection(searchParams.getSortDirection()))
			.orElse(null);
	}


}
