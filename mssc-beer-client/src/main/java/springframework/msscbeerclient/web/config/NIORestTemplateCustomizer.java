package springframework.msscbeerclient.web.config;

import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.reactor.IOReactorException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsAsyncClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class NIORestTemplateCustomizer implements RestTemplateCustomizer {
	@Value("${sf.maxtotalconnections}") private Integer MAX_TOTAL_CONNECTIONS;
	@Value("${sf.maxtotalconnectionsperroute}") private Integer MAX_TOTAL_CONNECTIONS_PER_ROUTE;
	@Value("${sf.connectionrequesttimeout}") private Integer CONNECTION_REQUEST_TIMEOUT;
	@Value("${sf.sockettimeout}") private Integer SOCKET_TIMEOUT;
	@Value("${sf.iothreadcount}") private Integer IO_THREAD_COUNT;

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
				.setConnectTimeout(CONNECTION_REQUEST_TIMEOUT)
				.setIoThreadCount(IO_THREAD_COUNT)
				.setSoTimeout(SOCKET_TIMEOUT)
				.build());
		PoolingNHttpClientConnectionManager connectionManager = new PoolingNHttpClientConnectionManager(ioReactor);
		connectionManager.setDefaultMaxPerRoute(MAX_TOTAL_CONNECTIONS_PER_ROUTE);
		connectionManager.setMaxTotal(MAX_TOTAL_CONNECTIONS);

		CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClients.custom()
				.setConnectionManager(connectionManager)
				.build();

		return new HttpComponentsAsyncClientHttpRequestFactory(httpAsyncClient);
	}
}
