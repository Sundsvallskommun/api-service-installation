package se.sundsvall.installation.api.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;

import se.sundsvall.installation.api.validation.ValidCategory;

import generated.se.sundsvall.datawarehousereader.Direction;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Search parameters model")
@Getter
@Setter
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class SearchParameters {

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

	@Schema(description = "Page number", example = "1")
	@Min(1)
	private Integer page;

	@Schema(description = "Number of items per page", example = "10")
	@Max(1000)
	@Min(1)
	private Integer limit;

	@Schema(description = "Sort direction", example = "ASC")
	private Direction sortDirection;

	@Schema(description = "Sort by", example = "name")
	private List<String> sortBy;
}
