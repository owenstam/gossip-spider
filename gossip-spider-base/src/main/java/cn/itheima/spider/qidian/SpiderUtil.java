package cn.itheima.spider.qidian;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;


public class SpiderUtil {


    @Test
    public void getQiDianFreeList() throws Exception {


        String url = "https://www.qidian.com/free/all?orderId=&amp;vip=hidden&amp;style=1&amp;pageSize=20&amp;siteid=1&amp;pubflag=0&amp;hiddenField=1&amp;page=1";

        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.80 Safari/537.36");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(httpGet);
        int statusCode = response.getStatusLine().getStatusCode();
        if(statusCode==200){
            HttpEntity httpEntity = response.getEntity();
            String html = EntityUtils.toString(httpEntity, "UTF-8");
            Document document = Jsoup.parse(html);
            //all-img-list cf
            Elements liEls = document.select("[class=all-img-list cf] li");
            for (Element liEl : liEls) {
                Elements div_a = liEl.select("div a");
                for(Element a:div_a) {
                    if (a.text() != "" && !a.text().equals("")) {
                        System.out.println(a.text());
                    }
                }
                System.out.println("===================================");
            }
        }

    }
}
