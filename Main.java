import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        List<String> urls = Arrays.asList(
                "https://icollege.gsu.edu/",
                "https://www.npr.org",
                "https://www.nytimes.com"
        );
        
        List<WebCrawler> crawlers = new ArrayList<>();
        for (int i = 0; i < urls.size(); i++) {
            crawlers.add(new WebCrawler(i + 1, urls.get(i)));
            crawlers.get(i).start();
        }
    }
}
