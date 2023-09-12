#include <iostream>

using namespace std;

struct Node {
    int data;
    Node* next;
    Node* prev;
};

struct Queue {
    Node* head;
    Node* tail;
};

void push(Queue* queue, int data) {
    Node* node = new Node;
    node->data = data;
    node->next = NULL;
    node->prev = queue->tail;
    if (queue->tail) {
        queue->tail->next = node;
    } else {
        queue->head = node;
    }
    queue->tail = node;
}

int pop(Queue* queue) {
    if (!queue->head) {
        return -1; // если очередь пустая
    }
    int data = queue->head->data;
    Node* node = queue->head;
    queue->head = queue->head->next;
    if (queue->head) {
        queue->head->prev = NULL;
    } else {
        queue->tail = NULL;
    }
    delete node;
    return data;
}

void swap_first_last(Queue* queue) {
    if (!queue->head || !queue->head->next) {
        return; // если в очереди меньше двух элементов
    }
    int first_data = queue->head->data;
    int last_data = queue->tail->data;
    queue->head->data = last_data;
    queue->tail->data = first_data;
}

void print_queue(Queue* queue) { 
    Node* node = queue->head;
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

int main()
{
    Queue queue = {NULL, NULL};
     int size = 0, elem = 0;

    cout << "Введите размер очереди: ";
    size = num_check();
    for (int i = 0; i < size; i++){
        cout << "Введите элемент очереди: ";
        elem = num_check();
        push(&queue, elem);
    }
    print_queue(&queue);
    
    swap_first_last(&queue);
    print_queue(&queue);
    return 0;
}