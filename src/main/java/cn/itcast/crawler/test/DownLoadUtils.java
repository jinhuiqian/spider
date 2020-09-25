package cn.itcast.crawler.test;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author 231
 * @date 2020-09-25 11:17
 */
public class DownLoadUtils {

    public DownLoadUtils (){
    }

    public void downLoadPic (String url,String path){
        InputStream in = null;
        OutputStream os = null;
        URLConnection urlConnection = null;

        try {
            URL httpUrl = new URL(url);
            urlConnection = httpUrl.openConnection();
            in = urlConnection.getInputStream();
            // 数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            os = new FileOutputStream(path);
            // 开始读取
            while ((len = in.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                in.close();
                os.close();
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }
    }

