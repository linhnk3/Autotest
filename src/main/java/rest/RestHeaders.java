package rest;

import java.util.HashMap;

public class RestHeaders {
    private HashMap<String, String> headers;  //map object


    // Tạo Structor RestHeaders
    public RestHeaders() {
        this.headers = new HashMap<String, String>();
    }

    // Adđ Header cần add với key và value
    public RestHeaders addHeader(String key, String value) {
        headers.put(key, value);
        return this;
    }


    // Remove Header cần add với key và value
    public RestHeaders removeHeader(String key) {
        headers.remove(key);
        return this;
    }

    // Get danh sách Header từ HashMap đã được add và set trước đó ra
    public HashMap<String, String> getHeaders() {
        return headers;
    }
    }
