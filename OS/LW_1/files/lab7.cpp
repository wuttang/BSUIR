#include <iostream>
#include <string>
#include <list>

using namespace std;

struct Contact {
    string surname;
    string phoneNumber;
    string address;
};

int hashFunction(string key, int tableSize) {
    int sum = 0;
    for (char c : key) {
        sum += c;
    }
    return sum % tableSize;
}

void insert(list<Contact>* table, int tableSize, Contact contact) {
    int index = hashFunction(contact.phoneNumber, tableSize);
    table[index].push_back(contact);
}

Contact* search(list<Contact>* table, int tableSize, string phoneNumber) {
    int index = hashFunction(phoneNumber, tableSize);
    for (auto& contact : table[index]) {
        if (contact.phoneNumber == phoneNumber) {
            return &contact;
        }
    }
    return nullptr;
}

void printTable(list<Contact>* table, int tableSize) {
    for (int i = 0; i < tableSize; i++) {
        cout << "Хеш " << i << ": ";
        for (auto& contact : table[i]) {
            cout << contact.phoneNumber << " ";
        }
        cout << endl;
    }
}

int main() {
    const int TABLE_SIZE = 10;
    list<Contact> table[TABLE_SIZE];

    Contact contacts[9];
    cout << "Введите данные для 9 контактов:" << endl;
    for (int i = 0; i < 9; i++) {
        cout << "Контакт " << i+1 << endl;
        cout << "Фамилия: ";
        cin >> contacts[i].surname;
        cout << "Номер телефона: ";
        cin >> contacts[i].phoneNumber;
        cout << "Адрес: ";
        cin >> contacts[i].address;
        cout << endl;
    }

    for (int i = 0; i < 9; i++) {
        insert(table, TABLE_SIZE, contacts[i]);
    }

    cout << "Исходный массив структур:" << endl;
    for (int i = 0; i < 9; i++) {
        cout << "Контакт " << i+1 << endl;
        cout << "Фамилия: " << contacts[i].surname << endl;
        cout << "Номер телефона: " << contacts[i].phoneNumber << endl;
        cout << "Адрес: " << contacts[i].address << endl;
        cout << endl;
    }

    cout << "Хеш-таблица:" << endl;
    printTable(table, TABLE_SIZE);
    cout << endl;

    string searchPhoneNumber;
    cout << "Введите номер телефона для поиска: ";
    cin >> searchPhoneNumber;

    Contact* foundContact = search(table, TABLE_SIZE, searchPhoneNumber);

    if (foundContact != nullptr) {
        cout << "Найденный контакт:" << endl;
        cout << "Фамилия: " << foundContact->surname << endl;
        cout << "Номер телефона: " << foundContact->phoneNumber << endl;
        cout << "Адрес: " << foundContact->address << endl;
    } else {
        cout << "Контакт с таким номером телефона не найден." << endl;
    }

    return 0;
}
