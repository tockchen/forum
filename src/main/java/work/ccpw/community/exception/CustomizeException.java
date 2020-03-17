package work.ccpw.community.exception;

/**
 * @program: community
 * @description: 自定义异常
 * @author: cone
 * @create: 2020-03-16 11:58
 **/
public class CustomizeException extends RuntimeException {
    private String message;
    private Integer code;
    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

//    public CustomizeException(String message) {
//        this.message = message;
//    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
