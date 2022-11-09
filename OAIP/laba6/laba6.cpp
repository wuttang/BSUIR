// variant 4

#include <iostream>  
#include <stdlib.h>

using namespace std;

int main()
{
    char a[1000];
    cout << "Введите строку, состоящую из групп 0 и 1: ";
    cin >> a;
    for (int i = 0; i<1000; i++){
        if (a[i] != '0' && a[i] != '1'){
            cout << "Строка должна состоять из 0 и 1!";
        }
    }
    return 0;
}