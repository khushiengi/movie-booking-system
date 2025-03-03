package com.example.searchservice;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;


@Configuration
//@EnableElasticsearchRepositories(basePackages = "com.example.searchservice.repository")
public class ElasticSearchConfiguration {
	/*
	 * @Override public ClientConfiguration clientConfiguration() {
	 * 
	 * Supplier<HttpHeaders> headersSupplier = () -> { HttpHeaders headers = new
	 * HttpHeaders(); headers.add("Content-Type",
	 * "application/vnd.elasticsearch+json;compatible-with=8"); return headers; };
	 * 
	 * return ClientConfiguration.builder() .connectedTo("localhost:9200")
	 * .withHeaders(headersSupplier) .build(); }
	 */

	@Bean
    public ElasticsearchTransport elasticsearchTransport() {
        return new RestClientTransport(
            RestClient.builder(new HttpHost("localhost", 9200, "http"))
                .setDefaultHeaders(new Header[]{
                    new BasicHeader("Content-Type", "application/vnd.elasticsearch+json;compatible-with=8"), 
                    new BasicHeader("Accept", "application/vnd.elasticsearch+json;compatible-with=8")
                }) // Correct headers for ES 8+
                .build(),
            new JacksonJsonpMapper()
        );
    }

    @Bean
    public ElasticsearchClient elasticsearchClient(ElasticsearchTransport transport) {
        return new ElasticsearchClient(transport);
    }
}
