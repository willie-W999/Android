import java.util.*;
class TrieNode{
    public TrieNode[] children;
    public String word;
    public TrieNode(){
        children = new TrieNode[26];
        word = null;
    }
}
public class advanced_algorithms {
    public class ListNode{
        int val;
        ListNode next;
        ListNode(){}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next){ 
            this.val = val; 
            this.next = next; 
        }
    }
    
    public boolean containsNearbyDuplicate(int[] nums, int k){
        var map = new HashMap<Integer, Integer>();
        for(var i = 0;i < nums.length;i++){
            if(map.containsKey(nums[i]) && i - map.get(nums[i]) <= k){
                return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }
//--------------------------------------------------------------
    public int numOfSubarrays(int[] arr, int k, int threshold){
        var sum = 0;
        var count = 0;
        for(var i = 0;i < k;i++){
            sum += arr[i];
        }
        if(sum / k >= threshold)count++;
        for(var i = k;i < arr.length;i++){
            sum += arr[i] - arr[i - k];
            if(sum / k >= threshold)count++;
        }
        return count;
    }
//--------------------------------------------------------------
    public int minSubArrayLen(int target, int[] nums) {
        var minlen = Integer.MAX_VALUE;
        var sum = 0;
        var start = 0;
        for(var i = 0;i < nums.length;i++){
            sum += nums[i];
            while(sum >= target){
                minlen = Math.min(minlen, i - start + 1);
                sum -= nums[start];
                start++;
            }
        }
        return minlen == Integer.MAX_VALUE ? 0 : minlen;
    }
//--------------------------------------------------------------
    public int lengthOfLongestSubstring(String s){
        var set = new HashSet<Character>();
        var maxlen = 0;
        var j = 0;
        for(var i = 0;i < s.length();i++){
            if(!set.contains(s.charAt(i))){
                set.add(s.charAt(i));
                maxlen = Math.max(maxlen, i - j + 1);
            }else{
                while(s.charAt(j) != s.charAt(i)){
                    set.remove(s.charAt(j));
                    j++;
                }
                set.remove(s.charAt(j));
                j++;
                set.add(s.charAt(i));
            }
        }
        return maxlen;
    }
//--------------------------------------------------------------
    public int characterReplacement(String s, int k){
        var count = new int[26];
        var maxlen = 0;
        var maxcount = 0;
        var j = 0;
        for(var i = 0;i < s.length();i++){
            count[s.charAt(i) - 'A']++;
            maxcount = Math.max(maxcount, count[s.charAt(i) - 'A']);
            while(i - j + 1 - maxcount > k){
                count[s.charAt(j) - 'A']--;
                j++;
            }
            maxlen = Math.max(maxlen, i - j + 1);
        }
        return maxlen;
    }
//--------------------------------------------------------------
    public boolean isPalindrome(String s){
        if(s.isEmpty())return true;
        var start = 0;
        var end = s.length() - 1;
        while(start <= end){
            if(!Character.isLetterOrDigit(s.charAt(start))){
                start++;
            }else if(!Character.isLetterOrDigit(s.charAt(end))){
                end--;
            }else{
                if(Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))){
                    return false;
                }
                start++;
                end--;
            }
        }
        return true;
    }
//--------------------------------------------------------------
    public int[] twoSum(int[] numbers, int target){
        var start = 0;
        var end = numbers.length - 1;
        while(start <= end){
            var sum = numbers[start] + numbers[end];
            if(sum == target){
                return new int[]{start + 1, end + 1};
            }else if(sum > target){
                end--;
            }else{
                start++;
            }
        }
        return null;
    }
//--------------------------------------------------------------
    public int removeDuplicates(int[] nums){
        var i = 1;
        var j = 1;
        if(nums.length == 1)return 1;
        while(i < nums.length){
            if(nums[i - 1] == nums[i]){
                nums[j] = nums[i];
                j++;
            }
            i++;
        }
        return j;
    }
//--------------------------------------------------------------
    public int removeDuplicatesII(int[] nums){
        if(nums.length <= 2)return nums.length;
        var index = 0;
        for(var i = 2;i < nums.length;i++){
            if(nums[i] != nums[index - 2]){
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }
//--------------------------------------------------------------
    public int maxArea(int[] height){
        var i = 0;
        var j = height.length - 1;
        var maxarea = 0;
        while(i < j){
            var area = Math.min(height[i], height[j]) * (j - i);
            maxarea = Math.max(maxarea, area);
            if(height[i] < height[j]){
                i++;
            }else{
                j--;
            }
        }
        return maxarea;
    }
//--------------------------------------------------------------
    public int trap(int[] height){
        int left = 0, right = height.length - 1;
        int maxleft = 0, maxright = 0;
        var allwater = 0;
        while(left < right){
            if(height[left] < height[right]){
                if(height[left] >= maxleft){
                    maxleft = height[left];
                }else{
                    allwater += maxleft - height[left];
                }
                left++;
            }else{
                if(height[right] >= maxright){
                    maxright = height[right];
                }else{
                    allwater += maxright - height[right];
                }
                right++;
            }
        }
        return allwater;
    }
//--------------------------------------------------------------
    public int pivotIndex(int[] nums){
        var sum = 0;
        var leftsum = 0;
        for(var i : nums){
            sum += i;
        }
        for(var i = 0;i < nums.length;i++){
            if(leftsum == sum - leftsum - nums[i])return i;
            leftsum += nums[i];
        }
        return -1;
    }
//--------------------------------------------------------------
    public int[] productExceptSelf(int[] nums){
        var ans = new int[nums.length];
        var right = 1;
        for(var i = 1;i < nums.length;i++){
            ans[i] = ans[i - 1] * nums[i - 1];
        }
        for(var i = nums.length - 1;i >= 0;i--){
            ans[i] *= right;
            right *= nums[i];
        }
        return ans;
    }
//--------------------------------------------------------------
    public int subarraySum(int[] nums, int k){
        var map = new HashMap<Integer, Integer>();
        var sum = 0;
        var count = 0;
        for(var i : nums){
            sum += i;
            if(map.containsKey(sum - k)){
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
//--------------------------------------------------------------
    public ListNode middleNode(ListNode head){
        ListNode fast = head;
        ListNode slow = head;
        if(head == null)return null;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
//--------------------------------------------------------------
    public int pairSum(ListNode head){
        ListNode a = head;
        ListNode fast = head;
        ListNode slow = head;
        var maxsum = 0;
        if(head == null)return 0;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode left = slow;
        ListNode right = null;
        while(left != null){
            ListNode temp = left.next;
            left.next = right;
            right = left;
            left = temp;
        }
        while(right != null){
            maxsum = Math.max(maxsum, a.val + right.val);
            a = a.next;
            right = right.next;
        }
        return maxsum;
    }
//--------------------------------------------------------------
    public boolean hasCycle(ListNode head){
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next;
            if(fast == slow)return true;
        }
        return false;
    }
//--------------------------------------------------------------
    public ListNode detectCycle(ListNode head){
        ListNode fast = head;
        ListNode slow = head;
        if(head == null)return null;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast == slow)break;
        }
        if(fast == null || fast.next == null)return null;
        while(head != slow){
            head = head.next;
            slow = slow.next;
        }
        return head;
    }
//--------------------------------------------------------------
    public int findDuplicate(int[] nums){
        int fast = nums[0];
        int slow = nums[0];
        do{
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while(fast != slow);
        slow = nums[0];
        while(fast != slow){
            slow = nums[slow];
            fast = nums[fast];
        }
        return fast;
    }
//--------------------------------------------------------------
    private TrieNode root = new TrieNode();
    private Set<String> result = new HashSet<>();
    public List<String> findWords(char[][] board, String[] words){
        for(String word : words){
            addword(word);
        }
        for(int i = 0;i < board.length;i++){
            for(int j = 0;j < board.length;j++){
                dfs(board, i, j, root);
            }
        }
        return new ArrayList<>(result);
    }
    public void addword(String word){
        TrieNode node = root;
        for(char i : word.toCharArray()){
            int index = i - 'a';
            if(node.children[index] == null){
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.word = word;
    }
    private void dfs(char[][] board, int i, int j, TrieNode node){
        char c = board[i][j];
        if(c == '#' || node.children[c - 'a'] == null)return;
        node = node.children[c - 'a'];
        if(node.word != null){
            result.add(node.word);
            node.word = null;
        }
        board[i][j] = '#';
        if(i > 0)dfs(board, i - 1, j, node);
        if(j > 0)dfs(board, i, j - 1, node);
        if(i < board.length - 1)dfs(board, i + 1, j, node);
        if(j < board.length - 1)dfs(board, i, j + 1, node);
        board[i][j] = c;
    }
//--------------------------------------------------------------
    public int[] findRedundantConnection(int[][] edges){
        int n = edges.length;
        UnionFind uf = new UnionFind(n);
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            if(!uf.Union(u - 1, v - 1)){
                return edge;
            }
        }
        return new int[0];
    }
    private class UnionFind{
        private int[] parent;
        private int[] rank;
        public UnionFind(int size){
            parent = new int[size];
            rank = new int[size];
            for(int i = 0;i < size;i++){
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int p){
            while(parent[p] != p){
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        public boolean Union(int p, int q){
            int rootp = find(p);
            int rootq = find(q);
            if(rootp == rootq)return false;
            if(parent[rootp] > parent[rootq]){
                parent[rootq] = rootp;
            }else if(parent[rootp] < parent[rootq]){
                parent[rootp] = rootq;
            }else{
                parent[rootq] = rootp;
                rank[rootp]++;
            }
            return true;
        }
    }
//--------------------------------------------------------------
    public List<List<String>> accountsMerge(List<List<String>> accounts){
        var emailToName = new HashMap<String, String>();
        var parent = new HashMap<String, String>();
        var unions = new HashMap<String, TreeSet<String>>();

        for(List<String> account : accounts){
            String name = account.get(0);
            for(int i = 1;i < account.size();i++){
                String email = account.get(i);
                emailToName.put(email, name);
                parent.put(email, email);
            }
        }

        for(List<String> account : accounts){
            String firstname = account.get(1);
            for(int i = 2;i < account.size();i++){
                Union(firstname, account.get(i), parent);
            }
        }
        
        for(String email : parent.keySet()){
            String root = find(email, parent);
            unions.computeIfAbsent(root, x -> new TreeSet<>()).add(email);
        }

        List<List<String>> ans = new ArrayList<>();
        for(String root : unions.keySet()){
            List<String> emails = new ArrayList<>(unions.get(root));
            emails.add(0, emailToName.get(root));
            ans.add(emails);
        }
        return ans;
    }
    private String find(String email, Map<String, String> parent){
        if(!email.equals(parent.get(email))){
            parent.put(email, find(parent.get(email), parent));
        }
        return parent.get(email);
    }
    private void Union(String email1, String email2, Map<String, String> parent){
        String root1 = find(email1, parent);
        String root2 = find(email2, parent);

        if(!root1.equals(root2))parent.put(root1, root2);
    }
//--------------------------------------------------------------
    public int longestConsecutive(int[] nums){
        // if(nums.length == 0) return 0;
        // Arrays.sort(nums);
        // int a = 1;
        // int b = 0;
        // for(int i = 1;i < nums.length;i++){
        //     if(nums[i] != nums[i - 1]){
        //         if(nums[i] == nums[i - 1] + 1){
        //             a++;
        //         }else{
        //             b = Math.max(b , a);
        //             a = 1;
        //         }
        //     }
        // }
        // return Math.max(a, b);
        if(nums == null || nums.length == 0)return 0;
        var parent = new HashMap<Integer, Integer>();
        var size = new HashMap<Integer, Integer>();
        for(int num : nums){
            parent.put(num, num);
            size.put(num, 1);
        }
        for(int num : nums){
            if(parent.containsKey(num + 1)){
                union(num, num + 1, parent, size);
            }
        }
        int maxlen = 0;
        for(int num : nums){
            maxlen = Math.max(maxlen, size.get(find(num, parent)));
        }
        return maxlen;
    }
    private int find(int num, Map<Integer, Integer> parent){
        if(num != parent.get(num)){
            parent.put(num, find(parent.get(num), parent));
        }
        return parent.get(num);
    }
    private void union(int num1, int num2, Map<Integer, Integer> parent, Map<Integer, Integer> size){
        int root1 = find(num1, parent);
        int root2 = find(num2, parent);

        if(root1 != root2){
            int size1 = size.get(root1);
            int size2 = size.get(root2);
            if(size1 < size2){
                parent.put(root1, root2);
                size.put(root2, size1 + size2);
            }else{
                parent.put(root2, root1);
                size.put(root1, size1 + size2);
            }
        }
    }
//--------------------------------------------------------------
}
