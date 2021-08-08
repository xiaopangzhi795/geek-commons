/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.commons.exception.enums;

/**
 * @ClassName: ExceptionTypeEnums
 * @Decription:
 * @Author: qian
 * qian create ExceptionTypeEnums.java of 2021/7/26 8:50 下午
 */
public enum ExceptionTypeEnums {
    COMMON_EXCEPTION("COMMON_EXCEPTION", "工具异常"),
    VALIDATOR_EXCEPTION("VALIDATOR_EXCEPTION", "校验异常"),
    BIZ_EXCEPTION("BIZ_EXCEPTION", "业务异常"),
    UN_KNOW_EXCEPTION("UN_KNOW_EXCEPTION", "未知异常"),
    OTHER_EXCEPTION("OTHER_EXCEPTION", "其他异常"),
    ;

    ExceptionTypeEnums(String type, String description) {
        this.setType(type);
        this.setDescription(description);
    }
    /**
     * 类型
     */
    private String type;
    /**
     * 说明
     */
    private String description;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
