package se.sundsvall.installation.api;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se.sundsvall.installation.service.InstallationService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Validated
@RequestMapping("/installations")
@Tag(name = "Installation")
class InstallationResource {

	private final InstallationService installationService;

	InstallationResource(final InstallationService installationService) {
		this.installationService = installationService;
	}


}
