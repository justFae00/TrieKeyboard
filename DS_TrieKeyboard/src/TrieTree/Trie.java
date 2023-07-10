package TrieTree;

import java.util.*;

public class Trie {
    private static TrieNode root;
    public static Map<String, Integer> map = new HashMap<>();


    public Trie() {
        root = new TrieNode();
    }


    public void insert(Trie t, String word) {
        t.root.insert(word);
        map.put(word, 0);
    }


    //checks if the spell is right
    public static boolean spellCheck(Trie t, String word) {
        return t.root.completeSearch(word);
    }


    //searches through the trie with the given prefix
    public String[] autocomplete(Trie t, String prefix) {
        List<String> results = t.root.partialSearch(prefix);
        String[] result = results.toArray(new String[0]);
        return sortByFrequency(result);
    }


    //calculates the distance between two words
    public int distance(String word1, String word2) {
        int distance = 0;
        int len = Math.min(word1.length(), word2.length());
        for (int i = 0; i < len; i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                distance += 1;
            }
        }
        return distance + Math.abs(word1.length() - word2.length());
    }


    //sorts the array based on their frequency
    public String[] sortByFrequency(String[] suggestionWords) {
        String[] w = new String[0];
        Map<String, Integer> tempMap = new HashMap<>();
        for (String s : suggestionWords) {
            tempMap.put(s, findFrequency(s));
        }

        //convert the map to a list of entries
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(tempMap.entrySet());

        //create a custom comparator to compare values in descending order
        Comparator<Map.Entry<String, Integer>> valueComparator = (e1, e2) -> e2.getValue().compareTo(e1.getValue());

        //sort the list using the custom comparator
        Collections.sort(entryList, valueComparator);

        //create a LinkedHashMap to store the sorted entries
        Map<String, Integer> sortedMap = new LinkedHashMap<>();

        //iterate over the sorted list and put entries into the LinkedHashMap
        for (Map.Entry<String, Integer> entry : entryList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        //store data in an array
        int i = 0;
        String[] result = new String[suggestionWords.length];
        for (String s : sortedMap.keySet()) {
            result[i] = s;
            i++;
        }

        return result;
    }


    //sorts the array based on their distances
    public void sortByDistance(String misspelledWord, String[] suggestionWords) {
        Arrays.sort(suggestionWords, Comparator.comparingInt(s -> distance(misspelledWord, s)));
    }


    //returns string of suggested words
    public String[] correction(Trie trie, Trie rtrie, String word) {
        StringBuilder suggestions = new StringBuilder();

        String prefix = trie.root.getLongestPrefix(word);
        String suffix = rtrie.root.getLongestPrefix(AppInitilizer.reverseString(word));

        //look by prefix in trie
        for (String s : autocomplete(trie, prefix)) {
            suggestions.append(s).append(" ");
        }

        //look by suffix in reverse trie
        for (String s : autocomplete(rtrie, suffix)) {
            suggestions.append(AppInitilizer.reverseString(s)).append(" ");
        }

        //convert to string array
        String[] results = suggestions.toString().split(" ");

        //convert the array to a set to remove duplicates
        Set<String> uniqueWords = new HashSet<>(Arrays.asList(results));

        //convert the set back to an array
        String[] uniqueArray = uniqueWords.toArray(new String[uniqueWords.size()]);

        sortByDistance(word, uniqueArray);

        return uniqueArray;
    }


    public static void incFrequency(String input) {
        //find the input
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String word = entry.getKey();
            int frequency = entry.getValue();

            if (Objects.equals(word, input)) {
                //update the key by incrementing it
                map.put(word, frequency + 1);
            }
        }
    }


    //returns the frequency of given input
    public int findFrequency(String input) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String word = entry.getKey();
            int frequency = entry.getValue();

            if (Objects.equals(word, input)) {
                return frequency;
            }
        }
        return 0;
    }

}
