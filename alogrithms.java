import java.util.*;
public class alogrithms {
    
    public class ListNode {
        int val;
        ListNode next;
    
        ListNode(int x) {
            val = x;
        }
    }
     public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
//-------------------------------------------------------------w
    public int removeDuplicates(int[] nums){
        var ans = 1;
        for(var i = 1;i < nums.length;i++){
            if(nums[ans] != nums[i - 1]){
                nums[ans] = nums[i];
                ans++;
            }
        }
        return ans;
    }
//--------------------------------------------------------------
    public int removeElement(int[] nums, int val){
        var ans = 0;
        for(var i = 0;i < nums.length;i++){
            if(nums[i] != val){
                nums[ans] = nums[i];
                ans++;
            }
        }
        return ans;
    }
//--------------------------------------------------------------
    public int[] getConcatenation(int[] nums){
        var ans = new int[nums.length * 2];
        for(var i = 0;i < nums.length;i++){
            ans[i] = ans[i + nums.length] = nums[i];
        }
        return ans;
    }
//--------------------------------------------------------------
    public int calPoints(String[] operations){
        var sum = 0;
        var ans = new int[operations.length];
        var a = 0;
        for(var i : operations){
            if(i.equals("+")){
                ans[a] = ans[a - 1] + ans[a - 2];
                a++;
            }else if(i.equals("D")){
                ans[a] = ans[a - 1] * 2;
                a++;
            }else if(i.equals("C")){
                a--;
            }else{
                ans[a] = Integer.parseInt(i);
                a++;
            }
        }
        for(var j = 0;j < a;j++){
            sum += ans[j];
        }
        return sum;
    }
//--------------------------------------------------------------
    public boolean isValid(String s){
        var stack = new Stack<Character>();
        for(var i : s.toCharArray()){
            if(i == '('){
                stack.push(')');
            }else if(i == '['){
                stack.push(']');
            }else if(i == '{'){
                stack.push('}');
            }else if(stack.isEmpty() || stack.pop() != i){
                return false;
            }
        }
        return stack.isEmpty();
    }
//--------------------------------------------------------------
    public ListNode reverseList(ListNode head){
        ListNode ans = null;
        ListNode curr = head;
        ListNode temp;
        while(curr != null){
            temp = curr.next;
            curr.next = ans;
            ans = curr;
            curr = temp;
        }
        return ans;
    }
//--------------------------------------------------------------
    public ListNode mergeTwoLists(ListNode list1, ListNode list2){
        ListNode list = new ListNode(0);
        ListNode curr = list;
        while(list1 != null && list2 != null){
            if(list1.val < list2.val){
                curr.next = list1;
                list1 = list1.next;
            }else{
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }
        curr.next = list1 != null ? list1 : list2;
        return list.next;
    }
//--------------------------------------------------------------
    public int countStudents(int[] students, int[] sandwiches){
        var count = new int[2];
        for(var i : students){
            count[i]++;
        }
        for(var j : sandwiches){
            if(count[j] <= 0){
                break;
            }
            count[j]--;
        }
        return count[0] + count[1];
    }
//--------------------------------------------------------------
    public int fib(int n){
        var ans = 1;
        var last = 0;
        for(var i = 2;i <= n;i++){
            var sum = last + ans;
            last = ans;
            ans = sum;
        }
        return ans;
    }
//--------------------------------------------------------------
    public int climbStairs(int n){     
        //if(n == 1) return 1;
        //if(n == 2) return 2;
        // int[] dp = new int[n + 1];
        // dp[1] = 1;
        // dp[2] = 2;
        // for(int i = 3;i <= n;i++){
        //     dp[i] = dp[i - 1] + dp[i - 2];
        // }
        // return dp[n];

        var count = 1;
        for(var i = 1;i <= n;i++){
            var sum = 1;
            for(var j = i;j < 2 * i;j++){
                sum *= (n - j) / (j - i + 1);
            }
            count += sum;
        }
        return count;
    }
//--------------------------------------------------------------
    public int[] sortArray(int[] nums){
        var l = nums.length;
        var mid = l / 2;
        var left = new int[mid];
        var right = new int[l - mid];
        
        for(var i = 0;i < mid;i++){
            left[i] = nums[i];
        }
        for(var i = mid;i < l;i++){
            right[i - mid] = nums[i];
        }

        for (var i = 1; i < mid; i++) {
            for (var j = i; j > 0 && left[j] < left[j - 1]; j--) {
                var temp = left[j];
                left[j] = left[j - 1];
                left[j - 1] = temp;
            }
        }   
        for (var i = 1; i < l - mid; i++) {
            for (var j = i; j > 0 && right[j] < right[j - 1]; j--) {
                var temp = right[j];
                right[j] = right[j - 1];
                right[j - 1] = temp;
            }
        }
        var res = new int[l];
        int i = 0, j = 0, k = 0;
        while (i < mid && j < l - mid) {
            if (left[i] < right[j]) {
                res[k++] = left[i++];
            } else {
                res[k++] = right[j++];
            }
        }
        while(i < mid){
            res[k++] = left[i++];
        }
        while(j < l - mid){
            res[k++] = right[j++];
        }
        return res;
    }
//--------------------------------------------------------------
    public ListNode mergeKLists(ListNode[] lists){
        if(lists == null || lists.length == 0) return null;
        var level = 1;
        while(level < lists.length){
            for(var i = 0;i + level < lists.length;i += level * 2){
                lists[i] = mergeTwoLists(lists[i], lists[i + level]);
            }
            level *= 2;
        }
        return lists[0];
    }
//--------------------------------------------------------------
    public int findKthLargest(int[] nums, int k){
        var heap = new PriorityQueue<Integer>();
        for(var i : nums){
            heap.offer(i);
        }
        while(heap.size() > k){
            heap.poll();
        }
        return heap.peek();
        // Arrays.sort(nums);
        // return nums[nums.length - k];
    }
//--------------------------------------------------------------
    public void sortColors(int[] nums){
        var l = nums.length - 1;
        var point = 0;
        for(var i = 0;i <= l;){
            if(nums[i] == 0){
                swap(nums, i++, point++);
            }else if(nums[i] == 1){
                i++;
            }else{
                swap(nums, i, l--);
            }
        }
    }
     void swap(int[] nums, int i, int j){
        var temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
//--------------------------------------------------------------
    public int search(int[] nums, int target){
        var left = 0;
        var right = nums.length - 1;
        while(left < right){
            var mid = left + (right - left) / 2;
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] > target){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return -1;
    }
//--------------------------------------------------------------
    public boolean searchMatrix(int[][] matrix, int target){
        var m = matrix.length;
        var n = matrix[0].length;
        var start = 0;
        var end = m * n - 1;
        while(start <= end){
            var mid = start + (end - start) / 2;
            var mid_v = matrix[mid/ n][mid % n];
            if(mid_v == target){
                return true;
            }else if(mid_v > target){
               end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        return false;
    }
//--------------------------------------------------------------
    public int minEatingSpeed(int[] piles, int h){
        var start = 1;
        var max = 0;
        for(var i : piles){
            max = Math.max(max, i);
        }
        var end = max;
        while(start < end){
            var mid = start + (end - start) / 2;
            var hours = 0;
            for(var i : piles){
                hours += (i - 1) / mid + 1;
            }
            if(hours <= h){
                end = mid;
            }else{
                start = mid + 1;
            }
        }
        return start;
    }
//--------------------------------------------------------------
    public TreeNode searchBST(TreeNode root, int val){
        if(root == null)return null;
        var r = new TreeNode(val);
        if(root.val == val){
            return root;
        }else if(root.val > val){
            r = searchBST(root.left, val);
        }else{
            r = searchBST(root.right, val);
        }
        return r;
    }
//--------------------------------------------------------------
    public TreeNode insertIntoBST(TreeNode root, int val){
        if(root == null)return new TreeNode(val);
        if(root.val > val){
            root.left = insertIntoBST(root.left, val);
        }else{
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }
//--------------------------------------------------------------
    public TreeNode deleteNode(TreeNode root, int key){
        if(root == null)return null;
        if(root.val > key){
            root.left = deleteNode(root.left, key);
        }else if(root.val < key){
            root.right = deleteNode(root.right, key);
        }else{
            if(root.left == null){
                return root.right;
            }else if(root.right == null){
                return root.left;
            } 
            var min = root.right;
            while(min.left != null){
                min = min.left;
            }
            root.val = min.val;
            root.right = deleteNode(root.right, root.val);   
        }
        return root;
    }
//--------------------------------------------------------------
    public List<Integer> inorderTraversal(TreeNode root){
        var list = new ArrayList<Integer>();
        var stack = new Stack<TreeNode>();
        if(root == null) return list;
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root = root.right;
        }
        return list;
    }
//--------------------------------------------------------------
    public int kthSmallest(TreeNode root, int k){
        var stack = new Stack<TreeNode>();
        if(root == null) return 0;
        var n = 0;
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            n += 1;
            if(n == k) return root.val;
            root = root.right;
        }
        return -1;
    }
//--------------------------------------------------------------
    public TreeNode buildTree(int[] preorder, int[] inorder){
        var map = new HashMap<Integer, Integer>();
        for(var i = 0;i < inorder.length;i++){
            map.put(inorder[i], i);
        }
        var root = build(preorder, inorder, 0, 0, inorder.length - 1, map);
        return root;
    }
    private TreeNode build(int[] preorder, int[] inorder, int prestart, int instart, int inend, Map<Integer, Integer> map){
        if(prestart > preorder.length || instart > inend) return null;
        var node = new TreeNode(preorder[prestart]);
        var mid = map.get(node.val);
        node.left = build(preorder, inorder, prestart + 1, instart, mid - 1, map);
        node.right = build(preorder, inorder, prestart + (mid - instart) + 1, mid + 1, inend, map);
        return node;
    }
//--------------------------------------------------------------
    public List<List<Integer>> levelOrder(TreeNode root){
        List<List<Integer>> list = new ArrayList<>();
        if(root == null)return list;
        var q = new LinkedList<TreeNode>();
        q.add(root);
        while(!q.isEmpty()){
            var size = q.size();
            var l = new ArrayList<Integer>();
            for(var i = 0;i < size;i++){
                TreeNode node = q.poll();
                l.add(node.val);
                if(node.left != null)q.offer(node.left);
                if(node.right != null)q.offer(node.right);
            }
            list.add(l);
        }
        return list;
    }
//--------------------------------------------------------------  
    public List<Integer> rightSideView(TreeNode root){
        List<Integer> list = new ArrayList<>();
        rightV(root, list, 0);
        return list;
    }
    private void rightV(TreeNode root, List<Integer> list, int level){
        if(root == null)return;
        if(list.size() == level){
            list.add(root.val);
        }
        rightV(root.right, list, level + 1);
        rightV(root.left, list, level + 1);
    }
//--------------------------------------------------------------
    public boolean hasPathSum(TreeNode root, int targetSum){
        if(root == null) return false;
        if(root.left == null && root.right == null) return targetSum == root.val;
        var leftsum = hasPathSum(root.left, targetSum - root.val);
        var rightsum = hasPathSum(root.right, targetSum - root.val);
        return leftsum || rightsum;
    }
//--------------------------------------------------------------
    public List<List<Integer>> subsets(int[] nums){
        List<List<Integer>>  list = new ArrayList<>();
        backtrack(nums, 0, list, new ArrayList<>());
        return list;
    }
    private void backtrack(int[] nums, int start, List<List<Integer>> list, List<Integer> l){
        list.add(new ArrayList<>(l));
        for(var i = start;i < nums.length;i++){
            l.add(nums[i]);
            backtrack(nums, i + 1, list, l);
            l.remove(l.size() - 1);
        }
    }
//--------------------------------------------------------------
    public List<List<Integer>> combinationSum(int[] candidates, int target){
        List<List<Integer>> list = new ArrayList<>();
        backtrack(candidates, 0, target, list, new ArrayList<>());
        return list;
    }
    private void backtrack(int[] candidates, int start, int target, List<List<Integer>> list, List<Integer> l){
        if(target < 0){
            return ;
        }else if(target == 0){
            list.add(new ArrayList<>(l));
        }
        for(int i = start;i < candidates.length;i++){
            l.add(candidates[i]);
            backtrack(candidates, i, target - candidates[i], list, l);
            l.remove(l.size() - 1);
        }
    }
//--------------------------------------------------------------
    public int lastStoneWeight(int[] stones){
        var heap = new PriorityQueue<Integer>(Collections.reverseOrder());
        for(var i : stones){
            heap.add(i);
        }
        int x, y = 0;
        while(heap.size() > 1){
            y = heap.poll();
            x = heap.poll();
            if(x < y){
                heap.offer(y - x);
            }
        }
        return heap.isEmpty() ? 0 : heap.poll();
    }
//--------------------------------------------------------------
    public int[][] kClosest(int[][] points, int k){
        var heap = new PriorityQueue<int[]>((a, b) -> (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1]));
        for(var i : points){
            heap.offer(i);
            if(heap.size() > k){
                heap.poll();
            }
        }
        int[][] ans = new int[k][2];
        var i = 0;
        while(!heap.isEmpty()){
            ans[i++] = heap.poll();
        } 
        return ans;
        // Arrays.sort(points, (a, b) -> (b[0]*b[0] + b[1]*b[1]) - (a[0]*a[0] + a[1]*a[1]));
        // return Arrays.copyOfRange(points, 0, k);
    }
//--------------------------------------------------------------
    public boolean containsDuplicate(int[] nums) {
        var set = new HashSet<>();
        for(var i : nums){
            if(set.contains(i)){
                return true;
            }
            set.add(i);
        }
        return false;
    }
//--------------------------------------------------------------
    public int[] twoSum(int[] nums, int target) {
        // var map = new HashMap<Integer, Integer>();
        // for(var i = 0;i < nums.length;i++){
        //     var a = target - nums[i];
        //     if(map.containsKey(a)){
        //         return new int[]{map.get(a), i};
        //     }
        //     map.put(nums[i], i);
        // }
        // return new int[0];
        for(var i = 1;i < nums.length;i++){
            for(var j = i;j < nums.length;j++){
                if(nums[j] + nums[j - i] == target){
                    return new int[]{j, j - i};
                }
            }
        }
        return null;
    }
//--------------------------------------------------------------
    public int numIslands(char[][] grid){
        if(grid == null || grid.length == 0)return 0;
        var m = grid.length;
        var n = grid[0].length;
        var island = 0;
        for(var i = 0;i < m;i++){
            for(var j = 0;j < n;j++){
                if(grid[i][j] == '1'){
                    dfs(grid, i, j);
                    island++;
                }
            }
        }
        return island;
    }
    private void dfs(char[][] grid, int i, int j){
        var m = grid.length;
        var n = grid[0].length;
        if(i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == '0')return;
        grid[i][j] = '0';
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }
//--------------------------------------------------------------
    public int maxAreaOfIsland(int[][] grid) {
        var m = grid.length;
        var n = grid[0].length;
        var max = 0;
        for(var i = 0;i < m;i++){
            for(var j = 0;j < n;j++){
                if(grid[i][j] == 1){
                    max = Math.max(max, dfs(grid, i, j));
                }
            }
        }
        return max;
    }
    private int dfs(int[][] grid, int i, int j){
        var m = grid.length;
        var n = grid[0].length;
        if(i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == 0)return 0;
        var area = 1;
        grid[i][j] = 0;
        area += dfs(grid, i + 1, j);
        area += dfs(grid, i - 1, j);
        area += dfs(grid, i, j + 1);
        area += dfs(grid, i, j - 1);
        return area;
    }
//--------------------------------------------------------------
    public int shortestPathBinaryMatrix(int[][] grid) {
        var n = grid.length;
        if(grid == null || grid[0][0] == 1 || grid[n - 1][n - 1] == 1)return -1;
        var direction = new int[][]{{-1, -1}, {-1, 1}, {-1, 0}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        var seen = new boolean[n][n];
        var q = new LinkedList<int[]>();
        q.add(new int[]{0, 0, 1});
        for(var i = 1;!q.isEmpty();i++){
            for(var j = q.size();j > 0;j--){
                var cell = q.poll();
                if(cell[0] == n - 1 && cell[1] == n - 1)return i;
                for(var move : direction){
                    var x = cell[0] + move[0];
                    var y = cell[1] + move[1];

                    if(x >= 0 && y >= 0 && x < n && y < n && grid[x][y] == 0 && !seen[x][y]){
                        seen[x][y] = true;
                        q.offer(new int[]{x, y});
                    }
                }
                
            }
        }
        return -1;
    }
//--------------------------------------------------------------
    public int orangesRotting(int[][] grid) {
        var m = grid.length;
        var n = grid[0].length;
        var freshcount = 0;
        var time = 0;
        var diraction = new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
        Queue<int[]> q = new LinkedList<>(); 
        for(int i = 0;i < m;i++){
            for(int j = 0;j < n;j++){
                if(grid[i][j] == 1){
                    freshcount++;
                }else if(grid[i][j] == 2){
                    q.offer(new int[]{i, j, 0});
                }
            }
        }
        while(!q.isEmpty()){
            int[] cell = q.poll();
            int t = cell[2];
            time = Math.max(time, t);
            for(int[] dir : diraction){
                int x = cell[0] + dir[0];
                int y = cell[1] + dir[1];

                if(x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1){
                    grid[x][y] = 2;
                    q.offer(new int[]{x, y, t + 1});
                    freshcount--;
                }
            }
        }
        return freshcount == 0 ? time : -1;
    }
//--------------------------------------------------------------
    Map<Node, Node> visited = new HashMap<>();
    public Node cloneGraph(Node node){
        if(node == null) return null;
        if(visited.containsKey(node)) return visited.get(node);
        Node clonenode = new Node(node.val);
        visited.put(node, clonenode);
        for(Node neighbor : node.neighbors){
            clonenode.neighbors.add(cloneGraph(neighbor));
        }
        return clonenode;
    }
//--------------------------------------------------------------
    public boolean canFinish(int numCourses, int[][] prerequisites){
        List<List<Integer>> list = new ArrayList<>();
        for(var i = 0;i < numCourses;i++){
            list.add(new ArrayList<>());
        }
        int[] indegree = new int[numCourses];
        for(int[] prerequisite : prerequisites){
            int course = prerequisite[0];
            int prerequisiteCourse = prerequisite[1];
            list.get(prerequisiteCourse).add(course);
            indegree[course]++; 
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0;i < numCourses;i++){
            if(indegree[i] == 0){
                q.offer(i);
            }
        }
        int count = 0;
        while(!q.isEmpty()){
            int course = q.poll();
            count++;
            for(int nextcourse : list.get(course)){
                if(--indegree[nextcourse] == 0){
                    q.offer(nextcourse);
                }
            }
        }
        return count == numCourses;
    }
//--------------------------------------------------------------
    public int rob(int[] nums){
        var n = nums.length;
        if(nums == null || n == 0)return 0;
        if(n == 1)return nums[0];
        var dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for(var i = 2;i < n;i++){
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }
        return dp[n - 1];
    }
//--------------------------------------------------------------
    public int uniquePaths(int m, int n){
        var dp = new int[m][n];
        for(int i = 0;i < n;i++){
            dp[0][i] = 1;
        }
        for(int i = 0;i< m;i++){
            dp[i][0] = 1;
        }
        for(int i = 1;i < m;i++){
            for(int j = 1;j < n;j++){
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
//--------------------------------------------------------------
    public int uniquePathsWithObstacles(int[][] obstacleGrid){
        var m = obstacleGrid.length;
        var n = obstacleGrid[0].length;

        var dp = new int[m][n];
        for(var i = 0;i < m;i++){
            if(obstacleGrid[i][0] == 1){
                break;
            }
            dp[i][0] = 1;
        }
        for(var i = 0;i < n;i++){
            if(obstacleGrid[0][i] == 1){
                break;
            }
            dp[0][i] = 1;
        }
        if(m == 1 || n == 1)return dp[dp.length - 1][dp[0].length - 1];
        for(int i = 1;i < m;i++){
            for(int j = 1;j < n;j++){
                if(obstacleGrid[i][j] == 1){
                    continue;
                }
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }
//--------------------------------------------------------------
    public int longestCommonSubsequence(String text1, String text2) {
        var m = text1.length();
        var n = text2.length();
        var dp = new int[m + 1][n + 1];
        for(var i = 0;i <= m;i++){
            for(var j = 0;j <= n;j++){
                if(text1.charAt(i - 1) == text2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
//--------------------------------------------------------------
    public int hammingWeight(int n){
        if(n == 0)return 0;
        var count = 0;
        while(n > 0){
            count += n % 2;
            n = n / 2;
        }
        return count;
    }
//--------------------------------------------------------------
    public int[] countBits(int n){
        var ans = new int[n + 1];
        for(var i = 1;i <= n;i++){
            if(i % 2 == 0){
                ans[i] = ans[i / 2];
            }else{
                ans[i] = ans[i - 1] + 1;
            }
        }
        return ans;
    }
//--------------------------------------------------------------
    public int reverseBits(int n){
        var ans = 0;
        for(var i = 0;i < 32;i++){
            ans = (ans<< 1) | (n & 1);
        }
        return ans;
    }
}
