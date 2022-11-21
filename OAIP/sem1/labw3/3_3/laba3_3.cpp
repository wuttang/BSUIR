// variant 4

#include <cmath>  
#include <iostream>  

using namespace std;

double a, b, h, n, y,rec,sum,k,mod,eps;  

double y_x(double x){
    return cos(x);
}

double long s(double a, int n) 
{ 
    sum = 1; 
    rec = 1; 
    for (int k = 1; k <= n; k++) 
    { 
        rec *= -1 * a * a / ((2 * k - 1) * (2 * k)); 
        sum += rec; 
    } 
    return sum; 
}

int main()  
{    
    cout << "Введите a,b,h,epsilon: ";  
    cin >> a >> b >> h >> eps;  

    if (!cin  || cin.get() != '\n') {
        cout << "Введите числовое значение.";
        return 0;
    }

    for (; a <= b; a += h, n = 0) { 
        do { 
            mod = abs(s(a, n) - y_x(a)); 
            n++; 
            cout << "x = " << a << "\nY(x) = " << y << "\nS(x) = " <<  sum <<  "\ne = |Y(x)-S(x)| = " << mod << "\n"; 
            cout << "--------------------------------------------------------------------------------\n"; 
        } while (mod >= eps); 
        cout << " Искомое число n = " << n << "\n\n\n"; 
    } 

  return 0;
}