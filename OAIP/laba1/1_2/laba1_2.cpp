// varinat 4

#include <iostream>
#include <cmath>

using namespace std;

int main()

{
    cout.precision(5);
    double alpha,z1,z2, PI = 3.1415926;
    cout << "Введите alpha (в радианах): ";
    cin >> alpha;

    if (!cin) {
        cin.clear();
        cin.ignore();
        cout << "Введите числовое значение.";
        return 0;
    }

    if ((1+cos(4*alpha))*(1+cos(2*alpha)) !=0) {
        z1 = (sin(4*alpha)*cos(2*alpha))/((1+cos(4*alpha))*(1+cos(2*alpha)));
        z2 = 1.0/tan((3*PI/2)-alpha);
        cout << "z1 = " << z1 << "\n";
        cout << "z2 = " << z2 << "\n";
    }
    else {
    cout << "Что с тобой не так?";
    return 0;
    }
}