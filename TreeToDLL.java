public class TreeToDLL {

  public static class TreeNode {
    public int value;

    public TreeNode(int value) {
      this.value = value;
    }

    public TreeNode left;
    public TreeNode right;
  }

  public static void main(String[] args)  {

    TreeNode root = new TreeNode(10);
    root.left = new TreeNode(9);
    root.right = new TreeNode(8);
    root.left.left = new TreeNode(7);
    root.left.right = new TreeNode(6);
    root.right.left = new TreeNode(5);
    root.right.right = new TreeNode(4);
    root.left.left.left = new TreeNode(3);
    root.left.right.right = new TreeNode(2);
    root.right.left.left = new TreeNode(1);

    TreeNode[] headAndTail = toDoublyLinkedList(root);

    // First element in the array is head of the linked list
    TreeNode head = headAndTail[0];
    while (head != null) {
      System.out.println(head.value);
      head = head.right;
    }

  }

  private static TreeNode[] toDoublyLinkedList(TreeNode root) {
    if (null == root) {
      return null;
    }
    TreeNode[] currentHeadAndTail = new TreeNode[2];
    // Get head and tail of the left subtree
    TreeNode[] leftHeadAndTail = toDoublyLinkedList(root.left);
    if (null == leftHeadAndTail) {
      currentHeadAndTail[0] = root;
    } else {
      // Head of left subtree is the left of current node
      root.left = leftHeadAndTail[1];
      root.left.right = root;
      // The new tail is the tail of the left subtree
      currentHeadAndTail[0] = leftHeadAndTail[0];
    }
    // Get head and tail of the left subtree
    TreeNode[] rightHeadAndTail = toDoublyLinkedList(root.right);
    if (null == rightHeadAndTail) {
      currentHeadAndTail[1] = root;
    } else {
      // Tail of right subtree is right of current node
      root.right = rightHeadAndTail[0];
      root.right.left = root;
      // The new head is head of the right subtree
      currentHeadAndTail[1] = rightHeadAndTail[1];
    }
    return currentHeadAndTail;
  }

}