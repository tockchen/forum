package work.ccpw.community.exception;

/**
 * @program: community
 * @description: 枚举实现接口
 * @author: cone
 * @create: 2020-03-16 12:57
 **/
public enum CustomizeErrorCode implements ICustomizeErrorCode{
    /**
     *
     */
    QUESTON_NOT_FOUND("你更新的问题不在了,要不要换个试试?");

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
