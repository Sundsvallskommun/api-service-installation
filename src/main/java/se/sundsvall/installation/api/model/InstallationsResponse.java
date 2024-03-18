package se.sundsvall.installation.api.model;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;

import java.util.List;

import generated.se.sundsvall.datawarehousereader.PagingAndSortingMetaData;
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
public class InstallationsResponse {

	@ArraySchema(schema = @Schema(implementation = InstallationDetails.class, accessMode = READ_ONLY))
	private List<InstallationDetails> installationDetails;

	@Schema(description = "Paging and sorting metadata", accessMode = READ_ONLY)
	private PagingAndSortingMetaData meta;

}
