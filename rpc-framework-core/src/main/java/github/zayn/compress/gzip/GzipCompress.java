package github.zayn.compress.gzip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import github.zayn.compress.Compress;

/**
 * @ClassName GzipCompress
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/14 下午5:08
 **/
public class GzipCompress implements Compress {
    @Override
    public byte[] compress(byte[] bytes) {
        if (bytes == null) {
            throw new NullPointerException("bytes is null!");
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            GZIPOutputStream zip = new GZIPOutputStream(out);
            zip.write(bytes);
            zip.flush();
            zip.finish();
            return out.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("gzip compress error", e);
        }
    }

    @Override
    public byte[] unCompress(byte[] bytes) {
        if (bytes == null) {
            throw new NullPointerException("bytes is null!");
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            GZIPInputStream unzip = new GZIPInputStream(in);
            byte[] buffer = new byte[1024];
            int n;
            while ((n = unzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }

        } catch (IOException e) {
            throw new RuntimeException("gzip uncompress error", e);
        }
        return new byte[0];
    }
}
