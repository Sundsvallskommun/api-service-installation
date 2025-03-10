package se.sundsvall.installation;

import static java.lang.String.valueOf;

import generated.se.sundsvall.datawarehousereader.InstallationDetails;
import generated.se.sundsvall.datawarehousereader.InstallationDetailsResponse;
import generated.se.sundsvall.datawarehousereader.InstallationMetaDataEmbeddable;
import generated.se.sundsvall.datawarehousereader.PagingAndSortingMetaData;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import org.springframework.data.domain.Sort;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import se.sundsvall.installation.api.model.SearchParameters;

public final class TestUtil {

	private TestUtil() {
		// prevent instantiation
	}

	public static SearchParameters createSearchParameters() {
		return SearchParameters.create()
			.withCategory("ELECTRICITY")
			.withDateFrom(LocalDate.now())
			.withFacilityId("facilityId")
			.withInstalled(false)
			.withPage(1)
			.withLimit(1)
			.withSortBy(List.of("asc"))
			.withSortDirection(Sort.Direction.ASC);
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
		final MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

		Optional.ofNullable(searchParameters.getInstalled()).ifPresent(p -> parameters.add("installed", valueOf(p)));
		Optional.ofNullable(searchParameters.getFacilityId()).ifPresent(p -> parameters.add("facilityId", p));
		Optional.ofNullable(searchParameters.getDateFrom()).ifPresent(p -> parameters.add("dateFrom", p.toString()));
		Optional.ofNullable(searchParameters.getCategory()).ifPresent(p -> parameters.add("category", p));
		Optional.of(searchParameters.getPage()).ifPresent(p -> parameters.add("page", valueOf(p)));
		Optional.of(searchParameters.getLimit()).ifPresent(p -> parameters.add("limit", valueOf(p)));
		Optional.ofNullable(searchParameters.getSortDirection()).ifPresent(p -> parameters.add("sortDirection", p.name()));
		Optional.ofNullable(searchParameters.getSortBy()).ifPresent(p -> p.forEach(sortBy -> parameters.add("sortBy", sortBy)));
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
