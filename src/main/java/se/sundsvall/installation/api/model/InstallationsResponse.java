package se.sundsvall.installation.api.model;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;

import java.util.List;

import se.sundsvall.dept44.models.api.paging.PagingAndSortingMetaData;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder(setterPrefix = "with")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InstallationsResponse {

	@ArraySchema(schema = @Schema(implementation = Installation.class, accessMode = READ_ONLY))
	private List<Installation> installationDetails;

	@Schema(description = "Paging and sorting metadata", accessMode = READ_ONLY)
	private PagingAndSortingMetaData meta;

}
