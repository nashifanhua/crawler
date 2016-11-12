package yuao.yu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.csvreader.CsvWriter;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;


public class MyCrawler extends WebCrawler {

	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg" + "|png|mp3|mp3|zip|gz))$");

	/**
	 * This method receives two parameters. The first parameter is the page in
	 * which we have discovered this new url and the second parameter is the new
	 * url. You should implement this function to specify whether the given url
	 * should be crawled or not (based on your crawling logic). In this example,
	 * we are instructing the crawler to ignore urls that have css, js, git, ...
	 * extensions and to only accept urls that start with
	 * "http://www.ics.uci.edu/". In this case, we didn't need the referringPage
	 * parameter to make the decision.
	 */
	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String href = url.getURL().toLowerCase();
		//System.out.println("a");
		return !FILTERS.matcher(href).matches() && (href.startsWith("http://sh.lianjia.com/ershoufang/")||href.startsWith("https://www.zhihu.com/"));
		
	}

	/**
	 * This function is called when a page is fetched and ready to be processed
	 * by your program.
	 */
	@Override
	public void visit(Page page) {
		String url = page.getWebURL().getURL();
		String ur2 = "(" + url + ")";
		System.out.println("URL: " + url);

		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			String text = htmlParseData.getText();
			String html = htmlParseData.getHtml();
			String tiles = htmlParseData.getTitle();
		
			System.out.println("a");
		Document doc = null;
		doc = Jsoup.parse(html,"utf-8");

	    File csvwrite=new File("C://Users//lenovo//Desktop//lianjia//infromation.csv");
		FileWriter write = null;
		try {
			write = new FileWriter(csvwrite, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CsvWriter out=new CsvWriter(write,',');
		
		Elements li=doc.select("ul.house-lst").select("li");
	 for(org.jsoup.nodes.Element div:li){
		    String jieshao=div.	select("div.where").text().replace("   ", ",");
		    String price=div.select("span.num").first().text();
		    String pirce_pre=div.select("div.price-pre").text().replace("元/平", "");
		    String subwayi=div.select("span.fang-subway-ex").text();
		    String position=div.select("div.con").text();
		    String key=div.	select("div.pic-panel").select("a").attr("key");
		    try {
				out.write(jieshao);
			
		    out.write(price);
		    out.write(pirce_pre);
		 	out.write(subwayi);
			out.write(position);
			out.write(key);
			out.endRecord();
		    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		    System.out.println(jieshao+";"+subwayi+";"+position+";"+key+";"+price+";"+pirce_pre);
	    }
	        out.close();
			/*String regex="路人甲";
			Matcher m=Pattern.compile(regex).matcher(tiles);
			if(!m.find()){
				return;
			}
			
			/*int i = 0;
			File filehtml = new File("G://html//" + tiles + ".html");
			if (filehtml.exists()) {
				while (filehtml.exists()) {
					
					filehtml = new File("G://html//index" + tiles + i + ".html");
				}
			}
			System.out.println(i);
			System.out.println(tiles);

			//BufferedWriter out = null;
		/*	try {
				out = new BufferedWriter(new FileWriter(filehtml,true));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                 
			try {
				out.write(html);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			Set<WebURL> links = htmlParseData.getOutgoingUrls();
			// System.out.println(links);
			System.out.println("Text length: " + text.length());
			System.out.println("Html length: " + html.length());
			System.out.println("Number of outgoing links: " + links.size());
		}
	}
}