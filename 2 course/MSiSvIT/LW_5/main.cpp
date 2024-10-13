#include <iostream>
#include <cmath>
using namespace std;
int main()
{
    int y;
    int x;
    double n;
    double p;
    double pr;
    cout << "VVedite razemer: ";
    cin >> n;
    int* a = new int[n];
    // Vvod
    for (int i = 0; i < n; i++)
    {
        cout << "VVedite nomer " << i << " : ";
        cin >> a[i];
    }
    p = -1;
    // Vivod
    for (int i = 0; i < n; i++)
    {
        if ((a[i] > 0) && (p < i)) p = i;
    }
    // cout << "Nomer poslednego poloshitelnogo = "<< p << endl;
    x = -1;
    for (int i = 0; i < n; i++)
    {
        if ((a[i] > 0) && (i < p)) x = i;
    }
    // cout << "Nomer predposlednego poloshitelnogo = " << x << endl;
    pr = 1;
    for (int i = x + 1; i < p; i++)
    {
        pr *= a[i];

        switch(x) {
            case(1): {
                cout << "1" << endl;
                break;
            }

            case(2): {
                cout << "2" << endl;
                break;
            }

            case(3): {
                cout << "3" << endl;
                break;
            }

            default: {
                cout << "default" << endl;
                break;
            }
        }
    }

    if (x == (p - 1)) cout << "Oshibka vvoda";
    else cout << "Proisvedenie: " << pr;
    return 0;
}