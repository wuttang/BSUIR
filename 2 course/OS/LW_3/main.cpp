#include <iostream>
#include <fstream>
#include <vector>
#include <pthread.h>
#include <unistd.h>
#include <ctime>
#include <cstdlib>

// Глобальный буфер для хранения случайных чисел
std::vector<int> buffer;
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER; // Мьютекс для доступа к буферу
bool stopFlag = false;

// Функция, которую выполняет процесс p1
void* p1_function(void* arg) {
    while (!stopFlag) {
        pthread_mutex_lock(&mutex);
        if (!buffer.empty()) {
            int num = buffer.back();
            buffer.pop_back();
            pthread_mutex_unlock(&mutex);
            std::cout << "p1: " << num << std::endl;
        } else {
            pthread_mutex_unlock(&mutex);
            usleep(100); // Подождем немного, если буфер пуст
        }
    }
    return nullptr;
}

// Функция, которую выполняет процесс p2
void* p2_function(void* arg) {
    std::ofstream outputFile("output.txt");
    if (!outputFile.is_open()) {
        std::cerr << "Ошибка открытия файла." << std::endl;
        return nullptr;
    }

    while (!stopFlag) {
        pthread_mutex_lock(&mutex);
        if (!buffer.empty()) {
            int num = buffer.back();
            buffer.pop_back();
            pthread_mutex_unlock(&mutex);
            outputFile << "p2: " << num << std::endl;
        } else {
            pthread_mutex_unlock(&mutex);
            usleep(100); // Подождем немного, если буфер пуст
        }
    }

    outputFile.close();
    return nullptr;
}

int main() {
    srand(time(nullptr));

    pthread_t p1_thread, p2_thread;

    // Создание потоков p1 и p2
    pthread_create(&p1_thread, nullptr, p1_function, nullptr);
    pthread_create(&p2_thread, nullptr, p2_function, nullptr);

    // Генерация случайных чисел и добавление их в буфер
    for (int i = 0; i < 10; ++i) {
        pthread_mutex_lock(&mutex);
        buffer.push_back(rand() % 100);
        pthread_mutex_unlock(&mutex);
        usleep(500); // Подождем немного перед добавлением следующего числа
    }

    // Установка флага завершения и ожидание завершения потоков
    stopFlag = true;
    pthread_join(p1_thread, nullptr);
    pthread_join(p2_thread, nullptr);

    return 0;
}
