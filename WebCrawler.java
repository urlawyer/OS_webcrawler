//IMPORTANT: NEED TO IMPORT JSOUP.JAR FILE FOR CODE TO WORK
//JSOUP found here: https://jsoup.org/download

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
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
    private Set<String> links = ConcurrentHashMap.newKeySet(); // Thread-safe set for links
    
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
                    if(!links.contains(nextLink)) {
                        links.add(nextLink);
                        pullTheLeverKronk(depth + 1, nextLink); // Corrected depth increment
                    }
                }
            } else {
                System.out.println("Document return null in request() method");
            }
        }
    }
    
    //method to grab webpages
    private Document getLink(String url) {
        try {
            Connection con = Jsoup.connect(url).userAgent("Mozilla/5.0");
            Document doc = con.get();
            System.out.println("\nThreadID: " + threadID + ", Link found: " + url);
            System.out.println(doc.title());
            
            return doc;
        } catch(IOException e) {
            System.out.println("Error fetching URL: " + url);
            return null;
        }
    }
}
