package work.ccpw.community.enums;

/**
 * @program: community
 * @description:
 * @author: cone
 * @create: 2020-03-22 14:00
 **/
public enum NotificationStatusEnum {

    UNREAD(0), READ(1);
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
