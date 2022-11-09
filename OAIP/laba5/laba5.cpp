// variant 4

#include <iostream>  
#include <cstdlib>
#include <ctime>
#include <iomanip>

using namespace std;

double num_check()
{   
    int n;
    do {
        cin >> n;
        if (!cin || ( cin.get() != ' ' && cin.get() != '\n') ) {
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
    int n,m,elem,sum,special_elements = 0;
    cout << "Введите размер строки: ";
    n = num_check();
    cout << "Введите размер столбца: ";
    m = num_check();

    int **a = new int*[n];
    for (int count = 0; count < n; count++)
        a[count] = new int[m];

    cout << "Вы хотите самостоятельно задать элементы массива или сделать это рандомно? (1 - cам, 2 - ранд): ";
    elem = num_check();

    switch (elem)
    {
        case 1: for (int i = 0; i<n; i++)
                    for (int j = 0; j<m; j++) {
                        cout << "Введите элемент a[" << i << "]" << "[" << j << "]" << ": ";
                        a[i][j] = num_check();
                    } break;
        case 2: srand(time(0));
            for(int i = 0; i<n; ++i){
                for (int j = 0; j<m; j++)
                a[i][j] = rand() % 50 -25 / int(rand() % 50 -25); 
            } break;
        default: {
                    cout << "Недопустимое значение"; return 0;
                }
    }
    
    cout << "Массив: " << endl;
    for(int i = 0; i<n; i++){
        for (int j = 0; j<m; j++)
        cout << setw(5) << a[i][j] << " ";
        cout << endl;
    } 

    for (int i = 0; i<n; i++){   
        sum = 0;
        for (int j = 0; j<m; j++){
            sum += a[j][i];
        }
        
        int count = 0;

        for (int j = 0; j<m; j++){
            if (a[j][i] > sum - a[i][j]) count++;
        }
        special_elements += count;

        cout << "Количество особых элементов в " << i+1 << " столбце = " << count << endl;
    }
    cout << "------------------------------------" << endl;
    cout << "Общее количество особых элементов: " << special_elements << endl; 

    delete []a;
    return 0;
}