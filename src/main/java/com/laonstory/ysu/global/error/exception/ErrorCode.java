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

    // AUTH
    SMS_NOT_FOUND(400, "A001", "인증번호 발송 후 진행해주세요."),
    SMS_CODE_NOT_MATCHED(400, "A002", "인증번호가 올바르지 않습니다."),
    EXPIRED_CODE(400, "A003", "인증번호가 만료되었습니다.\n 문자인증을 다시 진행해주세요."),
    ALREADY_USED_CODE(400, "A003", "이미 사용된 인증번호입니다.\n 문자인증을 다시 진행해주세요."),

    // Resource
    FILE_SAVE_ERROR(400, "R001", "파일 저장에 실패하였습니다."),
    FILE_DELETE_ERROR(400, "R001", "파일 삭제에 실패하였습니다."),

    // Member
    EMAIL_DUPLICATION(400, "M001", "중복된 이메일 입니다."),
    STUDENT_ID_DUPLICATION(400, "M002", "중복된 학번 입니다."),
    STUDENT_ID_INPUT_INVALID(400, "M003", "학번을 획인해주세요."),
    PASSWORD_INPUT_INVALID(400, "M004", "비밀번호를 획인해주세요."),
    CHECK_USER_INVALID(400, "M005", "해당 정보에 맞는 유저가 없습니다."),
    PHONE_DUPLICATION(400, "M006", "중복된 전화번호 입니다."),
    ALREADY_WITHDRAW_USER(400, "M007", "탈퇴된 회원입니다.\n 탈퇴일 기준으로 7일 뒤 부터 가입할 수 있습니다."),
    USER_HAS_ADMIN_ROLE(400, "M008", "회원 탈퇴가 불가능합니다.\n매니저 계정 삭제 후, 다시 시도해 주세요."),
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
