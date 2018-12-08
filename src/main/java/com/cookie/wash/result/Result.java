package com.cookie.wash.result;

/**
 * Created by chenlei on 2017/5/24.
 */
public class Result {
    private boolean success = true;
    private String code;
    private String subcode;
    private String message;
    private Long timestamp ;  // 时间戳

    public Result() {}

    public Result(boolean success, String code, String subcode, String message, Long timestamp) {
        this.success = success;
        this.code = code;
        this.subcode = subcode;
        this.message = message;
        this.timestamp = timestamp;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getSubcode() {
        return subcode;
    }

    public void setSubcode(String subcode) {
        this.subcode = subcode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
