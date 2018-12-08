package com.cookie.wash.result;

/**
 * Created by cxq on 2017/6/7.
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
        common("000000", "common");

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
