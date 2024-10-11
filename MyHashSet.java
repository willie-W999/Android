import java.util.ArrayList;
import java.util.List;
public class MyHashSet {
    private List<List<Integer>> bucket;
    private int size;
    //HashSet<Integer> set;
    public MyHashSet() {
        size = 1000;
        bucket = new ArrayList<>();
        for(int i = 0;i < size;i++){
            bucket.add(new ArrayList<>());
        }
        //set = new HashSet<>();
    }
    
    public void add(int key) {
        int index = key % size;
        List<Integer> b = bucket.get(index);
        if(!b.contains(key)){
            b.add(key);
        }
        //set.add(key);
    }
    
    public void remove(int key) {
        int index = key % size;
        List<Integer> b = bucket.get(index);
        b.remove(Integer.valueOf(key));
        //set.remove(key);
    }
    
    public boolean contains(int key) {
        int index = key % size;
        List<Integer> b = bucket.get(index);
        return b.contains(key);
        //return set.contains(key);
    }
    
}