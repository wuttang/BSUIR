#include <iostream>
#include <fstream>

using namespace std;

int EncryptText(char[], char[]);
int DecryptText(char[], char[]);
int GetChoose();

int main()
{
    int choose;

    cout << "1. Зашифровать текст и записать его в файл output.txt" << endl;
    cout << "2. Расшифровать текст из файла input.txt" << endl;
    choose = GetChoose();

    switch (choose) {
    case 1: {
        ofstream fout;
        fout.open("C:\\Users\\wuttang\\source\\repos\\lab77\\output.txt");
        char encrypt_string[400];
        char str[200];
        cout << "Введите строку, которую хотите зашифровать: ";
        cin.getline(str, 200);
        int encrypt_string_len = EncryptText(str, encrypt_string);

        for (int i = 0; i < encrypt_string_len; i++) {
            fout << encrypt_string[i];
        }
        fout.close();
        break;
    }
    case 2: {
        ifstream fin;
        fin.open("C:\\Users\\wuttang\\source\\repos\\lab77\\input.txt");
        char decrypt_string[200];
        char str[400];
        fin.getline(str, 400);
        int decrypt_string_len = DecryptText(str, decrypt_string);
        cout << "Расшифрованная строка: ";
        for (int i = 0; i < decrypt_string_len; i++) {
            cout << decrypt_string[i];
        }
        cout << endl;
        fin.close();
        break;
    }
    }

    cout << "SUCCESS";

    return 0;
}

int GetChoose() {
    int n;

    do {
        cout << "Введите ваш выбор: ";
        cin >> n;
        if (cin.fail() || cin.get() != '\n') {
            cout << "Неверный формат ввода выбора, попробуйте снова." << endl;
            cin.clear();
            cin.ignore((numeric_limits<streamsize>::max)(), '\n');
        }
        else {
            if (n < 1 || n > 2) {
                cout << "Число не попадает в диапозон, попробуйте снова." << endl;
                continue;
            }
            return n;
        }
    } while (true);
}

int EncryptText(char string[], char encrypt_string[]) {
    int current_element = 0;

    for (int i = 0; i < strlen(string); i++) {
        encrypt_string[current_element] = string[i];
        current_element++;
        if (tolower(string[i]) == unsigned char('а') || tolower(string[i]) == unsigned char('е') ||
            tolower(string[i]) == unsigned char('и') || tolower(string[i]) == unsigned char('о') ||
            tolower(string[i]) == unsigned char('у') || string[i] == 'я' || string[i] == 'Я' ||
            tolower(string[i]) == unsigned char('ы') || tolower(string[i]) == unsigned char('ё') ||
            tolower(string[i]) == unsigned char('ю') || tolower(string[i]) == unsigned char('я') ||
            tolower(string[i]) == unsigned char('э') || tolower(string[i]) == unsigned char('a') ||
            tolower(string[i]) == unsigned char('e') || tolower(string[i]) == unsigned char('i') ||
            tolower(string[i]) == unsigned char('o') || tolower(string[i]) == unsigned char('u') ||
            tolower(string[i]) == unsigned char('y')) {
            encrypt_string[current_element] = 'л';
            encrypt_string[current_element + 1] = 'а';
            current_element += 2;
        }
    }

    return current_element;
}

int DecryptText(char string[], char decrypt_string[]) {
    int current_element = 0;

    for (int i = 0; i < strlen(string); i++) {
        decrypt_string[current_element] = string[i];
        current_element++;
        if (tolower(string[i]) == unsigned char('а') || tolower(string[i]) == unsigned char('е') ||
            tolower(string[i]) == unsigned char('и') || tolower(string[i]) == unsigned char('о') ||
            tolower(string[i]) == unsigned char('у') || string[i] == 'я' || string[i] == 'Я' ||
            tolower(string[i]) == unsigned char('ы') || tolower(string[i]) == unsigned char('ё') ||
            tolower(string[i]) == unsigned char('ю') || tolower(string[i]) == unsigned char('я') ||
            tolower(string[i]) == unsigned char('э') || tolower(string[i]) == unsigned char('a') ||
            tolower(string[i]) == unsigned char('e') || tolower(string[i]) == unsigned char('i') ||
            tolower(string[i]) == unsigned char('o') || tolower(string[i]) == unsigned char('u') ||
            tolower(string[i]) == unsigned char('y')) {
            i += 2;
        }
    }

    return current_element;
}