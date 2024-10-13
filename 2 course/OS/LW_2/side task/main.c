#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <time.h>

int main() {
    time_t now;
    time(&now);


    pid_t parent_pid = getpid();
    pid_t child1_pid, child2_pid;

    child1_pid = fork(); // Создаем первый дочерний процесс

    if (child1_pid < 0) {
        perror("Ошибка при создании первого дочернего процесса");
        exit(1);
    } else if (child1_pid == 0) {
        printf("Первый дочерний процесс - PID: %d, PPID: %d\n", getpid(), getppid());

        printf("Текущее время: %s", ctime(&now));
        exit(0);
    } else {
        child2_pid = fork(); // Создаем второй дочерний процесс

        if (child2_pid < 0) {
            perror("Ошибка при создании второго дочернего процесса");
            exit(1);
        } else if (child2_pid == 0) {
            printf("Второй дочерний процесс - PID: %d, PPID: %d\n", getpid(), getppid());
            printf("Текущее время: %s", ctime(&now));
            exit(0);
        } else {
            printf("Родительский процесс - PID: %d\n", parent_pid);
            printf("Дочерний процесс 1 - PID: %d\n", child1_pid);
            printf("Дочерний процесс 2 - PID: %d\n", child2_pid);

            system("ps -x");

            // Ожидаем завершения обоих дочерних процессов
            wait(NULL);
            wait(NULL);

            printf("Родительский процесс завершен.\n");
            exit(0);
        }
    }
}
