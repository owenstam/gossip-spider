package cn.itheima.spider.jdk;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class JdkHttpGet {
    public static void main(String[] args) throws Exception {
        String strUrl = "http://www.itcast.cn/";
        URL url = new URL(strUrl);
        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
        urlConnection.setRequestMethod("GET");
        InputStream inputStream = urlConnection.getInputStream();
        byte[] b = new byte[1024];
        int len = 0;
        while ((len=inputStream.read(b))!=-1){
            System.out.println(new String(b,0,len));
        }
        inputStream.close();
    }
}
