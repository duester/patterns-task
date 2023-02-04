## Структурные паттерны

Необходимо написать библиотеку по работе с аудиофайлами. 
При этом, чтобы не повторять то, что уже реализовано в других библиотеках, было принято решение написать новые методы/алгоритмы поверх существующих библиотек.
Однако, поскольку хочется охватить как можно больше форматов, первая версия была написана неоптимально - 
каждый новый метод продублирован три раза (для mp3, wav, midi) с учётом особенностей каждого формата.

Нужно оптимизировать и переписать библиотеку AudioLib, используя структурный паттерн, чтобы в будущем проще было добавлять поддержку новых форматов.