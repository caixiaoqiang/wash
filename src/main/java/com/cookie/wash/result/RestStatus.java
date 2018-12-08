package com.cookie.wash.result;

/**
 * Created by chenlei on 2017/6/7.
 */
public interface RestStatus {

    String code();

    String subcode();
    /**
     * @return status enum name
     */
    String name();

    /**
     * @return message summary
     */
    String desc();

    public enum RestEnum{
        common("000000", "common"),
        pay("000001", "pay项目"),
        activity("000002", "activity项目"),
        message("000003", "message项目"),
        account("000004", "account项目"),
        system("000005", "system项目"),
        resource("000006", "resource项目"),
        timeline("000007", "timeline项目"),
        notice("000008", "notice项目"),
        callback("000009", "callback项目"),
        analysis("000010", "analysis项目");

        private String code;
        private String desc;

        RestEnum(String code, String desc) {
            this.code = code;
            this.desc = desc;
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
    }
}
