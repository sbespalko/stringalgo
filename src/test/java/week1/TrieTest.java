package week1;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class TrieTest {

  @Test
  void buildTrie() {
    Trie trie = new Trie();
    List<Map<Character, Integer>> result = trie.buildTrie(new String[]{"ATAGA", "ATC", "GAT"});
    trie.print(result);
  }
}

