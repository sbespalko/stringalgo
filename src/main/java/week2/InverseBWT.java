package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class InverseBWT {

  static public void main(String[] args) throws IOException {
    new InverseBWT().run();
  }

  String inverseBWT(String bwt) {
    StringBuilder result = new StringBuilder();

    // write your code here
    char[] firstColumn = bwt.toCharArray();
    Arrays.sort(firstColumn);
    char[] lastColumn = bwt.toCharArray();

    int[] lastToFirst = createLastToFirst(firstColumn, lastColumn);

    char currChar = lastColumn[0];
    int indexInFirst = lastToFirst[0];
    result.append('$');
    while (currChar != '$') {
      result.append(currChar);
      currChar = lastColumn[indexInFirst];
      indexInFirst = lastToFirst[indexInFirst];
    }
    result.reverse();
    return result.toString();
  }

  private int[] createLastToFirst(char[] firstColumn,
                                  char[] lastColumn) {
    int a = -1, c = -1, g = -1, t = -1;
    int startA = -1,
        startC = -1, startG = -1, startT = -1;
    int[] lastToFirst = new int[lastColumn.length];
    for (int i = 0; i < lastColumn.length; i++) {
      char currChar = lastColumn[i];
      int startIndex = -1;
      int offset = 0;
      if (currChar == 'A') {
        if (startA == -1) {
          startA = getStartIndex(firstColumn, currChar);
        }
        startIndex = startA;
        ++a;
        offset = a;
      } else if (currChar == 'C') {
        if (startC == -1) {
          startC = getStartIndex(firstColumn, currChar);
        }
        startIndex = startC;
        ++c;
        offset = c;
      } else if (currChar == 'G') {
        if (startG == -1) {
          startG = getStartIndex(firstColumn, currChar);
        }
        startIndex = startG;
        ++g;
        offset = g;
      } else if (currChar == 'T') {
        if (startT == -1) {
          startT = getStartIndex(firstColumn, currChar);
        }
        startIndex = startT;
        ++t;
        offset = t;
      } else if (currChar == '$') {
        startIndex = 0;
        offset = 0;
      }
      lastToFirst[i] = startIndex + offset;
    }
    return lastToFirst;
  }


  private int getStartIndex(char[] firstColumn, char currChar) {
    int index = Arrays.binarySearch(firstColumn, currChar);
    while (index >= 0 && firstColumn[index] == currChar) {
      --index;
    }
    return index + 1;
  }

  public void run() throws IOException {
    FastScanner scanner = new FastScanner();
    String bwt = scanner.next();
    System.out.println(inverseBWT(bwt));
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
