// variant 4

#include <iostream>
#include <cmath>

using namespace std;

double max(double x,double y)
{
    if(x > y)
    {
        return x;
    }
    else   
    {
        return y;
    }
}

double min(double x,double y)
{
    if(x < y)
    {
        return x;
    }
    else   
    {
        return y;
    }
}

int main()
{ 
    double x,y,z,m;
    cout << "Введите x,y,z: ";
    cin >> x >> y >> z;

    if (!cin  || cin.get() != '\n') {
        cout << "Введите числовое значение.";
        return 0;
    }

    if (max(y,z) != 0) {
        m = min(max(x,y),max(y,z))/max(y,z);
        cout << "m = " << m;
        return 0;
    } else {
        cout << "На 0 делить нельзя!";
    }
    return 0;
}