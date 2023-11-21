#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

typedef struct MemoryBlock {
    size_t size;
    struct MemoryBlock* next;
} MemoryBlock;

MemoryBlock* freeMemory = NULL;

void initializeMemoryManager(MemoryBlock* initialBlock) {
    initialBlock->size = 1024;
    initialBlock->next = NULL;

    freeMemory = initialBlock;
}

void* allocateMemory(size_t size) {
    MemoryBlock* currentBlock = freeMemory;
    MemoryBlock* prevBlock = NULL;

    while (currentBlock != NULL) {
        if (currentBlock->size >= size) {
            if (currentBlock->size > size + sizeof(MemoryBlock)) {
                MemoryBlock* newBlock = (MemoryBlock*)((char*)currentBlock + sizeof(MemoryBlock) + size);
                newBlock->size = currentBlock->size - size - sizeof(MemoryBlock);
                newBlock->next = currentBlock->next;
                currentBlock->size = size;
                currentBlock->next = newBlock;
            }

            if (prevBlock != NULL) {
                prevBlock->next = currentBlock->next;
            } else {
                freeMemory = currentBlock->next;
            }

            return (char*)currentBlock + sizeof(MemoryBlock);
        }

        prevBlock = currentBlock;
        currentBlock = currentBlock->next;
    }

    return NULL;
}

void freeMemoryBlock(void* ptr) {
    if (ptr == NULL) {
        return;
    }

    MemoryBlock* block = (MemoryBlock*)((char*)ptr - sizeof(MemoryBlock));

    if (block->size > 0) {
        block->next = freeMemory;
        freeMemory = block;
    }
}

void readMemory(void* ptr, size_t size) {
    if (ptr == NULL) {
        return;
    }

    printf("Reading %zu bytes from memory at address %p: ", size, ptr);
    for (size_t i = 0; i < size; ++i) {
        printf("%02X ", *((unsigned char*)ptr + i));
    }
    printf("\n");
}

void writeMemory(void* ptr, size_t size) {
    if (ptr == NULL) {
        return;
    }

    printf("Writing %zu bytes to memory at address %p: ", size, ptr);
    for (size_t i = 0; i < size; ++i) {
        unsigned char randomByte = rand() % 256;  // Генерация случайного байта
        *((unsigned char*)ptr + i) = randomByte;
        printf("%02X ", randomByte);
    }
    printf("\n");
}

void runUnitTests() {
    // Тесты для allocateMemory и freeMemoryBlock
    void* ptr1 = allocateMemory(100);
    assert(ptr1 != NULL);

    void* ptr2 = allocateMemory(200);
    assert(ptr2 != NULL);

    freeMemoryBlock(ptr1);
    freeMemoryBlock(ptr2);

    // Тесты для readMemory и writeMemory
    void* ptr3 = allocateMemory(50);
    assert(ptr3 != NULL);

    writeMemory(ptr3, 50);

    // Чтение и проверка записанных данных
    printf("Expected: ");
    for (size_t i = 0; i < 50; ++i) {
        printf("%02X ", i);
    }
    printf("\n");

    printf("Actual:   ");
    readMemory(ptr3, 50);

    // Освобождение памяти
    printf("Before freeMemoryBlock, ptr3 points to: %p\n", ptr3);
    freeMemoryBlock(ptr3);
    ptr3 = NULL;
}


int main() {
    MemoryBlock* initialBlock = (MemoryBlock*)malloc(1024);
    if (initialBlock == NULL) {
        perror("Memory allocation failed");
        exit(EXIT_FAILURE);
    }

    initializeMemoryManager(initialBlock);

    runUnitTests();

    free(initialBlock);

    return 0;
}
