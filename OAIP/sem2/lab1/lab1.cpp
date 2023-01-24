#include <iostream>
using namespace std;

unsigned int fibonacci_numbers(unsigned int);
unsigned int num_check(); 

int main()
{
    unsigned int n = 0;
    int choice = 0;
    int fib_num = 0;
    int i = 2, i1 = 1, i2 = 1;
    cout << "Введите число: ";
    n = num_check();
    cout << "Выберите, каким образом хотите выполнить программу: 1 - рекурсивным, 2 - циклическим: ";
    cin >> choice;
    switch(choice)
    {
        case 1: {
            cout << "Fb(" << n << ") = " << fibonacci_numbers(n);
            } break;
        case 2: {
            while (i < n) {
                fib_num = i1 + i2;
                i1 = i2;
                i2 = fib_num;
                i++;
            }
            cout << "Fb(" << n << ") = " << fib_num;
        } break;
        default: cout << "Некорректный ввод";
    }
    return 0;

}

unsigned int fibonacci_numbers(unsigned int n) {
    return n < 2 ? n : fibonacci_numbers(n - 2) + fibonacci_numbers(n - 1);
    /* if (n < 2) return n;
      else return fibonacci_numbers(n - 2) + fibonacci_numbers(n - 1); */
}

unsigned int num_check()
{   
    unsigned int n;
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
