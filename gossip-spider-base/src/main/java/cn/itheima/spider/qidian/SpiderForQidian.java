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

import java.io.*;

public class SpiderForQidian {
    public static void main(String[] args) throws Exception {
        String url = "https://read.qidian.com/chapter/L-tItW0qtJ3xq9ZHzk0vMw2/ig1PGE91Y3ROBDFlr9quQA2";
        while (true){
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.80 Safari/537.36");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(httpGet);

        int statusCode = response.getStatusLine().getStatusCode();
        if(statusCode==200) {

            HttpEntity httpEntity = response.getEntity();
            String html = EntityUtils.toString(httpEntity, "UTF-8");
            Document document = Jsoup.parse(html);


            Elements h3Els = document.select(".j_chapterName");
            String titel = h3Els.text();
            StringBuilder sb = new StringBuilder();
            sb.append(titel);
            sb.append(System.lineSeparator());

            Elements pEls = document.select("[class=read-content j_readContent] p");


            for (Element pEl : pEls) {
                sb.append(pEl.text().replaceAll("　　",""));
                sb.append(System.lineSeparator());
            }

            System.out.println(titel+"输出。。。");

            toFile("1.txt",sb);

            System.out.println(titel+"输出完成");

            Thread.sleep(500);

            httpClient.close();

            Elements aEl = document.select("#j_chapterNext[href*=chapter]");
            if (aEl == null || aEl.size() == 0) {
                break;
            }
            String href = aEl.attr("href");


            url = "https:" + href;
            System.out.println(url);
        }
        }


    }

    public static void toFile(String fileName,StringBuilder sbText) throws Exception {
//        Reader reader = new StringReader(sbText.toString());
//        BufferedReader bufferedReader = new BufferedReader(reader);
        OutputStream outputStream = new FileOutputStream(fileName,true);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        bufferedOutputStream.write(sbText.toString().getBytes());
        bufferedOutputStream.close();
        outputStream.close();

    }
}
