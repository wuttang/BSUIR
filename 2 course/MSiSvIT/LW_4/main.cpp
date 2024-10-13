#include <iostream>
#include <cmath>

using namespace std;

int sum(int a, int b) {
    if (a > b) return a + b;
    else if (a < b) return b - a;
    else return a * 2;
}

int max(int a, int b) {
    if (a > b) return a;
    else if (a < b) return b;
    else return 0;
}

int main() {
    int y;
    int x;
    double n;
    double p;
    double pr;
    cout << "VVedite razemer: ";
    cin >> n;
    int *a = new int[n];
    // Vvod
    for (int i = 0; i < n; i++) {
        cout << "VVedite nomer " << i << " : ";
        cin >> a[i];
    }
    p = -1;
    // Vivod
    for (int i = 0; i < n; i++) {
        if ((a[i] > 0) && (p < i)) p = i;
    }
    // cout << "Nomer poslednego poloshitelnogo = "<< p << endl;
    x = -1;
    for (int i = 0; i < n; i++) {
        if ((a[i] > 0) && (i < p)) x = i;
    }
    // cout << "Nomer predposlednego poloshitelnogo = " << x << endl;
    pr = 1;
    for (int i = x + 1; i < p; i++) {
        pr *= a[i];
    }

    if (x == (p - 1)) cout << "Oshibka vvoda" << endl;
    else cout << "Proisvedenie: " << pr << endl;
    cout << sum(x, n) << endl;
    cout << max(n, p) << endl;
    return 0;
}