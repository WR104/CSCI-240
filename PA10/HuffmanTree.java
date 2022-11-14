package PA10;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTree {
    private class HuffmanNode implements Comparable<HuffmanNode> {
        char data;
        int weight;
        HuffmanNode left;
        HuffmanNode right;

        public HuffmanNode(char c, int freq) {
            data = c;
            weight = freq;
            left = null;
            right = null;
        }

        @Override
        public int compareTo(HuffmanNode o) {
            return this.weight - o.weight;
        }
    }

    char[] charArray;
    Map<Character, Integer> freq = new HashMap<>();
    Map<Character, String> map = new HashMap<>();
    PriorityQueue<HuffmanNode> q = new PriorityQueue<>();
    HuffmanNode root;

    public HuffmanTree(String s) {
        charArray = s.toCharArray();
        countFreq();
        int n = charArray.length;
        for (Map.Entry<Character, Integer> f : freq.entrySet()) {
            HuffmanNode node = new HuffmanNode(f.getKey(), f.getValue());
            q.add(node);
        }
        root = null;
        while (q.size() > 1) {
            HuffmanNode x = q.poll();
            HuffmanNode y = q.poll();
            HuffmanNode z = new HuffmanNode('-', x.weight + y.weight);
            z.left = x;
            z.right = y;
            root = z;
            q.add(z);
        }

    }

    private void countFreq() {
        int n = charArray.length;
        boolean[] visited = new boolean[n];
        Arrays.fill(visited, false);
        for (int i = 0; i < n; i++) {
            if (visited[i])
                continue;
            int count = 1;
            for (int j = i + 1; j < n; j++)
                if (charArray[i] == charArray[j]) {
                    visited[j] = true;
                    count++;
                }

            freq.put(charArray[i], count);
        }
    }

    public void inorder(HuffmanNode node, String s) {
        if (node.left == null && node.right == null
                && (Character.isLetter(node.data) || node.data == ' ')) {
            System.out.println(node.data + ": " + s);
            map.put(node.data,s);
            return;
        }
        if (node.left != null)
            inorder(node.left, s + "0");
        if (node.right != null)
            inorder(node.right, s + "1");
    }

    public void print() {
        System.out.println("Number of characters: " + charArray.length + "\n");
        inorder(root, "");

        String encodedBits = "";
        for(char c : charArray){
            String code = map.get(c);
            encodedBits += code;
        }
        System.out.println("\nNumber of bits: " + encodedBits.length());
        System.out.println(encodedBits);
    }

}