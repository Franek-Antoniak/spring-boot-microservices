package springframework.msscbeerclient.web.config;

import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.IOReactorException;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class NIORestTemplateCustomizer implements RestTemplateCustomizer {

	@Override
	public void customize(RestTemplate restTemplate) {
		try {
			restTemplate.setRequestFactory(clientHttpRequestFactory());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ClientHttpRequestFactory clientHttpRequestFactory() throws IOReactorException {
		DefaultConnectingIOReactor ioReactor = new DefaultConnectingIOReactor(IOReactorConfig.custom()
				.setConnectTimeout(3000)
				.setIoThreadCount(4)
				.setSoTimeout(3000)
				.build());
		PoolingNHttpClientConnectionManager connectionManager = new PoolingNHttpClientConnectionManager(ioReactor);
		connectionManager.setDefaultMaxPerRoute(100);
		connectionManager.setMaxTotal(1000);

		CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClients.custom()
				.setConnectionManager(connectionManager)
				.build();

		return new HttpComponentsAsyncClientHttpRequestFactory(httpAsyncClient);
	}
}
