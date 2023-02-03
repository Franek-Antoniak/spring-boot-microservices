package springframework.msscbeerclient.client;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConstructorBinding
@ConfigurationProperties(
		prefix = "sf.brewery",
		ignoreUnknownFields = false
)
public class BreweryClientConfiguration {
	@Setter private String apihost;

	@Bean
	public RestTemplate getRestTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public String apihost() {
		return apihost;
	}
}
