class TrieNode{
    public TrieNode[] children;
    public boolean isEndOfWord;
    public TrieNode(){
        children = new TrieNode[26];
        isEndOfWord = false;
    }
}
public class WordDictionary {
    public final TrieNode root;
    public WordDictionary() {
        root = new TrieNode();
    }
    
    public void addWord(String word) {
        TrieNode node = root;
        for(char i : word.toCharArray()){
            int index = i - 'a';
            if(node.children[index] == null){
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true;
    }
    
    public boolean search(String word) {
        return searchInNode(word, root);
    }
    
    private boolean searchInNode(String word, TrieNode node){
        for(int i = 0;i < word.length();i++){
           char c = word.charAt(i);
           if(c == '.'){
            for(TrieNode child : node.children){
                if(child != null && searchInNode(word.substring(i + 1), child)){
                    return true;
                }
            }
            return false;
           }else{
            int index = c - 'a';
            if(node.children[index] == null){
                return false;
            }
            node = node.children[index];
           }
        }
        return node.isEndOfWord;
    } 
}
