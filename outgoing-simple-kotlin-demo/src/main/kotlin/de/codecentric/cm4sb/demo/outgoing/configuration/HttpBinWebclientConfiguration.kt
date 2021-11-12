package de.codecentric.cm4sb.demo.outgoing.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class HttpBinWebclientConfiguration {

    @Bean
    fun httpBinWebClient(): WebClient {
        return WebClient.create("https://httpbin.org");
    }
}