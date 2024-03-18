package se.sundsvall.installation.api.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;

import se.sundsvall.dept44.models.api.paging.PagingAndSortingMetaData;
import se.sundsvall.installation.api.validation.ValidCategory;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Schema(description = "Search parameters model")
public class SearchParameters extends PagingAndSortingMetaData {

	@Schema(description = "Category", example = "ELECTRICITY")
	@ValidCategory
	private String category;

	@Schema(description = "Facility id", example = "123456789132456789")
	private String facilityId;

	@Schema(description = "Is the installation installed", example = "true")
	private Boolean installed;

	@Schema(description = "Last changed date. Format is YYYY-MM-DD.", example = "2022-01-01")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dateFrom;

	public static SearchParameters create() {
		return new SearchParameters();
	}

	@Override
	public SearchParameters withSortBy(final List<String> sortBy) {
		super.setSortBy(sortBy);
		return this;
	}

	@Override
	public SearchParameters withSortDirection(final Sort.Direction sortDirection) {
		super.setSortDirection(sortDirection);
		return this;
	}

	public SearchParameters withCategory(final String category) {
		this.category = category;
		return this;
	}

	public SearchParameters withFacilityId(final String facilityId) {
		this.facilityId = facilityId;
		return this;
	}

	public SearchParameters withInstalled(final Boolean installed) {
		this.installed = installed;
		return this;
	}

	public SearchParameters withDateFrom(final LocalDate dateFrom) {
		this.dateFrom = dateFrom;
		return this;
	}

}
