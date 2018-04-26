package exercise1;

public class tes {
	public static void main(String[] a){
	    TextAnalysis ta = new TextAnalysis("C:\\Users\\Andreas\\Downloads\\hamlet.txt", 50000);
	       System.out.println("word count = " + ta.wordCount());
	       System.out.println("different words = " + ta.getNoOfDifferentWords());
	       System.out.println("repetitions = " + ta.getNoOfRepetitions());
	   }
}
