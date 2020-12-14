package github.zayn.compress;

/**
 * @ClassName Compress
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/14 下午5:04
 **/
public interface Compress {
    byte[] compress(byte[] bytes);

    byte[] unCompress(byte[] bytes);

}
