import java.util.List;
import java.util.Stack;

public class W2Q5 {
    public static void main(String[] args) {
        Stack<Character> stack_a = new Stack<>();
        Stack<Character> stack_b = new Stack<>();
        Stack<Character> stack_c = new Stack<>();
        List<Character> target_seq = new java.util.ArrayList<>();
        List<String> path = new java.util.ArrayList<>();
        stack_a.push('e');
        stack_a.push('d');
        stack_a.push('c');
        stack_a.push('b');
        stack_a.push('a');
        target_seq.add('c');
        target_seq.add('d');
        target_seq.add('e');
        target_seq.add('a');
        target_seq.add('b');
        System.out.println("初始状态为：");
        System.out.println("A栈为：" + stack_a);
        System.out.println("B栈为：" + stack_b);
        System.out.println("C栈为：" + stack_c);
        System.out.println("目标序列为：" + target_seq);
        if (diaodu(stack_a, stack_b, stack_c, target_seq, 0, path)) {
            for (String str : path) {
                System.out.println(str);
            }
        } else {
            System.out.println("No Solution");
        }
    }

    private static boolean diaodu(Stack<Character> stack_a, Stack<Character> stack_b, Stack<Character> stack_c, List<Character> target_seq, int k, List<String> path) {
        if (k == target_seq.size()) {
            return true;
        }
        char str = target_seq.get(k);
        if (!stack_a.isEmpty() && str == stack_a.peek()) {
            stack_a.pop();
            path.add(str + ":A->D");
            if (diaodu(stack_a, stack_b, stack_c, target_seq, k + 1, path)) {
                return true;
            }
            path.removeLast();
            stack_a.push(str);
        }
        else if (!stack_b.isEmpty() && str == stack_b.peek()) {
            stack_b.pop();
            path.add(str + ":B->D");
            if (diaodu(stack_a, stack_b, stack_c, target_seq, k + 1, path)) {
                return true;
            }
            path.removeLast();
            stack_b.push(str);
        }
        else if (!stack_c.isEmpty() && str == stack_c.peek()) {
            stack_c.pop();
            path.add(str + ":C->D");
            if (diaodu(stack_a, stack_b, stack_c, target_seq, k + 1, path)) {
                return true;
            }
            path.removeLast();
            stack_c.push(str);
        }
        else if (!stack_a.isEmpty() && stack_a.contains(str)) {
            char top_char = stack_a.peek();
            stack_a.pop();
            stack_b.push(top_char);
            path.add(top_char + ":A->B");
            if (diaodu(stack_a, stack_b, stack_c, target_seq, k, path)) {
                return true;
                }
            stack_b.pop();
            path.removeLast();
            stack_c.push(top_char);
            path.add(top_char + ":A->C");
            if (diaodu(stack_a, stack_b, stack_c, target_seq, k, path)) {
                return true;
            }
            stack_c.pop();
            path.removeLast();
            stack_a.push(top_char);
        }
        else if (!stack_b.isEmpty() && stack_b.contains(str)) {
            stack_c.push(stack_b.pop());
            path.add(stack_c.peek() + ":B->C");
            if (diaodu(stack_a, stack_b, stack_c, target_seq, k, path)) {
                return true;
            }
            path.removeLast();
            stack_b.push(stack_c.pop());
            }
        return false;
    }
}
