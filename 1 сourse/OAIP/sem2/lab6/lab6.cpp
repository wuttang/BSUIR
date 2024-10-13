#include <iostream>
#include <algorithm>

using namespace std;

struct Node {
    int data;
    Node* left;
    Node* right;

    Node(int value) {
        data = value;
        left = nullptr;
        right = nullptr;
    }
};

Node* insert(Node* root, int value) {
    if (root == nullptr) {
        return new Node(value);
    }

    if (value < root->data) {
        root->left = insert(root->left, value);
    } else {
        root->right = insert(root->right, value);
    }

    return root;
}

int findMaxDepth(Node* root) {
    if (root == nullptr) {
        return 0;
    }

    int leftDepth = findMaxDepth(root->left);
    int rightDepth = findMaxDepth(root->right);

    return 1 + max(leftDepth, rightDepth);
}

void printTree(Node* root, int indent = 0) {
    if (root == nullptr) {
        return;
    }

    const int INDENTATION_SIZE = 4;

    printTree(root->right, indent + INDENTATION_SIZE);

    cout << string(indent, ' ') << root->data << endl;

    printTree(root->left, indent + INDENTATION_SIZE);
}

void inorderTraversal(Node* node, int arr[], int& count) {
    if (node == nullptr) {
        return;
    }

    inorderTraversal(node->left, arr, count);
    arr[count++] = node->data;
    inorderTraversal(node->right, arr, count);
}

Node* buildBalancedBST(int arr[], int start, int end) {
    if (start > end) {
        return nullptr;
    }

    int mid = (start + end) / 2;
    Node* root = new Node(arr[mid]);

    root->left = buildBalancedBST(arr, start, mid - 1);
    root->right = buildBalancedBST(arr, mid + 1, end);

    return root;
}

Node* balanceTree(Node* root) {
    if (root == nullptr) {
        return nullptr;
    }

    int arr[100];
    int count = 0;

    inorderTraversal(root, arr, count);

    return buildBalancedBST(arr, 0, count - 1);
}

int main() {
    Node* root = nullptr;
    root = insert(root, 15);
    root = insert(root, 10);
    root = insert(root, 20);
    root = insert(root, 8);
    root = insert(root, 12);
    root = insert(root, 17);
    root = insert(root, 25);
    root = insert(root, 6);
    root = insert(root, 11);

    cout << "Дерево до балансировки:" << endl;
    printTree(root);
    cout << endl;

    int maxDepth = findMaxDepth(root);
    cout << "Максимальная глубина дерева: " << maxDepth << endl;

    root = balanceTree(root);

    cout << "Дерево после балансировки:" << endl;
    printTree(root);
    cout << endl;

    return 0;
}
