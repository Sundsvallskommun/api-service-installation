package se.sundsvall.installation.api.model;

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
public class MetaDataEmbeddable {

	@Schema(description = "Company name", accessMode = Schema.AccessMode.READ_ONLY)
	private String company;

	@Schema(description = "Key", accessMode = Schema.AccessMode.READ_ONLY)
	private String key;

	@Schema(description = "Value", accessMode = Schema.AccessMode.READ_ONLY)
	private String value;

	@Schema(description = "Type", accessMode = Schema.AccessMode.READ_ONLY)
	private String type;

	@Schema(description = "Display Name", accessMode = Schema.AccessMode.READ_ONLY)
	private String displayName;
}
