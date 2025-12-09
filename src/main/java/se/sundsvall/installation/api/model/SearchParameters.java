package se.sundsvall.installation.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import se.sundsvall.dept44.models.api.paging.AbstractParameterPagingAndSortingBase;
import se.sundsvall.installation.api.validation.ValidCategory;

@Getter
@Setter
@Schema(description = "Search parameters model")
public class SearchParameters extends AbstractParameterPagingAndSortingBase {

	@Schema(description = "Category", examples = "ELECTRICITY")
	@ValidCategory
	private String category;

	@Schema(description = "Facility id", examples = "123456789132456789")
	private String facilityId;

	@Schema(description = "Is the installation installed", examples = "true")
	private Boolean installed;

	@Schema(description = "Last changed date. Format is YYYY-MM-DD.", examples = "2022-01-01")
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		SearchParameters that = (SearchParameters) o;
		return Objects.equals(category, that.category) && Objects.equals(facilityId, that.facilityId) && Objects.equals(installed, that.installed) && Objects.equals(dateFrom, that.dateFrom);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), category, facilityId, installed, dateFrom);
	}
}
