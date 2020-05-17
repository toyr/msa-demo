package com.toyr.msa.zipkin.common;

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.http.HttpRequest;
import com.github.kristofa.brave.http.SpanNameProvider;
import com.github.kristofa.brave.mysql.MySQLStatementInterceptorManagementBean;
import com.github.kristofa.brave.spring.BraveClientHttpRequestInterceptor;
import com.github.kristofa.brave.spring.ServletHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import zipkin.Span;
import zipkin.reporter.AsyncReporter;
import zipkin.reporter.Reporter;
import zipkin.reporter.Sender;
import zipkin.reporter.okhttp3.OkHttpSender;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author unisk1123
 * @Description
 * @create 2020-05-17 2:04 AM
 */
@Configuration
@Import({RestTemplate.class, BraveClientHttpRequestInterceptor.class, ServletHandlerInterceptor.class})
public class ZipKinConfiguration implements WebMvcConfigurer {


    @Autowired
    private ZipKinProperties zipKinProperties;

    @Bean
    public Sender sender() {
        return OkHttpSender.create(zipKinProperties.getEndpoint());
    }

    @Bean
    public Reporter<Span> reporter() {
        return AsyncReporter.builder(sender()).build();
    }

    @Bean
    public Brave brave() {
        return new Brave.Builder(zipKinProperties.getService()).reporter(reporter()).build();
    }

    @Bean
    public SpanNameProvider spanNameProvider() {
        return new SpanNameProvider() {
            @Override
            public String spanName(HttpRequest httpRequest) {
                return String.format("%s %s", httpRequest.getHttpMethod(), httpRequest.getUri().getPath());
            }
        };
    }

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BraveClientHttpRequestInterceptor clientInterceptor;

    @Autowired
    private ServletHandlerInterceptor serverInterceptor;


    @PostConstruct
    public void init() {
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        interceptors.add(clientInterceptor);
        restTemplate.setInterceptors(interceptors);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(serverInterceptor);
    }

    @Bean
    public MySQLStatementInterceptorManagementBean mySQLStatementInterceptorManagementBean() {
        return new MySQLStatementInterceptorManagementBean(brave().clientTracer());
    }
}
