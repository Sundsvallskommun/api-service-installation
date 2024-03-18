package se.sundsvall.installation.service.mapper;

import java.util.List;
import java.util.Optional;

import se.sundsvall.installation.api.model.InstallationDetails;
import se.sundsvall.installation.api.model.InstallationsResponse;
import se.sundsvall.installation.api.model.MetaDataEmbeddable;
import se.sundsvall.installation.api.model.SearchParameters;

import generated.se.sundsvall.datawarehousereader.Category;
import generated.se.sundsvall.datawarehousereader.InstallationDetailsResponse;
import generated.se.sundsvall.datawarehousereader.InstallationMetaDataEmbeddable;
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
				.sortBy(searchParams.getSortBy()))
			.orElse(null);
	}

	public static InstallationsResponse toInstallationsResponse(final InstallationDetailsResponse response) {
		return Optional.ofNullable(response).map(response1 -> InstallationsResponse.builder()
				.withInstallationDetails(toInstallationDetailsList(response.getInstallationDetails()))
				.withMeta(response.getMeta())
				.build())
			.orElse(null);
	}

	public static List<InstallationDetails> toInstallationDetailsList(final List<generated.se.sundsvall.datawarehousereader.InstallationDetails> installationDetails) {
		return installationDetails.stream()
			.map(Mapper::toInstallationDetails)
			.toList();
	}

	public static InstallationDetails toInstallationDetails(final generated.se.sundsvall.datawarehousereader.InstallationDetails installationDetails) {
		return Optional.ofNullable(installationDetails).map(details -> InstallationDetails.builder()
				.withCompany(details.getCompany())
				.withCareOf(details.getCareOf())
				.withCity(details.getCity())
				.withStreet(details.getStreet())
				.withDateFrom(details.getDateFrom())
				.withDateTo(details.getDateTo())
				.withFacilityId(details.getFacilityId())
				.withType(details.getType())
				.withDateLastModified(details.getDateLastModified())
				.withPropertyDesignation(details.getPropertyDesignation())
				.withPostCode(details.getPostCode())
				.withMetaData(toMetaDataEmbeddables(details.getMetaData()))
				.withPlacementId(details.getPlacementId())
				.build())
			.orElse(null);
	}

	public static MetaDataEmbeddable toMetaDataEmbeddable(final InstallationMetaDataEmbeddable metaData) {
		return Optional.ofNullable(metaData).map(data -> MetaDataEmbeddable.builder()
				.withKey(data.getKey())
				.withDisplayName(data.getDisplayName())
				.withType(data.getType())
				.withValue(data.getValue())
				.withCompany(data.getCompany())
				.build())
			.orElse(null);
	}

	public static List<MetaDataEmbeddable> toMetaDataEmbeddables(final List<InstallationMetaDataEmbeddable> metaData) {
		return metaData.stream()
			.map(Mapper::toMetaDataEmbeddable)
			.toList();
	}

}
