package projects.dictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final Dictionary dictionary = Dictionary.getInstance();

        //add word no synonyms
        final Word firstBook = new Word("book");
        final WordDescription firstBookDescription = new WordDescription("notebook", null);
        final boolean isFirstBookAdded = dictionary.addWord(firstBook, firstBookDescription);
        System.out.println("Result=" + isFirstBookAdded);
        System.out.println("Dictionary=" + Dictionary.getDictionary());


        //add book with synonyms in the dictionary
        final Word fourthWord = new Word("book");
        final WordDescription fourthBookDescription = new WordDescription("fourthB", createListOfWords(firstBook));
        final boolean isFourthBookAdded = dictionary.addWord(fourthWord, fourthBookDescription);
        System.out.println("Result=" + isFourthBookAdded);
        System.out.println("Dictionary=" + Dictionary.getDictionary());


        //remove existing  word  used as synonym
        final boolean isEightWordDeleted = dictionary.deleteWord(firstBook);
        System.out.println("Result=" + isEightWordDeleted);
        System.out.println("Dictionary=" + Dictionary.getDictionary());


        //search existing word
        final WordDescription existingDescription = dictionary.searchWord(new Word("fourthW"));
        System.out.println("Result=" + existingDescription);

        //word not found
        final List<Word> synonymsNotFound = dictionary.findSynonyms(new Word("not found"));
        System.out.println("Result=" + synonymsNotFound);
    }

    private static List<Word> createListOfWords(final Word... words) {
        final List<Word> wordsList = new ArrayList<>();
        wordsList.addAll(Arrays.asList(words));
        return wordsList;
    }
}