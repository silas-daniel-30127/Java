package projects.dictionary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Dictionary {
    private static Dictionary INSTANCE;
    private static Map<Word, WordDescription> dictionary;

    private Dictionary() {

    }

    public static Dictionary getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new Dictionary();
            dictionary = new HashMap<>();
        }
        return INSTANCE;
    }

    public static Map<Word, WordDescription> getDictionary() {
        return dictionary;
    }

    /**
     * Delete a word from the dictionary
     *
     * @param word instance of (@link Word) object to be added
     * @return true if the word was found and succesfully deleted or false if a particular synonyme was not found
     */
    public boolean deleteWord(final Word word) {
        if (!dictionary.containsKey(word)) {
            System.out.println(" Word having the  value" + word.getValue() + "not found");
            return false;
        }
        dictionary
                .forEach((key, wordDescription) -> {
                    if (Objects.nonNull(wordDescription.getSynonyms())) {
                        wordDescription.getSynonyms().remove(word);
                    }
                });
        //remove word from dictionary
        dictionary.remove(word);
        return true;
    }

    /**
     * Set description for a particular word
     *
     * @param word-instance of (@link word)object to bea searched
     * @return instance of (@link WordDescription) or null if not found
     */
    public WordDescription searchWord(final Word word) {
        return dictionary.get(word);
    }


    /**
     * Check if all provided words exist in the dictionary
     *
     * @param words- list of words to be validated
     * @return true if all words already exists in the dictionary, false otherwise
     */
    private boolean areWordsInDictionary(final List<Word> words) {
        if (Objects.isNull(words)) {
            return true;
        }
        return dictionary.keySet().containsAll(words);
    }

    /**
     * Add new word in dictionary
     *
     * @param word-instance            of (@link Word) object to be added
     * @param wordDescription-instance of (@link WordDescription) to be added with provided word
     * @return true if the word was succesfully added or false if a particular synonyme was not found in the dictionary
     */
    public boolean addWord(final Word word, final WordDescription wordDescription) {
        Objects.requireNonNull(word, "null");
        Objects.requireNonNull(wordDescription, "null");
        if (this.areWordsInDictionary(wordDescription.getSynonyms())) {
            System.out.println(" synonime not EXISTS in dictionary.");
            return false;
        }
        dictionary.put(word, wordDescription);
        return true;
    }

    /**
     * Find synonyms for a particular word
     *
     * @param word instance of (@link word) object to be searched
     * @return null if the word not found, or no synonyms are found for it
     */
    public List<Word> findSynonyms(final Word word) {
        if (dictionary.containsKey(word)) {
            return dictionary.get(word).getSynonyms();
        }
        return null;
    }

    /**
     * Clear all words from current dictionary
     */
    public void clearDictionary() {
        dictionary.clear();
    }
}
