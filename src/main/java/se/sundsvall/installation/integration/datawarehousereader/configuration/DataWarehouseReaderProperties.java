package se.sundsvall.installation.integration.datawarehousereader.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties("integration.datawarehousereader")
public record DataWarehouseReaderProperties(@DefaultValue("5") int connectTimeout,
                                            @DefaultValue("20") int readTimeout) {

}
