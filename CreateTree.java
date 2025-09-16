import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class CreateTree {
    private final Random rand = new Random(12);
    private final AtomicInteger atomicInteger = new AtomicInteger(0);
    public TreeNode treeCreate(int targetDepth){
        TreeNode root = new TreeNode(0,1);
        createMainLoop(targetDepth,root);
        createSubLoop(targetDepth,root,0,0.6);
        return root;
    }
    /**
     * 创建树的主干，确保树的深度达到我们的要求
     *@param targetDepth 为我们要求的树的深度
     *@param root 是我们创建的树的根节点
     */
    private void createMainLoop(int targetDepth,TreeNode root){
        int depth = 0;
        while (depth < targetDepth){
            if(rand.nextBoolean()){
                //通过随机数判断是否创建左子树，使用atomicInteger确保id的唯一性
                root.left = new TreeNode(atomicInteger.incrementAndGet(),rand.nextInt(100));
               //如果创建了左子树，将当前节点指向左子树
                root = root.left;
            }
            else{
                //通过随机数判断是否创建右子树，使用atomicInteger确保id的唯一性
                root.right = new TreeNode(atomicInteger.incrementAndGet(),rand.nextInt(100));
                root = root.right;
            }
            depth++;
        }
    }
    /**
     * 创建树的枝叶，确保树的每个节点都有左右子树
     * @param targetDepth 为我们要求的树的深度
     * @param root 是我们创建的树的根节点
     * @param depth 是当前节点的深度
     * @param p 是创建子树的概率
     */
    private void createSubLoop(int targetDepth,TreeNode root,int depth,double p){
        //如果当前节点为空或者当前节点的深度已经达到了目标深度，直接返回
        if(root==null||depth>=targetDepth){
            return;
        }
        //如果当前节点的左子树为空，通过随机数判断是否创建左子树，使用atomicInteger确保id的唯一性
        if(root.left==null){
            root.left = rand.nextDouble()<p?new TreeNode(atomicInteger.incrementAndGet(),rand.nextInt(100)):null;
        }
        //如果当前节点的右子树为空，通过随机数判断是否创建右子树，使用atomicInteger确保id的唯一性
        if(root.right==null){
            root.right = rand.nextDouble()<p?new TreeNode(atomicInteger.incrementAndGet(),rand.nextInt(100)):null;
        }
        createSubLoop(targetDepth,root.left,depth+1,p);
        createSubLoop(targetDepth,root.right,depth+1,p);
    }
    /**
     * 前序遍历树，将树的节点值添加到List中
     * @param node 是当前遍历的节点
     * @param nodes 是存储节点值的List
     */
    public void preorder(TreeNode node, List<Double> nodes) {
        if (node == null) {
            return;
        }
        nodes.add(node.val);
        preorder(node.left, nodes);
        preorder(node.right, nodes);
    }
    /**
     * 改写Morris前序遍历方法，使其适用于先序遍历
     * @param node 是当前遍历的节点
     * @param nodes 是存储节点值的List
     */
    public void preorder2(TreeNode node, List<Double> nodes) {
        while (node != null) {
            if (node.left != null) {
                TreeNode temp = node.left;
                //如果当前节点的左子树不为空，找到左子树的最右节点
                //如果最右节点的右指针为空，将最右节点的右指针指向当前节点，将当前节点指向左子树

                while (temp.right!=null&&temp.right!=node){
                    temp = temp.right;
                }
                if (temp.right==null){
                    //如果最右节点的右子节点为空，说明该节点是第一次遍历到，将当前节点的值添加到List中
                    nodes.add(node.val);
                    temp.right = node;
                    node = node.left;
                }
                //如果最右节点的右指针指向当前节点，说明左子树已经遍历完，将最右节点的右指针指向空，将当前节点指向右子树
                else{
                    temp.right = null;
                    node = node.right;
                }
            }
            //如果当前节点的左子树为空，将当前节点的值添加到List中，将当前节点指向右子树
            else {
                //确保在第一次遍历到当前节点时，将当前节点的值添加到List中
                nodes.add(node.val);
                node = node.right;
            }
        }


    }
}
