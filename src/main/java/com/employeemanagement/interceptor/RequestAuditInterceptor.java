package com.employeemanagement.interceptor;

import com.employeemanagement.common.ApplicationErrorCodes;
import com.employeemanagement.exception.ServiceException;
import com.employeemanagement.service.RequestResponseAuditService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Component
public class RequestAuditInterceptor implements HandlerInterceptor {

    private final RequestResponseAuditService requestResponseAuditService;

    public RequestAuditInterceptor(RequestResponseAuditService requestResponseAuditService) {
        this.requestResponseAuditService = requestResponseAuditService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(request instanceof ContentCachingRequestWrapper)) {
            request = new ContentCachingRequestWrapper(request);
        }
        if (!(response instanceof ContentCachingResponseWrapper)) {
            response = new ContentCachingResponseWrapper(response);
        }

        request.setAttribute("wrappedRequest", request);
        request.setAttribute("wrappedResponse", response);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ContentCachingRequestWrapper wrappedRequest = (ContentCachingRequestWrapper) request.getAttribute("wrappedRequest");
        ContentCachingResponseWrapper wrappedResponse = (ContentCachingResponseWrapper) request.getAttribute("wrappedResponse");

        if (wrappedRequest != null && wrappedResponse != null) {

            // Get request body
            String requestPayload = new String(wrappedRequest.getContentAsByteArray(), request.getCharacterEncoding());
            // Get response body
            String responsePayload = new String(wrappedResponse.getContentAsByteArray(), response.getCharacterEncoding());

            // Determine if there was an error
            String error = (ex != null) ? ex.getMessage() : null;

            // Save the log
            try {
                requestResponseAuditService.saveAuditDetails(request.getRequestURI(), requestPayload, requestPayload, error);
            } catch (ServiceException e) {
                throw new ServiceException(ApplicationErrorCodes.SERVICE_EXCEPTION_ERROR);
            }

            // Copy the response body back to the response to send it to the client
            wrappedResponse.copyBodyToResponse();
        }
    }
}
