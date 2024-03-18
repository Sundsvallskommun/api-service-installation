package se.sundsvall.installation.api.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

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
public class InstallationDetails {

	@Schema(description = "Company name", accessMode = Schema.AccessMode.READ_ONLY)
	private String company;

	@Schema(description = "Type", accessMode = Schema.AccessMode.READ_ONLY)
	private String type;

	@Schema(description = "Facility id", accessMode = Schema.AccessMode.READ_ONLY)
	private String facilityId;

	@Schema(description = "Placement id", accessMode = Schema.AccessMode.READ_ONLY)
	private Integer placementId;

	@Schema(description = "Care of address", accessMode = Schema.AccessMode.READ_ONLY)
	private String careOf;

	@Schema(description = "Street", accessMode = Schema.AccessMode.READ_ONLY)
	private String street;

	@Schema(description = "Postal code", accessMode = Schema.AccessMode.READ_ONLY)
	private String postCode;

	@Schema(description = "City", accessMode = Schema.AccessMode.READ_ONLY)
	private String city;

	@Schema(description = "Property designation", accessMode = Schema.AccessMode.READ_ONLY)
	private String propertyDesignation;

	@Schema(description = "From date", accessMode = Schema.AccessMode.READ_ONLY)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dateFrom;

	@Schema(description = "To date", accessMode = Schema.AccessMode.READ_ONLY)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dateTo;

	@Schema(description = "Date when object was last modified", accessMode = Schema.AccessMode.READ_ONLY)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dateLastModified;

	@Schema(description = "Meta data", accessMode = Schema.AccessMode.READ_ONLY)
	private List<MetaDataEmbeddable> metaData;
}
