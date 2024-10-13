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

    for (int i = 0; i < n - 1; i++) // Рассматриваемый участок кода // 2n+1
        if ((a[i] + a[i + 1]) % 2 == 0) { // n
            k = false; // n
            break; // n
        } // сложность = 5*n+1
    if (!k) cout << "no";
    else cout << "yes";

    return 0;
    // сложность всего кода 508 с
    // 5*n+8
}
// SLOC absolute = 23 ед.
// SLOC relative = 23 / 31 = 0,741.
// CLOC absolute = 7 ед.
// CLOC relative = 7 / 31 = 0,23.
