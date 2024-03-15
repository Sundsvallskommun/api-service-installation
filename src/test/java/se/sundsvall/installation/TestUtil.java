package se.sundsvall.installation;

import static java.lang.String.valueOf;
import static java.util.Optional.ofNullable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import se.sundsvall.installation.api.model.SearchParameters;

import generated.se.sundsvall.datawarehousereader.Direction;
import generated.se.sundsvall.datawarehousereader.InstallationDetails;
import generated.se.sundsvall.datawarehousereader.InstallationDetailsResponse;

public final class TestUtil {

	private TestUtil() {
		// prevent instantiation
	}

	public static SearchParameters createSearchParameters() {
		return SearchParameters.builder()
			.withInstalled(false)
			.withDateFrom(LocalDate.now())
			.withFacilityId("facilityId")
			.withCategory("ELECTRICITY")
			.withPage(1)
			.withLimit(100)
			.withSortBy(List.of("sortBy"))
			.withSortDirection(Direction.ASC)
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
		ofNullable(searchParameters.getPage()).ifPresent(p -> parameters.add("page", p.toString()));
		ofNullable(searchParameters.getLimit()).ifPresent(p -> parameters.add("limit", p.toString()));
		ofNullable(searchParameters.getSortDirection()).ifPresent(p -> parameters.add("sortDirection", String.valueOf(p)));
		ofNullable(searchParameters.getSortBy()).ifPresent(p -> p.forEach(sortBy -> parameters.add("sortBy", sortBy)));
		return parameters;
	}

}
