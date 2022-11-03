package own_jokes;

import psa.labs.Lab_7;

import java.util.function.Consumer;

public class RedBlackTree {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        tree.insertNode(2);
        tree.insertNode(-5);
        tree.insertNode(12);
        tree.insertNode(-10);
        tree.insertNode(1);
        tree.insertNode(10);
        tree.insertNode(15);
        tree.insertNode(7);
        tree.insertNode(11);
        tree.insertNode(13);
        tree.insertNode(16);
        tree.walk(tree.root);
        tree.deleteNode(15);
        System.out.println();
        tree.walk(tree.root);
//        System.out.println(tree.root.color);
//        System.out.println(tree.root.value);
//
//        System.out.println(tree.root.rightChild.value);
//
//        System.out.println(tree.root.rightChild.color);
//        System.out.println(tree.root.leftChild.value);
//
//        System.out.println(tree.root.rightChild.color);
//        System.out.println(tree.root.rightChild.value);
    }

    public static class Node {
        boolean color;
        Node parent;
        Node leftChild;
        Node rightChild;
        int value;

        public Node(int value, Node parent) {
            this.value = value;
            this.parent = parent;
            color = false;
        }
    }

    Node root;

    public void insertNode(int value) {
        if (root == null) {
            root = new Node(value, null);
            root.color = true;
            return;
        }
        Node current = root;
        while (true) {
            if (value > current.value) {
                if (current.rightChild == null) {
                    current.rightChild = new Node(value, current);
                    current = current.rightChild;
                    break;
                } else {
                    current = current.rightChild;
                }
            } else if (value < current.value) {
                if (current.leftChild == null) {
                    current.leftChild = new Node(value, current);
                    current = current.leftChild;
                    break;
                } else {
                    current = current.leftChild;
                }
            } else {
                break;
            }
        }
        checkRedBlackProp(current);
    }

    public void checkRedBlackProp(Node current) {
        while (current.parent != null && !current.parent.color) {
            Node p = current.parent;
            Node gP = p.parent;
            if (p == gP.leftChild) {
                if (gP.rightChild != null && !gP.rightChild.color) {
                    gP.color = false;
                    gP.rightChild.color = true;
                    gP.leftChild.color = true;
                    current = gP;
                } else if (current == p.rightChild) {
                    current = p;
                    leftRotate(current);
                } else {
                    p.color = true;
                    gP.color = false;
                    rightRotate(gP);
                }
            } else {
                if (gP.leftChild != null && !gP.leftChild.color) {
                    current = gP;
                } else if (current == p.leftChild) {
                    current = p;
                    rightRotate(p);
                } else {
                    p.color = true;
                    gP.color = false;
                    leftRotate(gP);
                }
            }
        }
        root.color = true;
    }

    public void leftRotate(Node node) {
        Node p = node.parent;
        Node x = node.rightChild;
        node.rightChild = x.leftChild;
        if (p == null) {
            root = x;
        } else if (node == p.leftChild) {
            p.leftChild = x;
        } else {
            p.rightChild = x;
        }
        x.parent = p;
        node.parent = x;
        x.leftChild = node;
    }

    public void rightRotate(Node node) {
        Node p = node.parent;
        Node x = node.leftChild;
        node.leftChild = x.rightChild;
        if (p == null) {
            root = x;
        } else if (node == p.rightChild) {
            p.rightChild = x;
        } else {
            p.leftChild = x;
        }
        x.parent = p;
        node.parent = x;
        x.rightChild = node;
    }

    private void walk(Node node) {
        if (node.leftChild != null) {
            walk(node.leftChild);
        }
        if (node.rightChild != null) {
            walk(node.rightChild);
        }
        System.out.println(node.value + " " + node.color);
//        System.out.println(node.color);
    }

    public void deleteNode(int value) {
        Node current = root;
        boolean color;
        while (true) {
            if (current.value == value) {
                color = current.color;
                if (current.leftChild == null) {
                    Node x = current.rightChild;
                    x.parent = current.parent;
                    if (current.parent.leftChild == current) {
                        current.parent.leftChild = x;
                    } else  {
                        current.parent.rightChild = x;
                    }
                } else if (current.rightChild == null) {
                    Node x = current.leftChild;
                    x.parent = current.parent;
                    if (current.parent.leftChild == current) {
                        current.parent.leftChild = x;
                    } else  {
                        current.parent.rightChild = x;
                    }
                } else {
                    Node yy = current.rightChild;
                    while (true) {
                        if (yy.leftChild != null) {
                            yy = yy.leftChild;
                        } else {
                            break;
                        }
                    }
                    color = yy.color;
                    Node x = yy.rightChild;
                    if (yy != current.rightChild) {
                        if (x == null) {
                            yy.parent.leftChild = null;
                        } else {
                            yy.parent.leftChild = x;
                            x.parent = yy.parent;
                        }
                    }
                    yy.parent = current.parent;
                    if (current.parent != null) {
                        if (current.parent.rightChild == current) {
                            current.parent.rightChild = yy;
                        } else {
                            current.parent.leftChild = yy;
                        }
                        yy.leftChild = current.leftChild;
                        yy.rightChild = current.rightChild;
                        yy.rightChild.parent = yy;
                        yy.leftChild.parent = yy;
                        color = yy.color;
                    }
                }
                if (color) {
                    deleteFix(current);
                }
                break;
            }


            if (value > current.value) {
                if (current.rightChild == null) {
                    System.out.println("cannot delete node");
                    break;
                } else {
                    current = current.rightChild;
                }
            } else {
                if (current.leftChild == null) {
                    System.out.println("cannot delete node");
                    break;
                } else {
                    current = current.leftChild;
                }
            }
        }
    }

    public void deleteFix(Node x) {
        while (x.color && x != root) {
            Node p = x.parent;
            if (p.leftChild == x) {
                Node w = p.rightChild;
                if (!w.color) {
                    w.color = true;
                    p.color = false;
                    leftRotate(p);
                    p = x.parent;
                    p.leftChild = w;
                } else if (w.rightChild.color == w.leftChild.color && w.rightChild.color) {
                    w.color = false;
                    x = x.parent;
                } else if (w.leftChild.color) {
                    w.rightChild.color = true;
                    w.color = false;
                    rightRotate(w);
                    p.leftChild = w;
                } else {
                    w.color = p.color;
                    p.color = true;
                    w.leftChild.color = true;
                    leftRotate(p);
                    root = x;
                }
            } else {
                Node w = p.leftChild;
                if (!w.color) {
                    w.color = true;
                    p.color = false;
                    rightRotate(p);
                    p = x.parent;
                    p.leftChild = w;
                } else if (w.rightChild.color == w.leftChild.color && w.rightChild.color) {
                    w.color = false;
                    x = x.parent;
                } else if (w.leftChild.color) {
                    w.rightChild.color = true;
                    w.color = false;
                    leftRotate(w);
                    p.leftChild = w;
                } else {
                    w.color = p.color;
                    p.color = true;
                    w.leftChild.color = true;
                    rightRotate(p);
                    root = x;
                }
            }
            x.color = true;
        }
    }
}
