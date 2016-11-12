package yuao.yu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;

import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Controller {
    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = "G:\\html";
        int numberOfCrawlers = 7;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        //config.setMaxDepthOfCrawling(numberOfCrawlers);
        
        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        robotstxtConfig.setEnabled(false);
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
       
        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */
   /*     
     controller.addSeed("http://www.lagou.com/zhaopin/Java/4/?filterOption=3");   
     controller.addSeed("http://www.lagou.com/zhaopin/Java/3/?filterOption=3");
     controller.addSeed("http://www.lagou.com/zhaopin/Java/2/?filterOption=2");
     controller.addSeed("http://www.lagou.com/zhaopin/Java/?labelWords=label");
     controller.addSeed("http://www.lagou.com/zhaopin/Java/5/?filterOption=3");
     controller.addSeed("http://www.lagou.com/zhaopin/Java/6/?filterOption=3");
     controller.addSeed("http://www.lagou.com/zhaopin/Java/7/?filterOption=3");
     controller.addSeed("http://www.lagou.com/zhaopin/Java/8/?filterOption=3");
     controller.addSeed("http://www.lagou.com/zhaopin/Java/9/?filterOption=3");
     controller.addSeed("http://www.lagou.com/zhaopin/Java/10/?filterOption=3");
     */
     controller.addSeed("http://sh.lianjia.com/ershoufang/");
   
   
        controller.start(MyCrawler.class, numberOfCrawlers);
        
    }
}
