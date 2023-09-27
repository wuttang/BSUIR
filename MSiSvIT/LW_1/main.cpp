#include <iostream>

using namespace std;

int main() {
    int n = 0;
    bool k = true;
    cin >> n;

    if (n > 100 || n < 0) {
        cout << "Количество элементов выходит за границы";
        return 1;
    }

    int *a = new int[n];

    for (int i = 0; i < n; i++)
        cin >> a[i];

    for (int i = 0; i < n - 1; i++)
        if ((a[i] + a[i + 1]) % 2 == 0) {
            k = false;
            break;
        } // сложность = 5*n+1
    if (!k) cout << "no";
    else cout << "yes";

    return 0;
}


// сложность всего кода 508 с
// 5*n+8

