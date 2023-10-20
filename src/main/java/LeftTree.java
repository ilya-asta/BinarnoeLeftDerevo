
import java.util.Scanner;

class Node {

    Node left, right;
    int data;

    boolean color;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;

        color = true;
    }
}

public class LeftTree {

    private static Node root = null;

    // Поворот нелево <-.
    Node rotateLeft(Node myNode) {
        System.out.printf("поворот влево!!\n");
        Node child = myNode.right;
        Node childLeft = child.left;

        child.left = myNode;
        myNode.right = childLeft;

        return child;
    }

    // Поворот направо->.
    Node rotateRight(Node myNode) {
        System.out.printf("вращение вправо\n");
        Node child = myNode.left;
        Node childRight = child.right;

        child.right = myNode;
        myNode.left = childRight;

        return child;
    }

    // Проверка
    boolean isRed(Node myNode) {
        if (myNode == null) {
            return false;
        }
        return (myNode.color == true);
    }

    void swapColors(Node node1, Node node2) {
        boolean temp = node1.color;
        node1.color = node2.color;
        node2.color = temp;
    }

    Node insert(Node myNode, int data) {

        if (myNode == null) {
            return new Node(data);
        }

        if (data < myNode.data) {
            myNode.left = insert(myNode.left, data);
        } else if (data > myNode.data) {
            myNode.right = insert(myNode.right, data);
        } else {
            return myNode;
        }

        // случай 1.
        // когда правый дочерний элемент красный, а левый дочерний элемент черный или не существует.
        if (isRed(myNode.right) && !isRed(myNode.left)) {
        // Повернуть узел  влево
            myNode = rotateLeft(myNode);

        // Поменять местами цвета дочернего узла всегда должен быть красным
            swapColors(myNode, myNode.left);
        }

        // случай 2
        // когда левый ребенок, а также левый внук выделены красным цветом
        if (isRed(myNode.left) && isRed(myNode.left.left)) {
        // Повернуть узел в право
            myNode = rotateRight(myNode);
            swapColors(myNode, myNode.right);
        }

        // случай 3
        // когда и левый, и правый дочерние элементы окрашены в красный цвет.
        if (isRed(myNode.left) && isRed(myNode.right)) {
        // Инвертировать цвет узла это левый и правый дети.
            myNode.color = !myNode.color;

        // Изменить цвет на черный.
            myNode.left.color = false;
            myNode.right.color = false;
        }

        return myNode;
    }

    void inorder(Node node) {
        if (node != null)
        {
            inorder(node.left);
            char c = '●';
            if (node.color == false)
                c = '◯';
            System.out.print(node.data + ""+c+" ");
            inorder(node.right);
        }
    }

    public static void main(String[] args) {

        LeftTree node = new LeftTree();
        Scanner scan = new Scanner(System.in);

        char ch;
        do {
            System.out.println("Введите целое число");

            int num = scan.nextInt();
            root = node.insert(root, num);

            node.inorder(root);
            System.out.println("\nВы хотите продолжить? (введите 1 или 0)");
            ch = scan.next().charAt(0);
        } while (ch == '1' || ch == '0');
    }
}