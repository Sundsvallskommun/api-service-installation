package se.sundsvall.installation.api.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;

import se.sundsvall.installation.api.validation.ValidCategory;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Search parameters model")
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

	@Min(0)
	@Schema(description = "Zero-based page index (0..N)", example = "0")
	private Integer page;

	@Min(1)
	@Schema(description = "The size of the page to be returned", example = "20")
	private Integer size;

	@ArraySchema(schema = @Schema(description = "Sort by, asc|desc", example = "asc", implementation = String.class))
	private List<String> sort;

}
