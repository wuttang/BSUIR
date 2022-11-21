// variant 4

#include <iostream>
#include <cmath>

using namespace std;

int main()
{
    int num; 
    double x,a,b,y,z,f;
    cout << "Введите z, a, b: ";
    cin >> z >> a >> b;

    if (!cin  || cin.get() != '\n') {
        cout << "Введите числовое значение.";
        return 0;
    }

    cout << "Выберите функцию: 1 - f(x) = 2*x; 2 - f(x) = x*x; 3 - f(x) = x/3" << "\n";
    cin >> num;

    switch(num)
    {
        case 1: f = 2*x;
                cout << "Выбрана 1-ая функция." << "\n"; break;
        case 2: f = x*x;
                cout << "Выбрана 2-ая функция." << "\n"; break;
        case 3: f = x/3;
                cout << "Выбрана 3-ая функция." << "\n"; break;
        default: cout << "Введите номер функции!" << "\n";
        return 0;
    }

    if (z<1) {
        x = pow(z,3) + 0.2;
        cout << "Вычисление x было выполнено через 1-ую ветвь." << "\n";
    } else if (z >= 1) {
        x = z + log(z);
        cout << "Вычисление x было выполнено через 2-ую ветвь." << "\n";
    }

    y = 2.0*a*pow(cos(x*x),3)+pow(sin(pow(x,3)),2)-b*f;
    cout << "y = " << y;

    return 0;
}