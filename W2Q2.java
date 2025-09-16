import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class W2Q2 {
    public static void main(String[] args) {
        //创建一个树
        CreateTree createTree = new CreateTree();
        //设置树的深度为10
        TreeNode node = createTree.treeCreate(10);
        //使用printTree方法打印树的结构
        System.out.println("\n树的结构 (简易打印):");
        printTree(node);
        //使用preorder方法和preorder2方法分别遍历树，将遍历结果存储到List中
        List<Double> nodes = new ArrayList<>();
        createTree.preorder(node,nodes);
        //使用preorder2方法遍历树，将遍历结果存储到List中
        List<Double> nodes1 = new ArrayList<>();
        createTree.preorder2(node,nodes1);
        //打印preorder和preorder2的遍历结果
        System.out.println("preorder遍历结果:");
        System.out.println(nodes.toString());
        //preorder2使用Morris遍历，可以在不增加时间复杂度的情况下降低空间复杂度
        System.out.println("preorder2遍历结果:");
        System.out.println(nodes1.toString());
        //将preorder的遍历结果转换为Double数组
        Double[] res = nodes.toArray(new Double[0]);
        System.out.println(Arrays.toString(res));
    }
    /**
     * 打印树的结构
     * @param node 当前节点
     * @param prefix 前缀，用于表示当前节点的位置,区分当前节点是左子节点还是右子节点以及层次
     * @param isLeft 表示当前节点是否为左子节点
     */
    public static void printTree(TreeNode node, String prefix, boolean isLeft) {
        if (node == null) {
            return;
        }

        System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.id);

        // 打印子节点时，需要根据当前节点的情况调整前缀
        // 如果有右子节点，左子树的连接线应该是'│'；否则是' '
        String childPrefix = prefix + (isLeft ? "│   " : "    ");

        // 递归打印，注意最后一个节点要用'└──'
        if (node.left != null || node.right != null) {
            printTree(node.left, childPrefix, true);
            printTree(node.right, childPrefix, false);
        }
    }

    // 一个方便调用的入口函数
    /**
     * 打印树的结构
     * @param root 根节点
     */
    public static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("树为空！");
            return;
        }
        System.out.println(root.id); // 先打印根节点
        printTree(root.left, "", true);
        printTree(root.right, "", false);
    }
}
