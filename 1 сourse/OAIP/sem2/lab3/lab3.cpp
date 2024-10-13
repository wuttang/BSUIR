#include <iostream>

using namespace std;

struct Node {
    int data;
    Node* next;
};

struct Stack {
    Node* head;
    Node* tail;
};

void push(Stack* stack, int data) {
    Node* node = new Node;
    node->data = data;
    node->next = stack->head;
    stack->head = node;
    if (!stack->tail) {
        stack->tail = node;
    }
}

int pop(Stack* stack) {
    if (!stack->head) {
        return -1; // если стек пустой
    }
    int data = stack->head->data;
    Node* node = stack->head;
    stack->head = stack->head->next;
    if (!stack->head) {
        stack->tail = NULL;
    }
    delete node;
    return data;
}

void swap_first_last(Stack* stack) {
    if (!stack->head || !stack->head->next) {
        return; // если в стеке меньше двух элементов
    }
    int first_data = stack->head->data;
    int last_data = stack->tail->data;
    stack->head->data = last_data;
    stack->tail->data = first_data;
}

void print_stack(Stack* stack) {
    Node* node = stack->head;
    while (node) {
        cout << node->data << " ";
        node = node->next;
    }
    cout << endl;
}

double num_check()
{   
    int n;
    do {
        cin >> n;
        if (!cin || cin.get() != '\n') {
            cin.clear();
            cin.ignore((numeric_limits<streamsize>::max)(), '\n');
            cout << "Повторите попытку: ";
        } else {
            return n;
        }
    } while (true);
}

int main() {
    Stack stack = { NULL, NULL };
    int size = 0, elem = 0;

    cout << "Введите размер стека: ";
    size = num_check();
    for (int i = 0; i < size; i++){
        cout << "Введите элемент стека: ";
        elem = num_check();
        push(&stack, elem);
    }
    print_stack(&stack);
    
    swap_first_last(&stack);
    print_stack(&stack);
    return 0;
}
