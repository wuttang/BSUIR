#include <iostream>
#include <stack>

using namespace std;

void swapExtremes(stack<int>& s) {
    if (s.empty()) return;
    int top = s.top();
    s.pop();
    if (s.empty()) {
        s.push(top);
        return;
    }
    int bottom = s.top();
    s.pop();
    s.push(top);
    s.push(bottom);
}

void printStack(stack<int> s) {
    while (!s.empty()) {
        cout << s.top() << " ";
        s.pop();
    }
    cout << endl;
}

int main() {
    stack<int> s;
    int n, num;
    cout << "Enter the number of elements to add: ";
    cin >> n;
    cout << "Enter the elements: ";
    for (int i = 0; i < n; i++) {
        cin >> num;
        s.push(num);
    }
    cout << "Stack elements: ";
    printStack(s);
    swapExtremes(s);
    cout << "Stack after swapping extremes: ";
    printStack(s);
    return 0;
}