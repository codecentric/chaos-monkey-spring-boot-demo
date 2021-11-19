package de.codecentric.cm4sb.demo.outgoing.configuration;

import io.netty.channel.ChannelOption;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import java.time.Duration;

@Configuration
class MovieSuccessorWebclientConfiguration {

    @Bean
    public WebClient movieSuccessorWebClient() {
        var httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                .responseTimeout(Duration.ofSeconds(1));
        return WebClient.builder()
                .baseUrl("https://httpbin.org")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}