package cn.itheima.spider.httpClient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HTTPClientGet {
    public static void main(String[] args) throws IOException {
        String indexUrl = "http://www.itcast.cn/";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(indexUrl);
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.80 Safari/537.36");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        int statusCode = response.getStatusLine().getStatusCode();
        if(statusCode==200){
            HttpEntity httpEntity = response.getEntity();
            String html = EntityUtils.toString(httpEntity);
            System.out.println(html);
        }
        httpClient.close();

    }
}
