package org.gxg.searching;

import org.gxg.collection.LinkedQueue;
import org.gxg.collection.LinkedStack;
import org.gxg.tools.In;

import java.util.NoSuchElementException;

public class BSTv2<Key extends Comparable<Key>, Value> {
    private Node root;// 根结点

    private class Node {
        private final Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private Integer N;          // 结点计数器。以该结点为根的子树的结点总数。

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.N = size;
        }
    }

    public BSTv2() {
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;// 空链接为0
        else return x.N;
    }

    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    public Value get(Key key) {
        return get(root, key);
    }
    private Value get(Node x, Key key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with a null key");
        }

        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else    return x.val;
        }
        return null;
    }

    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("calls put() with a null key");
        }
        if (val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) {
            return new Node(key, val, 1);
        }

        LinkedStack<Node> stack = new LinkedStack<>();
        while (x != null) {
            stack.push(x);
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else    {// 命中，更新val
                x.val = val;

                // 弹出节点，将最初的节点返回（最初的节点最后被弹出）
                while (!stack.isEmpty()) {
                    x = stack.pop();
                }
                return x;
            }
        }

        // 未命中
        x = stack.pop();
        // 创建新节点
        if (key.compareTo(x.key) < 0) {
            x.left = new Node(key, val, 1);
        } else {
            x.right = new Node(key, val, 1);
        }
        // 更新N值
        x.N = 1 + size(x.left) + size(x.right);
        while (!stack.isEmpty()) {
            x = stack.pop();
            x.N = 1 + size(x.left) + size(x.right);
        }

        return x;
    }

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMin(root);
    }

    // 删除 x 这棵树里最小的节点，同时返回删除最小节点后的这棵树。
    private Node deleteMin(Node x) {
        // x是最小的节点
        if (x.left == null) {
            return x.right;
        }

        LinkedStack<Node> stack = new LinkedStack<>();
        while (x.left != null) {
            stack.push(x);// 最后一个x不被push进来
            x = x.left;
        }

        Node minNode = x;// 待删除节点
        x = stack.pop();
        x.left = minNode.right;// 把最小节点的右子树，替代最小节点成为上层节点的左子树。
        // 更新N值
        x.N = 1 + size(x.left) + size(x.right);
        while (!stack.isEmpty()) {
            x = stack.pop();
            x.N = 1 + size(x.left) + size(x.right);
        }

        return x;
    }

    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMax(root);
    }
    private Node deleteMax(Node x) {
        // x是最大的节点
        if (x.right == null) {
            return x.left;
        }

        LinkedStack<Node> stack = new LinkedStack<>();
        while (x.right != null) {
            stack.push(x);// 最后一个x不被push进来
            x = x.right;
        }

        Node maxNode = x;// 待删除节点
        x = stack.pop();
        x.right = maxNode.left;// 把最大节点的左子树，替代最大节点成为上层节点的右子树。
        // 更新N值
        x.N = 1 + size(x.left) + size(x.right);
        while (!stack.isEmpty()) {
            x = stack.pop();
            x.N = 1 + size(x.left) + size(x.right);
        }

        return x;
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("calls delete() with a null key");
        }
        root = delete(root, key);
    }
    private Node delete(Node x, Key key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = delete(x.left,  key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            if (x.right == null) return x.left;
            if (x.left  == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }
    private Node min(Node x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    }
    private Node max(Node x) {
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to floor() is null");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("calls floor() with empty symbol table");
        }

        Node x = floor(root, key);
        if (x == null) throw new NoSuchElementException("argument to floor() is too small");
        else return x.key;
    }
    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp <  0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }

    public Key floor2(Key key) {
        Key x = floor2(root, key, null);
        if (x == null) throw new NoSuchElementException("argument to floor() is too small");
        else return x;

    }

    private Key floor2(Node x, Key key, Key best) {
        if (x == null) return best;
        int cmp = key.compareTo(x.key);
        if      (cmp  < 0) return floor2(x.left, key, best);
        else if (cmp  > 0) return floor2(x.right, key, x.key);
        else               return x.key;
    }

    public Key ceiling(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to ceiling() is null");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("calls ceiling() with empty symbol table");
        }
        Node x = ceiling(root, key);
        if (x == null) throw new NoSuchElementException("argument to ceiling() is too large");
        else return x.key;
    }
    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) {
            Node t = ceiling(x.left, key);
            if (t != null) return t;
            else return x;
        }
        return ceiling(x.right, key);
    }

    public Key select(int rank) {
        if (rank < 0 || rank >= size()) {
            throw new IllegalArgumentException("argument to select() is invalid: " + rank);
        }
        return select(root, rank);
    }
    private Key select(Node x, int rank) {
        if (x == null) return null;
        int leftSize = size(x.left);
        if      (leftSize > rank) return select(x.left,  rank);
        else if (leftSize < rank) return select(x.right, rank - leftSize - 1);
        else                      return x.key;
    }

    public int rank(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to rank() is null");
        }
        return rank(key, root);
    }
    // Number of keys in the subtree less than key.
    private int rank(Key key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else              return size(x.left);
    }

    public Iterable<Key> keys() {
        if (isEmpty()) return new LinkedQueue<>();
        return keys(min(), max());
    }
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

        LinkedQueue<Key> queue = new LinkedQueue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, LinkedQueue<Key> queue, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

    public int size(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to size() is null");
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null");

        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else              return rank(hi) - rank(lo);
    }

    public int height() {
        return height(root);
    }
    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    public Iterable<Key> levelOrder() {
        LinkedQueue<Key> keys = new LinkedQueue<>();
        LinkedQueue<Node> queue = new LinkedQueue<>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            if (x == null) continue;
            keys.enqueue(x.key);
            queue.enqueue(x.left);
            queue.enqueue(x.right);
        }
        return keys;
    }

    public static void main(String[] args) {
        BSTv2<String, Integer> st = new BSTv2<>();
        String file;
        file = "testData/searching/simple.txt";
//        file = "testData/searching/leipzig100k.txt";
        In in = new In(file);

        for (int i = 0; !in.isEmpty(); i++) {
            String key = in.readString();
            st.put(key, i);
        }

        System.out.println("level order keys: ");
        for (String s : st.levelOrder())
            System.out.println(s + " " + st.get(s));

        System.out.println();

        System.out.println("keys: ");
        for (String s : st.keys())
            System.out.println(s + " " + st.get(s));

        st.deleteMin();
        st.deleteMax();

        while (!st.isEmpty()) {
            st.deleteMin();
        }

        in.close();
    }

}

