package com.example.fs.io;

/**
 * Class that represents response from IOHandler
 */
public class Response {

    private String responseContent;
    private String message;
    private int responseCode;
    private String responseHeaderRateLimit;
    private String responseHeaderRateLimitRemaining;

    public Response(String responseContent, int responseCode, String message) {
        this.responseCode = responseCode;
        this.responseContent = responseContent;
        this.message = message;
    }

    public Response(String responseContent, int responseCode, String message, String responseHeaderRateLimit, String responseHeaderRateLimitRemaining) {
        this.responseCode = responseCode;
        this.responseContent = responseContent;
        this.message = message;
        this.responseHeaderRateLimit = responseHeaderRateLimit;
        this.responseHeaderRateLimitRemaining = responseHeaderRateLimitRemaining;
    }

    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseHeaderRateLimit() {
        return responseHeaderRateLimit;
    }

    public void setResponseHeaderRateLimit(String responseHeaderRateLimit) {
        this.responseHeaderRateLimit = responseHeaderRateLimit;
    }

    public String getResponseHeaderRateLimitRemaining() {
        return responseHeaderRateLimitRemaining;
    }

    public void setResponseHeaderRateLimitRemaining(String responseHeaderRateLimitRemaining) {
        this.responseHeaderRateLimitRemaining = responseHeaderRateLimitRemaining;
    }
}
