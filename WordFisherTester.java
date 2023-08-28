public class WordFisherTester {
	
	public static void main(String[] args) {
		
		WordFisher alice = new WordFisher("texts/carroll-alice.txt", "stopwords.txt");
		
		WordFisher moby = new WordFisher("texts/moby-dick.txt", "stopwords.txt");
		
	}
}
