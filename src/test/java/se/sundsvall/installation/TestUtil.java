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
			.withSortBy(List.of("name"))
			.withSortDirection(Direction.ASC)
			.build();
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
		ofNullable(searchParameters.getSortBy()).ifPresent(p -> parameters.add("sortBy", String.valueOf(p)));
		ofNullable(searchParameters.getSortDirection()).ifPresent(p -> parameters.add("sortDirection", String.valueOf(p)));
		return parameters;
	}

}
