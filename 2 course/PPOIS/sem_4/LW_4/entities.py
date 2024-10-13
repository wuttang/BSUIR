import os
import time
import exception


class Room:
    def __init__(self, width: int, length: int):
        self.isClean: bool = False
        self.width: int = width
        self.length: int = length
        self.square: int = self.width*self.length

        if self.width <= 0 or self.length <= 0:
            raise exception.InvalidResolutionValue("‼ Введены неверные размеры комнаты")


class Nozzle:
    def __init__(self):
        self.turbo_brush: bool = False
        self.slot_nozzle: bool = False
        self.round_nozzle: bool = False
        self.laminate_nozzle: bool = False
        self.corner_nozzle: bool = False


class Filter:
    def __init__(self):
        self.health: int = 5

    def usage_of_filter(self):
        self.health -= 1

    def renew_filter(self):
        self.health = 5


class Engine:
    def __init__(self):
        self.min_power: int = 0
        self.max_power: int = 0
        self.health: int = 100

    def set_power(self, min_power: int, max_power: int):
        if min_power < 0 or max_power < 0 or min_power > max_power:
            raise exception.InvalidPowerValue("‼️ Введены неверные значения мощности")
        self.min_power = min_power
        self.max_power = max_power

    def renew_health(self):
        self.health = 100

    def usage_of_engine(self):
        self.health -= 5


class DustContainer:
    def __init__(self):
        self.isFilled: bool = False


class ControlButtons:
    def __init__(self):
        self.start_b: bool = False
        self.using_power: int = 0

    def change_power(self, wishful_power: int):
        self.using_power = wishful_power


class VacuumCleaner:
    def __init__(self, color: str, firm: str):
        self.color: str = color
        self.firm: str = firm
        self.nozzle = Nozzle()
        self.dust_container = DustContainer()
        self.filter = Filter()
        self.engine = Engine()
        self.control_buttons = ControlButtons()

    def put_on_nozzle(self, picked_nozzle: str):
        if picked_nozzle == "Turbo Brush":
            self.nozzle.turbo_brush = True
        elif picked_nozzle == "Slot Nozzle":
            self.nozzle.slot_nozzle = True
        elif picked_nozzle == "Round Nozzle":
            self.nozzle.round_nozzle = True
        elif picked_nozzle == "Laminate Nozzle":
            self.nozzle.laminate_nozzle = True
        elif picked_nozzle == "Corner Nozzle":
            self.nozzle.corner_nozzle = True
        else:
            print("Введенная насадка отсутсвует")

    def change_nozzle(self, new_nozzle: str):
        self.nozzle.turbo_brush = False
        self.nozzle.slot_nozzle = False
        self.nozzle.round_nozzle = False
        self.nozzle.laminate_nozzle = False
        self.nozzle.corner_nozzle = False
        self.put_on_nozzle(new_nozzle)

    def show_nozzle(self):
        print("У вас надета насадка: ", end='')
        if self.nozzle.turbo_brush:
            print("Turbo Brush")
        elif self.nozzle.slot_nozzle:
            print("Slot Nozzle")
        elif self.nozzle.round_nozzle:
            print("RoundNozzle")
        elif self.nozzle.laminate_nozzle:
            print("Laminate Nozzle")
        elif self.nozzle.corner_nozzle:
            print("Corner Nozzle")
        else:
            print("-")

    def clean_dust_container(self):
        self.dust_container.isFilled = False

    def check_dust_container(self):
        if self.dust_container.isFilled:
            print("Контейнер для пыли засорён")
        else:
            print("Контейнер для пыли чист")

    def show_power(self):
        print(f"Диапазон мощности на пылесосе от {self.engine.min_power} до {self.engine.max_power} Вт.")
        print(f"Текущая мощность {self.control_buttons.using_power} Вт.")

    def clean_room(self, dust_room: Room):
        dust_room.isClean = True
        self.dust_container.isFilled = True
        self.filter.usage_of_filter()
        self.engine.usage_of_engine()
        time_impl = 0.05 * dust_room.square + 5 / self.control_buttons.using_power
        percent = 0
        for i in range(11):
            print(f"Выполняется уборка: {percent}% ", end='')
            print(i * "█")
            time.sleep(time_impl)
            percent += 10
            os.system("clear")
        print("Помещение успешно убрано!")

    def technical_work(self):
        self.engine.renew_health()
        self.filter.renew_filter()
        self.clean_dust_container()

    def start_vacuum(self, wishful_power: int):
        self.control_buttons.change_power(wishful_power)
        self.control_buttons.start_b = True

    def check_filter_health(self):
        print("До замены фильтра осталось чисток: " + str(self.filter.health))
