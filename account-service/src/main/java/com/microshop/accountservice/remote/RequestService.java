package com.microshop.accountservice.remote;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Service
public class RequestService implements IRequestService {

    private final RestTemplate restTemplate;

    public RequestService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public IRequest createRequest(final String applicationUrl) {
        return new Request(this, applicationUrl);
    }

    @Override
    public <T> T sendRequest(final IRequest request) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(request.getApplicationUrl() + request.getPath());
        setQueryParameters(request, builder);
        URI uri = builder.build().encode().toUri();
        HttpEntity<Object> entity = prepareRequestEntity(request.getBody());
        return (T) restTemplate.exchange(uri, request.getHttpMethod(), entity, request.getResponseType()).getBody();
    }

    private void setQueryParameters(final IRequest request, final UriComponentsBuilder builder) {
        for (Map.Entry<String, Object[]> entry : request.getQueryParameters().entrySet()) {
            builder.queryParam(entry.getKey(), entry.getValue());
        }
    }

    private HttpEntity<Object> prepareRequestEntity(final Object body) {
        return body != null ? new HttpEntity<>(body) : null;
    }
}