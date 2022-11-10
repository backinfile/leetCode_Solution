import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Trie {
    private final Node head = new Node('0');

    public Trie() {

    }

    public void insert(String word) {
        Node next = head;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            Node cur = next.getNode(ch);
            if (cur != null) {
                next = cur;
            } else {
                next = next.createNode(ch);
            }
        }
        next.isEnd = true;
    }

    public boolean search(String word) {
        Node next = head;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            next = next.getNode(ch);
            if (next == null) {
                return false;
            }
        }
        return next.isEnd;
    }

    public boolean startsWith(String prefix) {
        Node next = head;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            next = next.getNode(ch);
            if (next == null) {
                return false;
            }
        }
        return true;
    }

    private static class Node {
        public char ch;
        public boolean isEnd;
        public List<Node> children;

        public Node(char ch) {
            this.ch = ch;
        }

        public Node createNode(char ch) {
            if (children == null) {
                children = new ArrayList<>();
            }
            Node node = new Node(ch);
            children.add(node);
            return node;
        }

        public List<Node> getChildren() {
            return children == null ? Collections.emptyList() : children;
        }

        public Node getNode(char ch) {
            if (children != null) {
                for (Node node : children) {
                    if (node.ch == ch) {
                        return node;
                    }
                }
            }
            return null;
        }
    }
}
