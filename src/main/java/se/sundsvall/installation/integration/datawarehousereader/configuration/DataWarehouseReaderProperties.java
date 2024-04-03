package se.sundsvall.installation.integration.datawarehousereader.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties("config.integration.datawarehousereader")
public record DataWarehouseReaderProperties(@DefaultValue("20") int connectTimeout,
                                            @DefaultValue("115") int readTimeout) {
}
