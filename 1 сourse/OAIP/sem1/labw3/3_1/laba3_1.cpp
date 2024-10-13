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
    double x,y,h,a,b,min_y, max_y;
    cout << "Введите a,b,h: ";
    cin >> a >> b >> h;

    y = (1.0-a*a/4)*cos(a) -a*sin(a)/2;
    min_y = min(y,min_y);
    max_y = max(y,max_y);

    if (!cin  || cin.get() != '\n') {
        cout << "Введите числовое значение.";
        return 0;
    }

    for (int i = 0; a<=b; a+=h) {
        y = (1.0-a*a/4)*cos(a) -a*sin(a)/2;
        min_y = min(y,min_y);
        max_y = max(y, max_y);
        i++;
        cout << "\t" << i << "\t x = " << a << "\t y= " << y << "\n";
        cout << "----------------------------------------------------" << "\n";
        cout << "Минимальное значение y = " << min_y << "\n";
        cout << "Максимальное значение y = " << max_y << "\n";
        cout << "----------------------------------------------------" << "\n\n\n";
    }
    return 0;
}