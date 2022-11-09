#include <iostream>
#include <vector>

using namespace std;

int main ()
{
    int n,m,j,i,oper,repeat;

    vector<int> a; // Вводим первое множество
    vector<int> b; // Вводим второе множество
    vector<int> c; // Итоговое множество

    // Пользователь вводит мощность множества А
    cout << "Введите мощность множества А (0 <= |A| <= 20): ";
    cin >> n;

    // Пользователь вводит элементы множества A
    cout << "Введите элементы множества А через пробел (В промежутке [0;50]): ";
    for (i = 0; i<n; i++){
        int current_el;
        cin >> current_el;
        a.push_back(current_el);
    }

    // Пользователь вводит мощность множества B
    cout << "Введите мощность множества B (0 <= |B| <= 20): ";
    cin >> m;

    // Пользователь вводит элементы множества B
    cout << "Введите элементы множества B через пробел (В промежутке [0;50]): ";
    for (i = 0; i<m; i++){
        int current_el;
        cin >> current_el;
        b.push_back(current_el);
    }

operation:
    c.clear();
    // Пользователь выбирает операцию, которую он хочет выполнить
    cout << "Выберите операцию, которую вы хотите выполнить над множествами. 1 - Объединение, 2 - Пересечение: ";
    cin >> oper;
    switch(oper)
    {
        case 1: { // Операция объеденения 
            // Записываем все элементы  множества А в множество С
            for (int i = 0; i < n; i++) {
                c.push_back(a[i]);
            }
            for (i = 0; i < m; i++) { // Проходимся по элементам множества B
                for (j = 0; j < c.size(); j++) { // Проходимся по элементам множества С
                    if (b[i] != c[j]) { // Если элемент множества В не равен элементу множества С, то записываем его в множество С
                        if (j + 1 == c.size()) { // Проверяем, является ли этот элемент последним
                            c.push_back(b[i]);
                        }
                    }
                    else { // Если элемент является последним, то заканчиваем проверку элементов
                        break;
                    }
                }
            }

            // Выводим итоговое множество
            cout << "C = { ";
            for (i = 0; i<c.size(); i++){
                cout << c[i]<< " ";
            }
            cout << "}\n"; 
            break;
        }
        case 2: {
            // Операция пересечения
            for (i = 0; i<n; i++){ // Проходимся по каждому элементу множества А
                for (j = 0; j<m; j++){ // Проходимся по каждому элементу множества B
                    if (a[i] == b[j]){ // Если элемент множества А равен элементу множества B, то мы записываем его в итоговое множество С
                    
                        c.push_back(a[i]);
                    }
                }
            } 
            
            // Выводим итоговое множество
            cout << "C = { ";
            for (i = 0; i<c.size(); i++){
                cout << c[i]<< " ";
            }
            cout << "}\n"; 
            break;
        }
        default: cout << "Выберите операцию"; return 0;
    }

    // Спрашивем у пользователя, хочет ли он выполнить еще операции, либо же он хочет завершить программу
    cout << "Вы хотите выполнить еще одну операцию(1) или завершить программу(2)?: ";
    cin >> repeat;
    switch(repeat)
    {
        // В случае, если пользователь хочет выполнить еще операции, то переходим к выбору операций
        case 1: goto operation; // line 38
        // Если пользователь решил завершить программу, то программа завершается
        case 2: return 0;
        default: cout << "Выберите действие.";
    }
}