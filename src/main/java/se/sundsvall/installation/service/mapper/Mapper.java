package se.sundsvall.installation.service.mapper;

import generated.se.sundsvall.datawarehousereader.Category;
import generated.se.sundsvall.datawarehousereader.InstallationDetailsResponse;
import generated.se.sundsvall.datawarehousereader.InstallationMetaData;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort.Direction;
import se.sundsvall.dept44.models.api.paging.PagingAndSortingMetaData;
import se.sundsvall.installation.api.model.Installation;
import se.sundsvall.installation.api.model.InstallationsResponse;
import se.sundsvall.installation.api.model.MetaData;

public final class Mapper {

	private Mapper() {
		// prevent instantiation
	}

	public static Category toCategory(final String category) {
		return Optional.ofNullable(category).map(Category::fromValue).orElse(null);
	}

	public static generated.se.sundsvall.datawarehousereader.Direction toDirection(final Direction sortDirection) {
		return Optional.ofNullable(sortDirection)
			.map(Direction::name)
			.map(generated.se.sundsvall.datawarehousereader.Direction::fromValue)
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
			.withSortBy(Optional.of(meta.getSortBy()).orElse(Collections.emptyList()))
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

	public static MetaData toMetaData(final InstallationMetaData metaData) {
		return Optional.ofNullable(metaData).map(data -> MetaData.builder()
			.withKey(data.getKey())
			.withDisplayName(data.getDisplayName())
			.withType(data.getType())
			.withValue(data.getValue())
			.build())
			.orElse(null);
	}

	public static List<MetaData> toMetaDataList(final List<InstallationMetaData> metaData) {
		return metaData.stream()
			.map(Mapper::toMetaData)
			.toList();
	}
}
