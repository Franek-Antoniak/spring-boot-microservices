package springframework.msscbeerorder.services.beer.config;

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
		prefix = "sf.brewery.beer-service",
		ignoreUnknownFields = false
)
@Configuration
@RequiredArgsConstructor
public class BaseBeerServiceConfiguration {
	@Setter
	private String host;

	@Bean
	public RestTemplate getRestTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public String getBeerServiceHost() {
		return host;
	}
}
