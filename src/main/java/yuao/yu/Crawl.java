package yuao.yu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import edu.uci.ics.crawler4j.url.URLCanonicalizer;
import edu.uci.ics.crawler4j.url.WebURL;


public class Crawl extends CrawlController{
	 static final Logger logger = LoggerFactory.getLogger(CrawlController.class);
	public Crawl(CrawlConfig config, PageFetcher pageFetcher, RobotstxtServer robotstxtServer) throws Exception {
		super(config, pageFetcher, robotstxtServer);
		// TODO Auto-generated constructor stub
	}
	public void addSeed(String pageUrl, int docId) {
	    String canonicalUrl = URLCanonicalizer.getCanonicalURL(pageUrl);
	    if (canonicalUrl == null) {
	      logger.error("Invalid seed URL: {}", pageUrl);
	    } else {
	      if (docId < 0) {
	        docId = docIdServer.getDocId(canonicalUrl);
	        if (docId > 0) {
	          logger.trace("This URL is already seen.");
	          return;
	        }
	        docId = docIdServer.getNewDocID(canonicalUrl);
	      } else {
	        try {
	          docIdServer.addUrlAndDocId(canonicalUrl, docId);
	        } catch (Exception e) {
	          logger.error("Could not add seed: {}", e.getMessage());
	        }
	      }

	      WebURL webUrl = new WebURL();
	      webUrl.setURL(canonicalUrl);
	      webUrl.setDocid(docId);
	      webUrl.setDepth((short) 0);
	      //if (robotstxtServer.allows(webUrl)) {
	        frontier.schedule(webUrl);
	      //} else {
	        //logger.warn("Robots.txt does not allow this seed: {}",
	          //          pageUrl); // using the WARN level here, as the user specifically asked to add this seed
	      }
	    }
	  }


