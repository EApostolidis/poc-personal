package com.example.commons.utility;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

import static com.example.commons.constant.CommonConstant.CORRELATION_ID;

public class CorrelationIdFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Attempt to retrieve the correlation ID from the incoming request
        String correlationId = request.getHeader(CORRELATION_ID);
        boolean newCorrelationId = false;

        // If the request does not have a correlation ID, generate and set one
        if (correlationId == null) {
            correlationId = UUID.randomUUID().toString();
            newCorrelationId = true;
        }

        // Set the correlation ID in the response header as well, if it's new
        if (newCorrelationId) {
            response.addHeader(CORRELATION_ID, correlationId);
        }

        // Use MDC to make correlation ID available to the application for logging
        org.slf4j.MDC.put(CORRELATION_ID, correlationId);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // Optionally, implement logic to skip filtering for certain requests
        return false;
    }
}
