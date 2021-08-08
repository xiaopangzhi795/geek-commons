/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.commons.exception.enums;

/**
 * @ClassName: ExceptionCodeExums
 * @Decription:
 * @Author: qian
 * qian create ExceptionCodeExums.java of 2021/7/26 9:28 下午
 */
public enum ExceptionCodeEnums {
    PARAM_ERROR("PARAM_ERROR", "参数异常"),
    COMMON_ERROR("COMMON_ERROR", "工具异常"),
    DB_ERROR("DB_ERROR", "数据库异常"),
    SYSTEM_ERROR("SYSTEM_ERROR", "系统异常"),
    NETWORK_ERROR("NETWORK_ERROR", "网络异常"),
    BIZ_ERROR("BIZ_ERROR", "业务异常"),
    UN_KNOW_ERROR("UN_KNOW_ERROR", "未知异常"),
    ;

    private String code;
    private String description;

    ExceptionCodeEnums(String code, String description) {
        this.setCode(code);
        this.setDescription(description);
    }
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public String buildDescription(String msg) {
        return String.format("%s:%s", this.description, msg);
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
