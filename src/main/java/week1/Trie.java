package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Trie {
  static public void main(String[] args) throws IOException {
    new Trie().run();
  }

  List<Map<Character, Integer>> buildTrie(String[] patterns) {
    List<Map<Character, Integer>> trie = new ArrayList<Map<Character, Integer>>();

    // write your code here
    int counter = 1;
    int lvl;
    trie.add(new HashMap<>());
    for (String pattern : patterns) {
      lvl = 0;
      Map<Character, Integer> currentNode = trie.get(lvl);
      for (char c : pattern.toCharArray()) {
        Integer nextLvl = currentNode.get(c);
        if (nextLvl != null) {
          currentNode = trie.get(nextLvl);
          lvl = nextLvl;
        } else {
          Map<Character, Integer> newNode = new HashMap<>();
          newNode.put(c, counter);
          trie.get(lvl).putAll(newNode);

          if (trie.size() <= counter) {
            for (int i = 0; i <= counter - trie.size(); i++) {
              trie.add(new HashMap<>());
            }
          }
          currentNode = trie.get(counter);
          lvl = counter;
          ++counter;
        }
      }
    }
    return trie;
  }

  public void print(List<Map<Character, Integer>> trie) {
    for (int i = 0; i < trie.size(); ++i) {
      Map<Character, Integer> node = trie.get(i);
      for (Map.Entry<Character, Integer> entry : node.entrySet()) {
        System.out.println(i + "->" + entry.getValue() + ":" + entry.getKey());
      }
    }
  }

  public void run() throws IOException {
    FastScanner scanner = new FastScanner();
    int patternsCount = scanner.nextInt();
    String[] patterns = new String[patternsCount];
    for (int i = 0; i < patternsCount; ++i) {
      patterns[i] = scanner.next();
    }
    List<Map<Character, Integer>> trie = buildTrie(patterns);
    print(trie);
  }

  class FastScanner {
    StringTokenizer tok = new StringTokenizer("");
    BufferedReader in;

    FastScanner() {
      in = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() throws IOException {
      while (!tok.hasMoreElements())
        tok = new StringTokenizer(in.readLine());
      return tok.nextToken();
    }

    int nextInt() throws IOException {
      return Integer.parseInt(next());
    }
  }
}
