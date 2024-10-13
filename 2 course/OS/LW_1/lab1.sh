#!/bin/bash

# Проверяем, что переданы три аргумента командной строки
if [ $# -ne 3 ]; then
    echo "Использование: $0 <min_size> <max_size> <directory>"
    exit 1
fi

# Получаем минимальный размер файла из первого аргумента
min_size="$1"

# Получаем максимальный размер файла из второго аргумента
max_size="$2"

# Получаем каталог из третьего аргумента
directory="$3"

# Проверяем, что каталог существует
if [ ! -d "$directory" ]; then
    echo "Каталог '$directory' не существует."
    exit 1
fi

# Ищем файлы в заданном каталоге с размером между min_size и max_size (включительно)
found_files=$(find "$directory" -type f -size +"$min_size"c -size -"$max_size"c)

# Проверяем, есть ли найденные файлы
if [ -z "$found_files" ]; then
    echo "Файлы заданного размера не найдены в каталоге '$directory'."
else
    echo "Список файлов заданного размера в каталоге '$directory':"
    echo "$found_files"
fi

#/Users/wuttang/BSUIR/OS/lab1/lab1.sh 0 5000 /Users/wuttang/BSUIR/OS/lab1/files/