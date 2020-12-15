package github.zayn.remoting.netty.server;

import github.zayn.factory.SingletonFactory;
import github.zayn.remoting.handler.RpcRequestHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @ClassName NettyRpcServerHandle
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/14 下午8:36
 **/
public class NettyRpcServerHandler extends ChannelInboundHandlerAdapter {

    private final RpcRequestHandler rpcRequestHandler;

    public NettyRpcServerHandler() {
        this.rpcRequestHandler = SingletonFactory.getInstance(RpcRequestHandler.class);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ctx.fireChannelRead(msg);
    }

}
