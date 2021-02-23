package com.laonstory.ysu.global.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "C001", "올바르지 않은 형식입니다."),
    METHOD_NOT_ALLOWED(405, "C002", "지원하지 않는 메소드입니다."),
    ENTITY_NOT_FOUND(400, "C003", "해당 엔티티를 찾을 수가 없습니다."),
    INTERNAL_SERVER_ERROR(500, "C004", "알 수 업는 에러 (서버 에러)"),
    INVALID_TYPE_VALUE(400, "C005", "타입이 올바르지 않습니다."),
    HANDLE_ACCESS_DENIED(403, "C006", "권한이 없습니다."),
    HANDLE_INVALID_TOKEN(401, "C007", "토큰이 없거나 올바르지 않습니다."),


    // Member
    EMAIL_DUPLICATION(400, "M001", "중복된 이메일 입니다."),
    STUDENT_ID_DUPLICATION(400, "M002", "중복된 학번 입니다."),
    STUDENT_ID_INPUT_INVALID(400, "M003", "학번을 획인해주세요."),
    PASSWORD_INPUT_INVALID(400, "M004", "비밀번호를 획인해주세요."),

    ;

    private int status;
    private final String code;
    private final String message;


    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }


}
