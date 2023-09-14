#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <pthread.h>

#define PI 3.14159265358979323846

// Структура для передачи данных потокам
struct ThreadData {
    int thread_id;
    int K;
    int N;
    int n;
    double result;
};

// Функция для вычисления факториала
unsigned long long factorial(int n) {
    if (n <= 0) return 1;
    unsigned long long fact = 1;
    for (int i = 1; i <= n; i++) {
        fact *= i;
    }
    return fact;
}

// Функция, которую выполняет каждый поток
void *computeTerm(void *arg) {
    struct ThreadData *data = (struct ThreadData *)arg;
    int i = data->thread_id;
    int n = data->n;
    double x = 2.0 * PI * i / data->N;
    data->result = pow(-1, i) * pow(x, 2 * i + 1) / factorial(2 * i + 1);

    printf("Thread %d (TID %ld): Term %d = %lf\n", data->thread_id, (long)pthread_self(), i, data->result);

    pthread_exit(NULL);
}

int main() {
    int K, N, n;
    printf("Введите значение K: ");
    scanf("%d", &K);
    printf("Введите значение N: ");
    scanf("%d", &N);
    printf("Введите количество членов ряда Тейлора (n): ");
    scanf("%d", &n);

    pthread_t threads[K];
    struct ThreadData thread_data[K];

    FILE *output_file = fopen("output.txt", "w");
    if (output_file == NULL) {
        perror("Ошибка открытия файла для записи");
        exit(1);
    }

    for (int i = 0; i < K; i++) {
        thread_data[i].thread_id = i;
        thread_data[i].K = K;
        thread_data[i].N = N;
        thread_data[i].n = n;

        // Создаем поток
        if (pthread_create(&threads[i], NULL, computeTerm, &thread_data[i]) != 0) {
            perror("Ошибка создания потока");
            exit(1);
        }
    }

    double result = 0.0;
    for (int i = 0; i < K; i++) {
        // Ждем завершения каждого потока
        pthread_join(threads[i], NULL);
        // Суммируем результаты
        result += thread_data[i].result;
    }

    // Записываем результат в файл
    fprintf(output_file, "Результат: %lf\n", result);
    fclose(output_file);

    printf("Результат записан в файл 'output.txt'\n");

    return 0;
}