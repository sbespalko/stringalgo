package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BurrowsWheelerTransform {
  static public void main(String[] args) throws IOException {
    new BurrowsWheelerTransform().run();
  }

  String BWT(String text) {
    StringBuilder result = new StringBuilder();
    String[] matrix = new String[text.length()];

    for (int i = 0; i < text.length(); i++) {
      String left = text.substring(text.length() - i - 1);
      String right = text.substring(0, text.length() - i - 1);
      matrix[i] = left + right;
    }
    Arrays.sort(matrix);
    for (String str : matrix) {
      result.append(str.charAt(str.length() - 1));
    }

    return result.toString();
  }

  public void run() throws IOException {
    FastScanner scanner = new FastScanner();
    String text = scanner.next();
    System.out.println(BWT(text));
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
