import exception


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

