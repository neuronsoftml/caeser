# caeser
Цезар реліз софта 1.0 написаний за допомогою мови програмування Java.
Функції: шифрує текст, розшифровує текст, Brut-fors атака на шифр Цезаря. Підтримую Українську та Англійську мову.
Програма запускається в двох режимах  CONSOLE or (GUI покищо не доступний).
Для запуску необхідно вказати параметри до прикладу: 
-c ENCRYPT -f C:\Users\Ira\Desktop\File.txt -k 120 -m CONSOLE
-c DECRYPT -f C:\Users\Ira\Desktop\[ENCRYPTED].txt -k 60 -m CONSOLE
-c BRUTE_FORCE -f C:\Users\Ira\Desktop\[ENCRYPTED].txt -m CONSOLE
Ключ -с соmmand   в доступі  ENCRYPT  DECRYPT  BRUTE_FORCE.

Ключ -f filePatch шлях до файлу.
Ключ -k key кількість символів для зміщення .
Ключ -m MODE_OF_OPERATION_CONSOLE режим запуску  CONSOLE or GUI.
