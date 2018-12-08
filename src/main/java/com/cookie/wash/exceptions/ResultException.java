/**
 *
 */
package com.cookie.wash.exceptions;


import com.cookie.wash.result.RestStatus;

import java.io.Serializable;

/**
 * @author cxq
 *
 */
public class ResultException extends Exception implements Serializable {

    private static final long serialVersionUID = 3857309446472590497L;

    private RestStatus restStatus;

    /**
     * 错误代码
     */
    private String code;
    /**
     * 错误代码
     */
    private String subcode;
    /**
     * 错误信息
     */
    private String message;
    private Exception ex;

    public ResultException(RestStatus restStatus, String message) {
        super();
        this.code = restStatus.code();
        this.subcode = restStatus.subcode();
        this.message = message;
    }

    public ResultException(RestStatus restStatus) {
        super();
        this.code = restStatus.code();
        this.subcode = restStatus.subcode();
        this.message = restStatus.desc();
    }

    public ResultException(String code, String message) {
        super();
        this.code = code;
        this.message = message;
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

    public Exception getEx() {
        return ex;
    }

    public void setEx(Exception ex) {
        this.ex = ex;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public RestStatus getRestStatus() {
        return restStatus;
    }

    public void setRestStatus(RestStatus restStatus) {
        this.restStatus = restStatus;
    }
}
