public class W2Q3 {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7,8,9,10};
        int k = 9;
        int left = 0;
        int right = nums.length - 1;
        int res = solution(left,right,k,nums);
        if(res==-1) {
            System.out.println("该元素在数组中未找到");
        }
        else{
        System.out.println(k+"在数组中的索引是"+res);}
    }
    /**
     * 查找数组中是否存在目标元素，如果存在返回元素的索引，否则返回-1
     * @param left 左边界
     * @param right 右边界
     * @param num 目标元素
     * @param nums 数组
     * @return 目标元素的索引
     */
    private static int solution(int left,int right,int num,int[] nums) {
        if(left > right){
            return -1;
        }
        int mid = (left + right) / 2;
        if (nums[mid] < num) {
            left = mid + 1;
        }
        else if (nums[mid] > num) {
            right = mid - 1;
            }
        else {
            return mid;
            }
        return solution(left,right,num,nums);
    }
}

