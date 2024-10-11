
import java.util.Arrays;
public class MyHashMap {
    int[] data;
    public MyHashMap() {
        data = new int[100001];
        Arrays.fill(data, -1);
    }
    
    public void put(int key, int value) {
        data[key] = value;
    }
    
    public int get(int key) {
        return data[key];
    }
    
    public void remove(int key) {
        data[key] = -1;
    }
}
