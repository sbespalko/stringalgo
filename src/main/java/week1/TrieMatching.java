package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class TrieMatching implements Runnable {
  public static void main(String[] args) {
    new Thread(new TrieMatching()).start();
  }

  List<Integer> solve(String text, int n, List<String> patterns) {
    List<Map<Character, Integer>> trie = buildTrie(patterns);

    List<Integer> result = new ArrayList<Integer>();

    // write your code here
    int start = 0;
    int startPattern = -1;
    Map<Character, Integer> children;
    while (start < text.length()) {
      int offset = start;
      char symbol = text.charAt(start);
      children = trie.get(0); //root pattern
      startPattern = -1;
      while (children != null && !children.isEmpty()) {
        Integer nextChildIndex = children.get(symbol);
        if (nextChildIndex == null) {
          break;
        }
        if (startPattern == -1) {
          startPattern = start;
        }
        children = trie.get(nextChildIndex);
        if (children == null || children.isEmpty()) {
          //pattern ends
          result.add(startPattern);
          break;
        }
        ++offset;
        if (offset >= text.length()) {
          break;
        }
        symbol = text.charAt(offset);
      }
      ++start;
    }
    return result;
  }

  public void run() {
    try {
      BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
      String text = in.readLine();
      int n = Integer.parseInt(in.readLine());
      List<String> patterns = new ArrayList<String>();
      for (int i = 0; i < n; i++) {
        patterns.add(in.readLine());
      }

      List<Integer> ans = solve(text, n, patterns);

      for (int j = 0; j < ans.size(); j++) {
        System.out.print("" + ans.get(j));
        System.out.print(j + 1 < ans.size() ? " " : "\n");
      }
    } catch (Throwable e) {
      e.printStackTrace();
      System.exit(1);
    }
  }

  List<Map<Character, Integer>> buildTrie(List<String> patterns) {
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
}
