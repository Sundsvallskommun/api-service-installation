package se.sundsvall.installation.api.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;

import se.sundsvall.dept44.models.api.paging.AbstractParameterPagingAndSortingBase;
import se.sundsvall.installation.api.validation.ValidCategory;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Search parameters model")
public class SearchParameters extends AbstractParameterPagingAndSortingBase {

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

	public SearchParameters withPage(final Integer page) {
		this.page = page;
		return this;
	}

	public SearchParameters withLimit(final Integer limit) {
		this.limit = limit;
		return this;
	}

	public SearchParameters withSortDirection(final Sort.Direction sortDirection) {
		this.sortDirection = sortDirection;
		return this;
	}

	public SearchParameters withSortBy(final List<String> sortBy) {
		this.sortBy = sortBy;
		return this;
	}

}
