import java.io.File;
import java.io.FileNotFoundException; 
import java.util.Scanner; 
import java.io.*;
import java.lang.*;
import java.util.*; 
import java.util.Comparator;

class TextAnalyzer {
    static void showWordList(List<Word> wordlist)
    {
        for(Word w2:wordlist)
        {
            System.out.print("Word: "+w2.getWord()+" ");
            System.out.println("Frequency: "+w2.getFreq());
        }
    }
    static void showFrequentWords(List<Word> wordlist)
    {
        List<Word> freqlist=new ArrayList<Word>();
        for(Word w2:wordlist)
        {
            freqlist.add(w2);
        }
        Collections.sort(freqlist, new Comparator<Word>() {
            @Override
            public int compare(Word p1, Word p2) {
                if (p2.getFreq() != p1.getFreq()) {
                    return p2.getFreq() - p1.getFreq();
                }
                return p1.getWord().toLowerCase().compareTo(p2.getWord().toLowerCase());
            }
        });
        showWordList(freqlist);
    }
    public static void main(String[] args) {
        LinkedHashMap<String,Integer> hm=new LinkedHashMap<String, Integer>();
        List<Word> wordlist=new ArrayList<Word>(); 
        try {
            File myObj = new File("filename.txt");
            Scanner myReader = new Scanner(myObj);
      
      while (myReader.hasNextLine()) {
        String data = myReader.next();
        hm.put(data, hm.getOrDefault(data, 0) + 1);
      }
      for (Map.Entry<String, Integer> entry: hm.entrySet()) {
            Word w1=new Word();
            w1.setWord(entry.getKey());
            w1.setFreq(entry.getValue());
            wordlist.add(w1);
            
        }
        System.out.println("**Words Found in File**");
       showWordList(wordlist); 
       System.out.println();
       System.out.println("**Words in Alphabetical order**");
       Collections.sort(wordlist, new Comparator<Word>() {
            @Override
            public int compare(Word p1, Word p2) {
            	return p1.getWord().toLowerCase().compareTo(p2.getWord().toLowerCase());
            }
        });
        showWordList(wordlist);
        System.out.println();
        System.out.println("**Words in Alphabetical Reverse order**");
        ListIterator<Word> itr= wordlist.listIterator(wordlist.size());
        while (itr.hasPrevious()) 
        {
            Word w2=itr.previous();
            System.out.print("Word: "+w2.getWord()+" ");
            System.out.println("Frequency: "+w2.getFreq());
        }
        System.out.println();
        System.out.println("**Descending order of frequency**");
        showFrequentWords(wordlist);
        myReader.close();
      
    } 
    catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    }
}
class Word{
    int freq;
    String word;
    Word()
    {
        freq=0;
        word="";
    }
    void setWord(String w)
    {
        word=w;
    }
    void setFreq(int f)
    {
        freq=f;
    }
    int getFreq()
    {
        return freq;
    }
    String getWord()
    {
        return word;
    }
}