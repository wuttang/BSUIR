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
    cout << "Введите число: " << "\x1b[32m ";
    n = num_check();
    cout << "\x1b[0m" << "Выберите, каким образом хотите выполнить программу: 1 - рекурсивным, 2 - циклическим: ";
    cin >> choice;
    switch(choice)
    {
        case 1: {
            cout << "Fb(" << "\x1b[32m" << n << "\x1b[0m" << ") = " << "\x1b[36m" << fibonacci_numbers(n) << "\x1b[0m" <<endl;
            } break;
        case 2: {
            while (i < n) {
                fib_num = i1 + i2;
                i1 = i2;
                i2 = fib_num;
                i++;
            }
            cout << "Fb(" << "\x1b[32m" << n << "\x1b[0m" << ") = " << "\x1b[36m" << fib_num << "\x1b[0m" << endl;
        } break;
        default: cout << "\x1b[31m" << "Некорректный ввод" << "\x1b[30m" << endl;
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
        cout << "\x1b[32m";
        cin >> n;
        cout << "\x1b[0m";
        if (!cin || cin.get() != '\n') {
            cin.clear();
            cin.ignore((numeric_limits<streamsize>::max)(), '\n');
            cout << "\x1b[31m" << "Повторите попытку: " << "\x1b[0m";
        } else {
            return n;
        }
    } while (true);
}
