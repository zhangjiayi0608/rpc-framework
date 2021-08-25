package github.zayn.exception;


import github.zayn.enums.RpcErrorMessageEnum;

/**
  * @ClassName
  * @DESCRIPTION TODO
  * @Author zhangjiayi07
  * @Date 2021/5/30 5:09 下午
  **/
public class RpcException extends RuntimeException {
    public RpcException(RpcErrorMessageEnum rpcErrorMessageEnum, String detail) {
        super(rpcErrorMessageEnum.getMessage() + ":" + detail);
    }

    public RpcException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpcException(RpcErrorMessageEnum rpcErrorMessageEnum) {
        super(rpcErrorMessageEnum.getMessage());
    }
}
