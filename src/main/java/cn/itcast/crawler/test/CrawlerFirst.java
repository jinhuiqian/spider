package cn.itcast.crawler.test;

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

/**
 * @author 231
 * @date 2020-09-24 22:57
 */
public class CrawlerFirst {

    public static void main(String[] args) throws Exception {
        String content = null;
        int a=0;
        while (a<=225){
            String topUrl = "https://movie.douban.com/top250?start="+a+"&filter=";
        a+=25;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(topUrl);
        httpget.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36");
        CloseableHttpResponse response = httpclient.execute(httpget);
        HttpEntity httpEntity = response.getEntity();
        content = EntityUtils.toString(httpEntity,"UTF-8");
        Document doc = Jsoup.parse(content);

        Elements elements=doc.select("div[class=item]");
        for(Element element : elements){
            String title = element.select("span[class=title]").first().text();
            String score = element.select("span[class=rating_num]").text();
            String posters = element.select("img").attr("src");
            String url = element.select("a").attr("href");

            FilmDao dao=new FilmDaoImpl();
            Film film=new Film();
            film.setTitle(title);
            film.setScore(score);
            film.setPosters(posters);
            film.setUrl(url);
            dao.add(film);

            DownLoadUtils dl = new DownLoadUtils();
            dl.downLoadPic(url,"D:\\download\\"+title+".webp");
        }
        }
    }
}
