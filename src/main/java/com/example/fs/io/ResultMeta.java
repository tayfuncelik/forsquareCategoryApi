package com.example.fs.io;

public class ResultMeta {

    private Integer code;
    private String errorType;
    private String errorDetail;
    private String rateLimit;
    private String rateLimitRemaining;

    public ResultMeta(Integer code, String errorType, String errorDetail) {
        this.code = code;
        this.errorType = errorType;
        this.errorDetail = errorDetail;
    }

    public ResultMeta(Integer code, String errorType, String errorDetail, String rateLimit, String rateLimitRemaining) {
        this.code = code;
        this.errorType = errorType;
        this.errorDetail = errorDetail;
        this.rateLimit = rateLimit;
        this.rateLimitRemaining = rateLimitRemaining;
    }


    @Override
    public String toString() {
        return "ResultMeta{" +
                "code=" + code +
                ", errorType='" + errorType + '\'' +
                ", errorDetail='" + errorDetail + '\'' +
                ", rateLimit='" + rateLimit + '\'' +
                ", rateLimitRemaining='" + rateLimitRemaining + '\'' +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }

    public String getRateLimit() {
        return rateLimit;
    }

    public void setRateLimit(String rateLimit) {
        this.rateLimit = rateLimit;
    }

    public String getRateLimitRemaining() {
        return rateLimitRemaining;
    }

    public void setRateLimitRemaining(String rateLimitRemaining) {
        this.rateLimitRemaining = rateLimitRemaining;
    }
}
