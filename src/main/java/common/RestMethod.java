package common;

public enum RestMethod {
    GET("GET"), POST("POST"), PUT("PUT"), DELETE("DELETE"), UPDATE("UPDATE");

    private String method;

    RestMethod(String method) {
        this.method = method;
    }

    public String method() {
        return this.method;
    }
}
