//package com.nicho.filter;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.ReadListener;
//import javax.servlet.ServletInputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequestWrapper;
//import java.io.*;
//
///**
// * @author qiwl
// * @version 1.0
// * @description
// * @date 2021/2/25 19:25
// */
//
//public class HttpRequestWrapper extends HttpServletRequestWrapper {
//    private static Logger log = LoggerFactory.getLogger(HttpRequestWrapper.class);
//    private final String body;
//    private static final int BUFFER_LEN = 128;
//    public String getBody() {
//        return this.body;
//    }
//
//    public HttpRequestWrapper(HttpServletRequest request) throws IOException {
//        super(request);
//
//        StringBuilder stringBuilder = new StringBuilder();
//        BufferedReader bufferedReader = null;
//        try {
//            InputStream inputStream = request.getInputStream();
//            if (inputStream != null) {
//                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                char[] charBuffer = new char[BUFFER_LEN];
//                int bytesRead;
//                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
//                    stringBuilder.append(charBuffer, 0, bytesRead);
//                }
//            }
//        } catch (IOException ex) {
//            throw ex;
//        } finally {
//            if (bufferedReader != null) {
//                try {
//                    bufferedReader.close();
//                } catch (IOException ex) {
//                    throw ex;
//                }
//            }
//        }
//        body = stringBuilder.toString();
//     log.info("包装后的body--："+body);
//    }
//
//    @Override
//    public ServletInputStream getInputStream() throws IOException{
//        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
//        ServletInputStream servletInputStream = new ServletInputStream() {
//            public boolean isFinished() {
//                return false;
//            }
//            public boolean isReady() {
//                return false;
//            }
//            public void setReadListener(ReadListener readListener) {}
//            public int read() throws IOException {
//                return byteArrayInputStream.read();
//            }
//        };
//        return servletInputStream;
//    }
//
//    @Override
//    public BufferedReader getReader() throws IOException {
//        return new BufferedReader(new InputStreamReader(this.getInputStream()));
//    }
//}
