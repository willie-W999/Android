import java.util.HashMap;
import java.util.Map;
public class WordFilter {
    public Map<String, Integer> map;
    public WordFilter(String[] words){
        map = new HashMap<>();
        for(int index = 0;index < words.length;index++){
            String word = words[index];
            int wordlength = word.length();
            for(int i = 0;i <= wordlength;i++){
                String prefix = word.substring(0, i);
                for(int j = 0;j <= wordlength;j++){
                    String suffix = word.substring(j);
                    map.put(prefix + "#" + suffix, index);
                }
            }
        }
    }
    
    public int f(String pref, String suff){
        String key = pref + "#" + suff;
        return map.getOrDefault(key, -1);
    }
}