package se.sundsvall.installation;

import static java.lang.String.valueOf;
import static java.util.Optional.ofNullable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import se.sundsvall.installation.api.model.Installation;
import se.sundsvall.installation.api.model.SearchParameters;

import generated.se.sundsvall.datawarehousereader.InstallationDetails;
import generated.se.sundsvall.datawarehousereader.InstallationDetailsResponse;
import generated.se.sundsvall.datawarehousereader.InstallationMetaDataEmbeddable;
import generated.se.sundsvall.datawarehousereader.PagingAndSortingMetaData;

public final class TestUtil {

	private TestUtil() {
		// prevent instantiation
	}

	public static SearchParameters createSearchParameters() {
		return SearchParameters.builder()
			.withCategory("ELECTRICITY")
			.withDateFrom(LocalDate.now())
			.withFacilityId("facilityId")
			.withInstalled(false)
			.build();
	}

	public static Installation createInstallation() {
		return Installation.builder()
			.withCompany("company")
			.withType("category")
			.withFacilityId("facilityId")
			.withPlacementId(123456)
			.withCareOf("careOf")
			.withStreet("street")
			.withPostCode("postalCode")
			.withCity("city")
			.withPropertyDesignation("propertyDesignation")
			.withDateFrom(LocalDate.now())
			.withDateTo(LocalDate.now())
			.withDateLastModified(LocalDate.now())
			.withMetaData(List.of())
			.build();
	}

	public static InstallationDetails createInstallationDetails() {
		return new InstallationDetails()
			.company("company")
			.type("category")
			.facilityId("facilityId")
			.placementId(123456)
			.careOf("careOf")
			.street("street")
			.postCode("postalCode")
			.city("city")
			.propertyDesignation("propertyDesignation")
			.dateFrom(LocalDate.now())
			.dateTo(LocalDate.now())
			.dateLastModified(LocalDate.now())
			.metaData(List.of());
	}

	public static InstallationMetaDataEmbeddable createInstallationMetaDataEmbeddable() {
		return new InstallationMetaDataEmbeddable()
			.key("key")
			.value("value")
			.company("company")
			.type("category")
			.displayName("displayName");
	}

	public static InstallationDetailsResponse createInstallationDetailsResponse() {
		final var response = new InstallationDetailsResponse();
		response.setInstallationDetails(List.of(createInstallationDetails()));
		return response;
	}

	public static SearchParameters createSearchParameters(final Consumer<SearchParameters> modifier) {
		final var searchParameters = createSearchParameters();
		Optional.ofNullable(modifier).ifPresent(m -> m.accept(searchParameters));
		return searchParameters;
	}

	public static MultiValueMap<String, String> createParameterMap(final SearchParameters searchParameters) {
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

		ofNullable(searchParameters.getInstalled()).ifPresent(p -> parameters.add("installed", valueOf(p)));
		ofNullable(searchParameters.getFacilityId()).ifPresent(p -> parameters.add("facilityId", p));
		ofNullable(searchParameters.getDateFrom()).ifPresent(p -> parameters.add("dateFrom", p.toString()));
		ofNullable(searchParameters.getCategory()).ifPresent(p -> parameters.add("category", p));
		ofNullable(searchParameters.getPage()).ifPresent(p -> parameters.add("page", valueOf(p)));
		ofNullable(searchParameters.getSize()).ifPresent(p -> parameters.add("size", valueOf(p)));
		ofNullable(searchParameters.getSort()).ifPresent(p -> p.forEach(sortBy -> parameters.add("sort", sortBy)));
		return parameters;
	}

	public static PagingAndSortingMetaData createPagingAndSortingMetaData() {
		return new PagingAndSortingMetaData()
			.page(1)
			.limit(10)
			.count(10)
			.totalPages(10)
			.totalRecords(100L)
			.sortBy(List.of("sortBy"))
			.sortDirection(generated.se.sundsvall.datawarehousereader.Direction.ASC);
	}

}
