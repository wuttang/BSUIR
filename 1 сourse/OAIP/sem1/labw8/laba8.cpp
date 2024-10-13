// variant 4

#include <cmath>  
#include <iostream>  

using namespace std;

void func_cout(double sum, double y, double mod){
        cout << "------------------------------------------------------------------------------\n";  
        cout << "S(x) = " << sum << "\n" << "Y(x) = " << y << "\n" << "|Y(x)-S(x)| = " << mod << "\n";
}

int main()  
{ 
    double a, b, h, n, x, y,rec,sum,k,mod,z;  
    cout << "Введите a,b,h,n: ";  
    cin >> a >> b >> h >> n;  

    if (!cin || cin.get() != '\n') {
        cout << "Введите числовое значение.";
        return 0;
    }
     
    for (x = a; x <= b; x+=h)  
    {  
        sum = 1; rec = 1;
        z = cos(x);
        for (k = 1;k <= n;k++)  
        {  
            rec *= -x * x /( 2 * k*(2 * k - 1));  
            sum += rec;  
        }  
        mod = abs(z-sum);
        func_cout(sum,y,mod);
    }

  return 0;
}