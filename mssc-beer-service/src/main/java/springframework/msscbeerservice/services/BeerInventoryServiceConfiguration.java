package springframework.msscbeerservice.services;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Slf4j
@ConfigurationProperties(
		prefix = "sf.brewery",
		ignoreUnknownFields = false
)
@Configuration
@RequiredArgsConstructor
public class BeerInventoryServiceConfiguration {
	@Setter
	private String beerInventoryServiceHost;

	@Bean
	public RestTemplate getRestTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public String getBeerInventoryServiceHost() {
		return beerInventoryServiceHost;
	}
}
