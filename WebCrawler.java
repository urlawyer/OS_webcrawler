//IMPORTANT: NEED TO IMPORT JSOUP.JAR FILE FOR CODE TO WORK
//JSOUP found here: https://jsoup.org/download

import java.io.*;
import java.util.*;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.*;

public class WebCrawler extends Thread {
	
	//maxDepth so the program doesn't continue for forever, change as want
	private static final int maxDepth = 5;
	private int threadID;
	private String url;
	private ArrayList<String> links = new ArrayList<String>();
	
	//constructor
	public WebCrawler(int threadID, String url) {
		this.threadID = threadID;
		this.url = url;
	}
	
	//extending the Thread class requires run() override
	@Override
	public void run() {
		pullTheLeverKronk(1, url);
	}
	
	//method to crawl the webpage
	private void pullTheLeverKronk(int depth, String url) {
		
		//checking if method is entered
		System.out.println("Why do we even have that lever?");
		
		//stops when maxDepth is reached
		if (depth <= maxDepth) {
			System.out.println("Current Depth: " + depth);
			
			//grabbing webpage
			Document doc = getLink(url);
			
			if(doc != null) {
				
				//grabbing each link in webpage
				for(Element link : doc.select("a[href]")) {
					String nextLink = link.absUrl("href");
					if(links.contains(nextLink) == false) {
						pullTheLeverKronk(depth++, nextLink);
					}
				}
			} else {
				System.out.println("doc return null in request() method");
			}
		}
	}
	
	//method to grab webpages
	private Document getLink(String url) {
		try {
			Connection con = Jsoup.connect(url);
			Document doc = con.get();
			System.out.println("\n ThreadID: " + threadID + ", Link found: " + url);
			System.out.println(doc.title());
			links.add(url);
			
			return doc;
		} catch(IOException e) {
			return null;
		}
	}
}
