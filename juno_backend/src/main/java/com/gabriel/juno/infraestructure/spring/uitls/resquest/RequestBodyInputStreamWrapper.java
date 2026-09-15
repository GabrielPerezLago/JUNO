package com.gabriel.juno.infraestructure.spring.uitls.resquest;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class RequestBodyInputStreamWrapper extends ServletInputStream {

    private ServletInputStream wrapper;
    private ByteArrayInputStream bais;

    public RequestBodyInputStreamWrapper(ServletInputStream wrapper)  throws  IOException {
        this.wrapper = wrapper;
        bais = new  ByteArrayInputStream(wrapper.readAllBytes());
    }

    public void resetReading(byte[] bytes) {
        this.bais = new ByteArrayInputStream(bytes);
    }

    @Override
    public int read() throws IOException {
        return bais.read();
    }

    @Override
    public boolean isFinished() {
        return bais == null || bais.available() == 0;
    }

    @Override
    public boolean isReady() {
        return bais != null;
    }

    @Override
    public void setReadListener(ReadListener readListener) {
        wrapper.setReadListener(readListener);
    }
}
