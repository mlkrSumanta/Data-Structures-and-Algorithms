/*
Please note that it's Function problem i.e.
you need to write your solution in the form Function(s) only.
Driver Code to call/invoke your function would be added by GfG's Online Judge.*/


/*You are required to complete below class */
class LRUCacheNew {
    
    public static void main(String[] args) {
        
        Solution sol = new Solution(6);
        sol.set(20, 24);
        sol.print();
        sol.set(40, 67);
        sol.print();
        sol.set(19, 86);
        sol.print();
        sol.set(81, 69);
        sol.print();
        sol.set(47, 4);
        sol.print();
        sol.set(20, 98);
        sol.print();
        int res1 = sol.get(40);
        sol.print();
        sol.set(43, 82);
        sol.print();
        int res2 = sol.get(19);
        sol.print();
        sol.set(59, 11);
        sol.print();
        sol.set(49, 11);
        sol.print();
        System.out.println(res1 + " " + res2);
    }
}

class Solution {

    class Node {
        int key;
        int value;
        
        public Node (int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    int maxSize, currSize;
    java.util.LinkedList<Node> nodeArray;
    /*Inititalize an LRU cache with size N */
    public Solution (int N) {
       maxSize = N;
       currSize = 0;
       nodeArray = new java.util.LinkedList<>();
    }
    //wubba
    /*Returns the value of the key x if 
     present else returns -1 */
    public int get(int x) {
        int i = 0;
        while (i < nodeArray.size()) {
            Node node = nodeArray.get(i);
            if (node.key == x) {
                if (i > 0) {
                    nodeArray.remove(i);
                    nodeArray.addFirst(node);
                }
                return node.value;
            }
            i++;
        }
        return -1;
    }
    
    /*Sets the key x with value y in the LRU cache */
    public void set(int x, int y) {
        
        Node node = new Node(x, y);
        if (currSize == 0) {
            currSize++;
            nodeArray.addFirst(node);
            return;
        }
        int key = get(x);
            
        if (key == -1) {
            if (currSize >= maxSize) {
                nodeArray.removeLast();
                nodeArray.addFirst(node);
                return;
            }
            nodeArray.addFirst(node);
            currSize++; 
            return;  
        }
        nodeArray.set(0, node);
    }

    public void print() {
        
    }
}
