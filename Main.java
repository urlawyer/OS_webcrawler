
public class Main {
	
	public static void main (String[] args) {
		
		WebCrawler crawler1 = new WebCrawler(1, "https://abcnews.go.com");
		WebCrawler crawler2 = new WebCrawler(2, "https://www.npr.org");
		WebCrawler crawler3 = new WebCrawler(3, "https://www.nytimes.com");
		
		
		crawler1.start();
		crawler2.start();
		crawler3.start();
		
	}

}
