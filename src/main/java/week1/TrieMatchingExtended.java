package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Node {
    private char symbol;
    private boolean patternEnd;
    private List<Node> children;

    Node(char symbol, boolean patternEnd) {
        this.symbol = symbol;
        this.patternEnd = patternEnd;
    }

    void addChild(Node child) {
        getChildren().add(child);
    }

    char getSymbol() {
        return symbol;
    }

    boolean isPatternEnd() {
        return patternEnd;
    }

    void setPatternEnd(boolean patternEnd) {
        this.patternEnd = patternEnd;
    }

    List<Node> getChildren() {
        if (children == null) {
            children = new ArrayList<>();
        }
        return children;
    }
}

public class TrieMatchingExtended implements Runnable {

    List<Integer> solve(String text, int n, List<String> patterns) {
        List<Integer> result = new ArrayList<Integer>();

        Node root = buildTrie(patterns);

        // write your code here
        int start = 0;
        while (start < text.length()) {
            int offset = start;
            Node currentNode = root;
            while (offset < text.length()) {
                char c = text.charAt(offset);
                Node child = searchChild(c, currentNode.getChildren());
                if (child == null) {
                    break;
                }
                if (child.isPatternEnd()) {
                    result.add(start);
                    break;
                }
                currentNode = child;
                ++offset;
            }
            ++start;
        }

        return result;
    }

    private Node buildTrie(List<String> patterns) {
        Node root = new Node(Character.MIN_VALUE, patterns.isEmpty());
        for (String pattern : patterns) {
            Node currentNode = root;
            for (int i = 0; i < pattern.toCharArray().length; i++) {
                char c = pattern.charAt(i);
                boolean isPatternEnd = i == (pattern.toCharArray().length - 1);
                Node child = searchChild(c, currentNode.getChildren());
                if (child != null) {
                    if (isPatternEnd) {
                        child.setPatternEnd(isPatternEnd);
                    }
                } else {
                    child = new Node(c, isPatternEnd);
                    currentNode.addChild(child);
                }
                currentNode = child;
            }
        }
        return root;
    }

    private Node searchChild(char c, List<Node> children) {
        for (Node child : children) {
            if (child.getSymbol() == c) {
                return child;
            }
        }
        return null;
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

    public static void main(String[] args) {
        new Thread(new TrieMatchingExtended()).start();
    }
}
