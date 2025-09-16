import java.util.ArrayList;
import java.util.Random;

public class W2Q4 {
    public static void main(String[] args) {
        Random rand = new Random();
        Double m1 = (double)rand.nextInt(1,10);
        Double m2 = (double)rand.nextInt(1,10);
        Double m3 = (double)rand.nextInt(1,10);
        Double m4 = (double)rand.nextInt(1,10);
        ArrayList<Double> test= new ArrayList<>();
        test.add(m1);
        test.add(m2);
        test.add(m3);
        test.add(m4);//随机案例
        ArrayList<Double> nums= new ArrayList<>();
        nums.add(1.0);
        nums.add(2.0);
        nums.add(3.0);
        nums.add(7.0);//成功案例
        point24(nums);
        point24(test);
    }

    /**
     * 解决24点问题的主要方法
     * @param nums 进行计算的数字，初始时刻为输入的四个数字，后续随着回溯递归而减少
     * @return 如果能够通过加减乘除得到24点，返回true；否则返回false
     */
    private static boolean solution(ArrayList<Double> nums) {
        //安全检查
        if (nums.isEmpty()) {return false;}
        //结果判断：如果只有一个数字，检查它是否等于24
        if (nums.size() == 1) {
            return Math.abs(nums.getFirst() - 24.0) < 1e-6;
        }
        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                //选择两个数字进行计算
                Double num1 = nums.get(i);
                Double num2 = nums.get(j);
                ArrayList<Double> list = new ArrayList<>();
                for (int k = 0; k < nums.size(); k++) {
                    //将除了选中的两个数字外的其他数字加入新列表
                    if(k!=i && k!=j){
                        list.add(nums.get(k));
                    }
                }
                //将两个数字的加减乘除结果加入新列表
                list.add(num1+num2);
                //递归调用解决新列表
                if (solution(list)) {
                    System.out.println(num1+"+"+num2+"="+(num1+num2));
                    return true;}
                //去除列表中最后一个元素（即刚刚加入的加法结果），进行回溯
                list.removeLast();
                //开始减法运算
                list.add(num1-num2);
                if (solution(list)) {
                    System.out.println(num1+"-"+num2+"="+(num1-num2));
                    return true;}
                list.removeLast();
                //尝试另一种减法
                list.add(num2-num1);
                if (solution(list)) {
                    System.out.println(num2+"-"+num1+"="+(num1-num2));
                    return true;}
                list.removeLast();
                list.add(num1*num2);
                if (solution(list)) {
                    System.out.println(num1+"*"+num2+"="+(num1*num2));
                    return true;}
                list.removeLast();
                //避免除数为0的情况
                if(num2!=0){
                list.add(num1/num2);
                if (solution(list)) {
                    System.out.println(num1+"/"+num2+"="+(num1/num2));
                    return true;}
                list.removeLast();}
                if (num1!=0) {
                    list.add(num2/num1);
                    if(solution(list)){
                        System.out.println(num2+"/"+num1+"="+(num2/num1));
                        return true;}
                    list.removeLast();
                }
            }
        }

    return false;
    }
    /**
     * 打印24点的计算过程
     * @param nums 进行计算的数字，初始时刻为输入的四个数字
     */
    private  static void point24(ArrayList<Double> nums){
        System.out.println("随机的四个整数是:"+nums.toString());
        if (solution(nums)) {
            System.out.println("找到了24点");
        }
        else{
            System.out.println("No Answer");
        }
    }
}
