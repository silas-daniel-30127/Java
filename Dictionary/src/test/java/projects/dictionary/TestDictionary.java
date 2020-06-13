package projects.dictionary;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestDictionary {
    @Before
    public void SetUp() {
        Dictionary.getInstance().clearDictionary();
    }

    @Test
    public void testRemoveCardNotExists() {
        final Word word = new Word("not found ");
        final boolean result = Dictionary.getInstance().deleteWord(word);
        assertEquals("Result= ", false, result);
        assertEquals("Size=", 0, Dictionary.getDictionary().size());

    }

    @Test
    public void testSearchWordNotExists() {
        final Dictionary dictionary = Dictionary.getInstance();
        final WordDescription notFoundWord = dictionary.searchWord(new Word("not found "));
        assertEquals("Result=", null, notFoundWord);
    }

    @Test
    public void testFindSynonymsNoWordFound() {
        final Dictionary dictionary = Dictionary.getInstance();
        final List<Word> synonymsNotFound = dictionary.findSynonyms(new Word("not found "));
        assertEquals("Result=", null, synonymsNotFound);


    }

    @Test
    public void testAddWordNoSynonyms() {
        final Word word = new Word("word");
        final WordDescription wordDescription = new WordDescription(" word description", null);
        final boolean result = Dictionary.getInstance().addWord(word, wordDescription);

        assertEquals("Result=", false, result);
        assertEquals("Size=", 0, Dictionary.getDictionary().size());

    }
}