// variant 4

#include <iostream>
#include <cmath>

using namespace std;

int main()
{
    double y,x,z;
    cout << "Введите z: ";
    cin >> z;

    if (!cin  || cin.get() != '\n') {
        cout << "Введите числовое значение.";
        return 0;
    }

    if (z<1) {
        x = pow(z,3) + 0.2;
        cout << "Вычисление x было выполнено через 1-ую ветвь." << "\n";
    } else if (z >= 1) {
        x = z + log(z);
        cout << "Вычисление x было выполнено через 2-ую ветвь." << "\n";
    }

    y = pow(cos(pow(x, 2)),3) + pow(sin(pow(x, 3)), 2);
    cout << "y = " << y;

    return 0;
}