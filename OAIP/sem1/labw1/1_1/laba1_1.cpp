// varinat 4

#include <iostream>
#include <cmath>

using namespace std;

int main ()

{
    double beta,z1,z2, PI = 3.1415926;
    cout << "Введите beta (в радианах): ";
    cin >> beta;
    z1 = pow(cos((3*PI/8)-(beta/4)),2) - pow(cos((11*PI/8)+(beta/4)),2);
    z2 = sqrt(2)*sin(beta/2)/2;
    cout << "z1 = " << z1 << "\n";
    cout << "z2 = " << z2 << "\n";
    return 0;
}

