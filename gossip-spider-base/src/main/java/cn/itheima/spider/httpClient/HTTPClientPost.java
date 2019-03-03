package cn.itheima.spider.httpClient;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class HTTPClientPost {
    public static void main(String[] args) throws Exception {

        String indexUrl = "http://www.itcast.cn/";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(indexUrl);
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
        list.add(new BasicNameValuePair("username","test"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();
        if(statusCode==200){
            HttpEntity httpEntity = response.getEntity();
            String html = EntityUtils.toString(httpEntity);
            System.out.println(html);
        }

    }
}
