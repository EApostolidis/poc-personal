package com.example.commons.configuration;

import com.example.commons.model.CommonResponseDto;
import com.example.commons.model.ErrorDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import static com.example.commons.constant.CommonConstant.CORRELATION_ID;

@RequiredArgsConstructor
@Slf4j
public class RestTemplateComponent {

    private final RestTemplate restTemplate;

    public CommonResponseDto<Object> restCall(String url, HttpMethod method,
                                              @Nullable HttpEntity<?> requestEntity, ParameterizedTypeReference<?> responseType) {
        final String correlationID = org.slf4j.MDC.get(CORRELATION_ID);
        try {
            ResponseEntity<?> response = restTemplate.exchange(url,
                    method,
                    requestEntity,
                    responseType);
            return CommonResponseDto.builder()
                            .data(response.getBody())
                            .correlationId(correlationID)
                            .build();
        } catch (RestClientResponseException e) {
            ErrorDetails errorDetails = e.getResponseBodyAs(ErrorDetails.class);
            return CommonResponseDto.builder()
                            .errorDetails(errorDetails)
                            .correlationId(correlationID)
                            .build();
        } catch (Exception e) {
            return CommonResponseDto.builder()
                            .message("An error occurred: " + e.getMessage())
                            .correlationId(correlationID)
                            .build();
        }
    }
}
