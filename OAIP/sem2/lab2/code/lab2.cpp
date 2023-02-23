#include <iostream>
#include <fstream>
#include <string>
#include <sstream>
using namespace std;

double num_check()
{
    double n;
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

struct bus_station
{
    int bus_number;
    string arrival_point;
    double departure_time;
    double arrival_time;
};

void quicksort(bus_station bus_arr[], int left, int right) {
    int i = left, j = right;
    int pivot = bus_arr[(left + right) / 2].arrival_time;

    while (i <= j) {
        while (bus_arr[i].arrival_time < pivot) {
            i++;
        }
        while (bus_arr[j].arrival_time > pivot) {
            j--;
        }
        if (i <= j) {
            swap(bus_arr[i].arrival_point, bus_arr[j].arrival_point);
            swap(bus_arr[i].bus_number, bus_arr[j].bus_number);
            swap(bus_arr[i].departure_time, bus_arr[j].departure_time);
            swap(bus_arr[i].arrival_time, bus_arr[j].arrival_time);
            i++;
            j--;
        }
    }

    if (left < j) {
        quicksort(bus_arr, left, j);
    }
    if (i < right) {
        quicksort(bus_arr, i, right);
    }
}

int main()
{
    int y = 0, j = 0;
    string strf,choise_1;
    double choise_2 = 0.0;
    bool arrival_point_error = false;
    bool arrival_time_error = false;
    string stra;
    ifstream in ("/Users/wuttang/BSUIR/OAIP/sem2/lab2/txt/input.txt");
    ofstream out ("/Users/wuttang/BSUIR/OAIP/sem2/lab2/txt/output.txt");
    getline(in, stra, '\0');
    in.seekg(0);
    while (getline(in, strf))
    {
        y++;
    }
    stringstream ff;
    ff.str(stra);
    bus_station* bus_arr = new bus_station[y];
    for (int i = 0; i < y; i++) {
        ff >> bus_arr[i].bus_number >> bus_arr[i].arrival_point >> bus_arr[i].departure_time >> bus_arr[i].arrival_time;
    }
    
    quicksort(bus_arr, 0, y-1);
    for (int i = 0; i < y; i++) {
        out << bus_arr[i].bus_number << " " << bus_arr[i].arrival_point << " " << bus_arr[i].departure_time << " "<< bus_arr[i].arrival_time << "\n";
    }

    cout << "-----------------------------" << endl;
    for (int i = 0; i < y; i++)
    {
        cout << "\t\x1b[36m" << bus_arr[i].arrival_point << endl;
    }
    cout << "\x1b[37m-----------------------------" << endl;
    cout << endl;
    cout << "Ваберите пункт назначения: ";
    do
    {
        cin >> choise_1;
        cout << endl;
        for (int i = 0; i < y; i++)
        {
            if (bus_arr[i].arrival_point.compare(choise_1))
            {
                j++;
                if (j == y)
                {
                    cout << "\x1b[31m" << "Отсутствует маршрут в данный пункт назначения. Попробуйте еще раз: " << "\x1b[37m";
                }
            }
            else
            {
                arrival_point_error = true;
                break;
            }
        }
    } while (arrival_point_error == false);
    
    cout << "Введите время прибытия(через точку): ";
    do {
        choise_2 = num_check();
        cout << endl;
        if ((choise_2 < 0) || (choise_2 > 23.6) || (((int)(100*choise_2)%100) > 60))
        {
            cout << "\x1b[31m" << "Время введено неверно. Попробуйте еще раз: " << "\x1b[37m";
        }
        else arrival_time_error = true;
    } while (arrival_time_error == false);
    for (int i = 0; i < y; i++)
    {
        if (bus_arr[i].arrival_point.compare(choise_1)) continue;
        else
        {
            if (bus_arr[i].arrival_time <= choise_2)
            {
                cout << "Номер маршрута: " << bus_arr[i].bus_number << endl << "Время прибытия: " << bus_arr[i].arrival_time << endl;
                cout << endl;
            }
            else
            {
                cout << "В ближайшее время автобус не прибывает." << endl;
                break;
            }
        }
    }

    return 0;
}