import java.util.HashMap;
import java.util.Map;
public class LRUCache {
    class Node{
        int key, val;
        Node prev, next;
        Node(int key, int val){
            this.key = key;
            this.val = val;
            next = null;
            prev = null;
        }
    }
    private Node left, right;
    private int capacity;
    private Map<Integer, Node> cache;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        left = new Node(0, 0);
        right = new Node(0, 0);
        left.next = right;
        right.prev = left;
    }
    
    public int get(int key) {
        if(cache.containsKey(key)){
            Node node = cache.get(key);
            remove(node);
            addtohead(node);
            return node.val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(cache.containsKey(key)){
            Node node = cache.get(key);
            node.val = value;
            remove(node);
            addtohead(node);
        }else{
            Node newnode = new Node(key, value);
            cache.put(key, newnode);
            if(cache.size() > capacity){
                Node movenode = right.prev;
                remove(movenode);
                cache.remove(movenode.val);
            }
            addtohead(newnode);
        }
    }
    private void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    private void addtohead(Node node){
        node.next = left.next;
        node.prev = left;
        left.next.prev = node;
        left.next = node;
    }
}
