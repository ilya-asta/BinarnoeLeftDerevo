public class BinaryTree {
    Node root;
    class Node {
        int value;
        Node parent;
        Node left;
        Node right;
        String color;
    }


    public void insert(int value) {
        if(root == null){
            root = new Node();
            root.value = value;
            root.color = "BLACK";
        } else {
            Node newNode = new Node();
            newNode.value = value;
            newNode.color = "RED";
            insert(root, newNode);
        }
    }

    private void insert(Node parent, Node newNode) {
        if (newNode.value < parent.value) {
            if (parent.left == null) {
                parent.left = newNode;
                newNode.parent = parent;
                balanceTreeAfterInsert(newNode);
            } else {
                insert(parent.left, newNode);
            }
        } else {
            if (parent.right == null) {
                parent.right = newNode;
                newNode.parent = parent;
                balanceTreeAfterInsert(newNode);
            } else {
                insert(parent.right, newNode);
            }
        }
    }

    private void balanceTreeAfterInsert(Node node) {
        while (node.parent != null && node.parent.color.equals("RED")) {
            Node uncle;
            if (node.parent == node.parent.parent.left) {
                uncle = node.parent.parent.right;
                if (uncle != null && uncle.color.equals("RED")) {
                    node.parent.color = "BLACK";
                    uncle.color = "BLACK";
                    node.parent.parent.color = "RED";
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.right) {
                        node = node.parent;
                        leftRotate(node);
                    }
                    node.parent.color = "BLACK";
                    node.parent.parent.color = "RED";
                    rightRotate(node.parent.parent);
                }
            } else {
                uncle = node.parent.parent.left;
                if (uncle != null && uncle.color.equals("RED")) {
                    node.parent.color = "BLACK";
                    uncle.color = "BLACK";
                    node.parent.parent.color = "RED";
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        rightRotate(node);
                    }
                    node.parent.color = "BLACK";
                    node.parent.parent.color = "RED";
                    leftRotate(node.parent.parent);
                }
            }
        }
        root.color = "BLACK";
    }

    private void leftRotate(Node node) {
        Node right = node.right;
        node.right = right.left;
        if (right.left != null) {
            right.left.parent = node;
        }
        right.parent = node.parent;
        if (node.parent == null) {
            root = right;
        } else if (node == node.parent.left) {
            node.parent.left = right;
        } else {
            node.parent.right = right;
        }
        right.left = node;
        node.parent = right;
    }

    private void rightRotate(Node node) {
        Node left = node.left;
        node.left = left.right;
        if (left.right != null) {
            left.right.parent = node;
        }
        left.parent = node.parent;
        if (node.parent == null) {
            root = left;
        } else if (node == node.parent.left) {
            node.parent.left = left;
        } else {
            node.parent.right = left;
        }
        left.right = node;
        node.parent = left;
    }

}
