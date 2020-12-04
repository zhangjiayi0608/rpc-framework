package github.zayn.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @EnumName RemoteType
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/4 上午11:29
 **/
@AllArgsConstructor
@Getter
public enum RemoteTypeEnum {
    SOCKET(1, "socket"),
    NETTY(2, "netty"),
    DEFAULT(3, "default");

    private final int code;
    private final String name;

    public static String getName(byte code) {
        for (RemoteTypeEnum c : RemoteTypeEnum.values()) {
            if (c.getCode() == code) {
                return c.name;
            }
        }
        return null;
    }


}
