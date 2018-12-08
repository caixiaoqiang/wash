package com.cookie.wash.enums;


import com.cookie.wash.result.RestStatus;

/**
 * 返回信息枚举定义
 * @author cxq
 * 公共的状态：000开头
 * 广告系统： 001开头
 *
 */
public enum ReturnInfoEnum implements RestStatus {

    /**
     * 成功
     */
    Success(RestEnum.common.getCode(), "000000", "成功"),
    /**
     * 失败
     */
    Fail(RestEnum.common.getCode(),"000001", "失败"),
    /**
     * 用户未登录
     */
    NotLogin(RestEnum.common.getCode(),"000999", "用户未登录"),
    /**
     * 无权限
     */
    NotRight(RestEnum.common.getCode(),"000998", "无权限"),
    /**
     * 参数错误
     */
    parameterError(RestEnum.common.getCode(), "000990","参数错误"),
    /**
     * 系统忙
     */
    UnknownError(RestEnum.common.getCode(),"999999", "系统忙");

    private String code;
    private String subcode;
    private String desc;

    ReturnInfoEnum(String code, String subcode, String desc) {
        this.code = code;
        this.subcode = subcode;
        this.desc = desc;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String desc() {
        return desc;
    }

    @Override
    public String subcode() {
        return subcode;
    }

}
