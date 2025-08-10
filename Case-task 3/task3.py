import random
import time
import threading

def input_watcher(stop_event):
    # Ждем, пока пользователь введет 0 и нажмет Enter
    while not stop_event.is_set():
        s = input()
        if s.strip() == "0":
            stop_event.set()

def main():
    numbers = []
    stop_event = threading.Event()

    print("Генерация началась. Введите 0 и нажмите Enter, чтобы остановить.")

    # Поток для отслеживания ввода пользователя
    watcher = threading.Thread(target=input_watcher, args=(stop_event,), daemon=True)
    watcher.start()

    try:
        while not stop_event.is_set():
            x = random.randint(1, 100)
            numbers.append(x)            
            time.sleep(0.5)  # задержка между числами
    finally:
        # Исключаем последнее число
        if numbers:
            numbers = numbers[:-1]

        print("Результат:")
        print(numbers)

if __name__ == "__main__":
    main()