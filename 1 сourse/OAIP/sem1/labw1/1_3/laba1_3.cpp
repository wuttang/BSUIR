// varinat 4

#include <iostream>
#include <cmath>

using namespace std;

int main()
{
    cout.precision(5);
    double w,x,y,z;
    cout << "Введите x, y, z: ";
    cin >> x >> y >> z;

    if (!cin) {
        cin.clear();
        cin.ignore();
        cout << "Введите числовое значение.";
        return 0;
    }

    w = (pow(fabs(cos(x)-cos(y)), (1.0+2*pow(sin(y), 2))))*(1.0+z+(pow(z,2)/2)+(pow(z,3))/3+(pow(z,4)/4));
    cout << "w = " << w;
    return 0;
}