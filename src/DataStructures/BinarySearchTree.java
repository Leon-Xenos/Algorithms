package DataStructures;

import java.util.Collection;

public class BinarySearchTree<T extends Comparable<T>> {

    protected BinaryNode<T> root = null;

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(new Integer[]{5, 2, 1, 3, 8, 7, 9, 10, 0, 11});
        System.out.println(tree);

        System.out.println(tree.contains(6));
        System.out.println(tree.contains(5));
        if (!tree.remove(5)) System.out.println("5 not contains");
        if (!tree.remove(4)) System.out.println("4 not contains");
        System.out.println(tree.contains(5));

        System.out.println(tree);
        for (int i = 0; i < 12; i++) {
            tree.remove(i);
            System.out.println(tree);
        }
        System.out.println(tree.isEmpty());

        BinarySearchTree<String> stringTree = new BinarySearchTree<>();
        stringTree.add(new String[]{"elephant", "panda", "ann", "alpha", "apple"});
        stringTree.add(new String[]{"like", "love", "and", "peace", "come", "game", "us", "helmet"});
        stringTree.add(new String[]{"dog", "announce"});
        System.out.println(stringTree);
    }

    public void add(Collection<T> collection) {
        for (T t : collection) {
            add(t);
        }
    }

    public void add(T[] array) {
        for (T t : array) {
            add(t);
        }
    }

    public void add(T value) {
        if (root == null) root = new BinaryNode<>(value);
        BinaryNode<T> iter = root;
        boolean inserted = false;
        int result;
        while (!inserted) {
            result = iter.value.compareTo(value);
            if (result == 0) {
                inserted = true;
            } else if (result > 0) {
                if (iter.left == null) {
                    iter.left = new BinaryNode<>(value);
                    inserted = true;
                } else iter = iter.left;
            } else {
                if (iter.right == null) {
                    iter.right = new BinaryNode<>(value);
                    inserted = true;
                } else iter = iter.right;
            }
        }
    }

    public boolean contains(T value) {
        if (root == null) return false;
        BinaryNode<T> iter = root;
        int result;
        while (iter != null) {
            result = iter.value.compareTo(value);
            if (result == 0) {
                return true;
            } else if (result > 0) {
                iter = iter.left;
            } else {
                iter = iter.right;
            }
        }
        return false;
    }

    public boolean remove(T value) {
        if (root == null) return false;
        BinaryNode<T> iter = root;
        BinaryNode<T> last = null;
        int result;
        while (iter != null) {
            result = iter.value.compareTo(value);
            if (result == 0) {
                if (iter.left != null) {
                    BinaryNode<T> leftMax = iter.left;
                    while (leftMax.right != null) {
                        leftMax = leftMax.right;
                    }
                    T max = leftMax.value;
                    remove(leftMax.value);
                    iter.value = max;

                } else if (iter.right != null) {
                    BinaryNode<T> rightMin = iter.right;
                    while (rightMin.left != null) {
                        rightMin = rightMin.left;
                    }
                    T min = rightMin.value;
                    remove(rightMin.value);
                    iter.value = min;
                } else {
                    if (last == null) root = null;
                    else if (last.left == null) last.right = null;
                    else if (last.right == null) last.left = null;
                    else {
                        if (last.left.value.compareTo(value) == 0) {
                            last.left = null;
                        } else {
                            last.right = null;
                        }
                    }
                }
                return true;
            } else if (result > 0) {
                last = iter;
                iter = iter.left;
            } else {
                last = iter;
                iter = iter.right;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        if (root == null) return null;
        return TreeBuilder(root, 0).toString();
    }

    private StringBuilder TreeBuilder(BinaryNode<T> node, int level) {
        StringBuilder result = new StringBuilder();
        if (node.left != null) {
            result.append(TreeBuilder(node.left, level + 1));
        }
        result.append("\t".repeat(level)).append(node.value).append("\n");
        if (node.right != null) {
            result.append(TreeBuilder(node.right, level + 1));
        }
        return result;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private static class BinaryNode<T> {
        T value;
        BinaryNode<T> left = null;
        BinaryNode<T> right = null;

        BinaryNode(T value) {
            this.value = value;
        }
    }
}
