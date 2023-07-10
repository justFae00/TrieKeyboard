package TrieTree;

import java.util.ArrayList;
import java.util.List;

class TrieNode {
    TrieNode[] children;
    boolean isWord;


    public TrieNode() {
        children = new TrieNode[27]; // Assuming only lowercase letters + '-'
        isWord = false;
    }


    //insert words to trie
    public void insert(String word) {
        TrieNode currentNode = this;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            //check if the character is alphabetic or '-'
            if (Character.isAlphabetic(ch) || ch == '-') {
                //convert character to index
                int index;
                if (Character.isAlphabetic(ch)) {
                    index = Character.toLowerCase(ch) - 'a'; //convert alphabetic character to index (assuming lowercase)
                } else {
                    index = 26; //index for '-' (assuming it is the last index)
                }

                if (currentNode.children[index] == null) {
                    currentNode.children[index] = new TrieNode();
                }

                currentNode = currentNode.children[index];
            }
        }

        currentNode.isWord = true;
    }


    //look through the trie to see if the prefix exists
    public TrieNode searchPrefix(String prefix) {
        TrieNode node = this;
        for (char ch : prefix.toCharArray()) {
            int index = ch - 'a';
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        return node;
    }


    //use the same method as searchPrefix but for the full word, returns boolean
    public boolean completeSearch(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isWord;
    }


    //find list of words that
    public List<String> partialSearch(String prefix) {
        List<String> results = new ArrayList<>();
        TrieNode current = this;
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            if (current.children[index] == null) {
                return results;
            }
            current = current.children[index];
        }
        searchWords(current, prefix, results);
        return results;
    }


    //DFS traversal of the trie, collecting all the words stored in the trie and adding them to the results list
    private void searchWords(TrieNode node, String prefix, List<String> results) {
        if (node.isWord) {
            results.add(prefix);
        }
        for (int i = 0; i < 27; i++) {
            if (node.children[i] != null) {
                char c = (char) ('a' + i);
                searchWords(node.children[i], prefix + c, results);
            }
        }
    }


    //find the longest prefix in an incorrect string
    String getLongestPrefix(String word) {
        StringBuilder prefix = new StringBuilder();
        TrieNode current = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (current.children[index] == null) {
                break; //no more matching characters found
            }
            current = current.children[index];
            prefix.append(ch);
            if (current.isWord) {
                return prefix.toString();
            }
        }
        return prefix.toString();
    }


}