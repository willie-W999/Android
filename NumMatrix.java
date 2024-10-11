public class NumMatrix {
    int[][] prefixsum;
    public NumMatrix(int[][] matrix) {
        prefixsum = new int[matrix.length + 1][matrix[0].length + 1];
        for(var i = 1;i <= matrix.length;i++){
            for(var j = 1;j <= matrix[0].length;j++){
                prefixsum[i][j] = matrix[i - 1][j - 1] + prefixsum[i - 1][j] + prefixsum[i][j - 1] - prefixsum[i - 1][j - 1];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        if(prefixsum == null)return 0;
        return prefixsum[row2 + 1][col2] - prefixsum[row1][col2 + 1] - prefixsum[row2 + 1][col1] + prefixsum[row1][col1];
    }
}
