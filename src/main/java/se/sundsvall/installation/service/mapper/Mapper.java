package se.sundsvall.installation.service.mapper;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort.Direction;

import se.sundsvall.dept44.models.api.paging.PagingAndSortingMetaData;
import se.sundsvall.installation.api.model.Installation;
import se.sundsvall.installation.api.model.InstallationsResponse;
import se.sundsvall.installation.api.model.MetaData;
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
				.sortBy(searchParams.getSortBy())
				.sortDirection(generated.se.sundsvall.datawarehousereader.Direction.fromValue(searchParams.getSortDirection().name().toUpperCase())))
			.orElse(null);
	}

	public static InstallationsResponse toInstallationsResponse(final InstallationDetailsResponse response) {
		return Optional.ofNullable(response).map(response1 -> InstallationsResponse.builder()
				.withInstallationDetails(toInstallationDetailsList(response.getInstallationDetails()))
				.withMeta(toPagingAndSortingMetaData(response.getMeta()))
				.build())
			.orElse(null);
	}

	public static PagingAndSortingMetaData toPagingAndSortingMetaData(final generated.se.sundsvall.datawarehousereader.PagingAndSortingMetaData meta) {
		return Optional.ofNullable(meta).map(meta1 -> PagingAndSortingMetaData.create()
				.withPage(meta.getPage())
				.withLimit(meta.getLimit())
				.withCount(meta.getCount())
				.withTotalRecords(meta.getTotalRecords())
				.withTotalPages(meta.getTotalPages())
				.withSortBy(meta.getSortBy())
				.withSortDirection(Direction.fromOptionalString(String.valueOf(meta.getSortDirection())).orElse(Direction.ASC)))
			.orElse(null);
	}

	public static List<Installation> toInstallationDetailsList(final List<generated.se.sundsvall.datawarehousereader.InstallationDetails> installationDetails) {
		return installationDetails.stream()
			.map(Mapper::toInstallationDetails)
			.toList();
	}

	public static Installation toInstallationDetails(final generated.se.sundsvall.datawarehousereader.InstallationDetails installationDetails) {
		return Optional.ofNullable(installationDetails).map(details -> Installation.builder()
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
				.withMetaData(toMetaDataList(details.getMetaData()))
				.withPlacementId(details.getPlacementId())
				.build())
			.orElse(null);
	}

	public static MetaData toMetaData(final InstallationMetaDataEmbeddable metaData) {
		return Optional.ofNullable(metaData).map(data -> MetaData.builder()
				.withKey(data.getKey())
				.withDisplayName(data.getDisplayName())
				.withType(data.getType())
				.withValue(data.getValue())
				.withCompany(data.getCompany())
				.build())
			.orElse(null);
	}

	public static List<MetaData> toMetaDataList(final List<InstallationMetaDataEmbeddable> metaData) {
		return metaData.stream()
			.map(Mapper::toMetaData)
			.toList();
	}

}
