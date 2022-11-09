// variant 4

#include <iostream>
#include <cstdlib>
#include <ctime>

using namespace std;

double num_check()
{   
    int n;
    do {
        cin >> n;
        if (!cin || cin.get() != '\n') {
            cin.clear();
            cin.ignore((numeric_limits<streamsize>::max)(), '\n');
            cout << "Повторите попытку: ";
        } else {
            return n;
        }
    } while (true);
}

int main()
{
    int n,elem;
    int *a = new int[n];
    cout << "Введите длину массива(<=20): ";
    n = num_check();

    if (n>20){
        cout << "Размер > 20.";
        return 0;
    }

    cout << "Вы хотите самостоятельно задать элементы массива или сделать это рандомно? (1 - cам, 2 - ранд): ";
    elem = num_check();

    switch (elem)
    {
        case 1: { for (int i = 0; i<n; i++){
                        cout << "Введите элемент массива: ";
                        a[i] = num_check();
                    } break;
        } 
    
        case 2: { srand(time(0));
            for(int i = 0; i<n; ++i){
            a[i] = rand() % 100 -50; 
            } break;
        }
        default: { cout << "Недопустимое значение"; return 0; }
    }

    cout << "Изначальный массив: ";
    for(int i = 0; i<n; i++){
        cout << a[i] << " ";
    } 

    for(int i = 0; i<n; i++){
        if (a[i]<0) 
        {
            for (int j = i+1; j<n; j++) a[j-1] = a[j];
            n--;
            i--;
        }
    }

    cout << "\nОбновленный массив: ";
    for(int i = 0; i<n; i++){
        cout << a[i] << " ";
    } 

    delete []a;
    return 0;
}