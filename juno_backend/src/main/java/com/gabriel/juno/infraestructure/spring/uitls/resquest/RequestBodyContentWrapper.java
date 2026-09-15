package com.gabriel.juno.infraestructure.spring.uitls.resquest;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;

public class RequestBodyContentWrapper extends ContentCachingRequestWrapper {
    private RequestBodyInputStreamWrapper bodyProcessor = new RequestBodyInputStreamWrapper(super.getInputStream());

    public RequestBodyContentWrapper(final HttpServletRequest request) throws IOException {
        super(request, request.getContentLength());
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return bodyProcessor;
    }

    public void cacheBodyCatcher() throws  IOException {
        super.getInputStream().readAllBytes();
        prepareInputStream();
    }

    public void prepareInputStream() {
        bodyProcessor.resetReading(super.getContentAsByteArray());
    }


}
