from entities import *
from server import WebServer
from flask import render_template, request, redirect, session, url_for
import pickle
import os


class CLIController:
    def __init__(self):
        pass

    @staticmethod
    def load_vacuum_cleaners():
        if os.path.isfile("resources/data.pickle"):
            with open("resources/data.pickle", "rb") as file:
                try:
                    vacuum_cleaners = pickle.load(file)
                    if not isinstance(vacuum_cleaners, list):
                        vacuum_cleaners = [vacuum_cleaners]
                except EOFError:
                    vacuum_cleaners = []
        else:
            vacuum_cleaners = []
        return vacuum_cleaners

    @staticmethod
    def save_vacuum_cleaners(vacuum_cleaners):
        with open("resources/data.pickle", "wb") as file:
            pickle.dump(vacuum_cleaners, file)

    @staticmethod
    def perform():
        vacuum_cleaners = CLIController.load_vacuum_cleaners()
        if not vacuum_cleaners:
            print("У вас отсутствует пылесос. Следует создать его.")
            choose_color = input("Введите цвет пылесоса: ")
            choose_firm = input("Введите фирму пылесоса: ")
            new_vacuum_cleaner = VacuumCleaner(choose_color, choose_firm)
            choose_min_power = int(input("Введите минимальную мощность(Вт): "))
            choose_max_power = int(input("Введите максимальную мощность(Вт): "))
            new_vacuum_cleaner.engine.set_power(choose_min_power, choose_max_power)
            vacuum_cleaners.append(new_vacuum_cleaner)
            CLIController.save_vacuum_cleaners(vacuum_cleaners)
        else:
            print("Список доступных пылесосов:")
            for idx, vc in enumerate(vacuum_cleaners):
                print(f"{idx + 1}. Фирма: {vc.firm}, Цвет: {vc.color}")

            vacuum_id = int(input("Введите номер пылесоса для использования: ")) - 1
            new_vacuum_cleaner = vacuum_cleaners[vacuum_id]

        while True:
            choose_action = input("\n🕹Управление пылесосом:\n\t1.Запустить пылесос\n\t2.Выбрать насадку"
                                  "\n\t3.Посмотреть засоренность фильтра"
                                  "\n\t4.Посмотреть засорен ли контейнер для пыли\n\t5.Посмотреть какая насадка надета"
                                  "\n\t6.Изменить текущую мощность\n\t7.Посмотреть установленную мощность\n\n🧹Уборка:"
                                  "\n\t8.Выполнить уборку помещения\n\n⚙️Обслуживание пылесоса:\n\t9.Заменить фильтр"
                                  "\n\t10.Очистить контейнер для пыли\n\t11.Заменить насадку"
                                  "\n\t12.Выполнить техническое обслуживание\n\n❌ 13.Выход\n\nВаш выбор: ")
            if choose_action == "1":
                if new_vacuum_cleaner.control_buttons.start_b:
                    print("Пылесос уже запущен.")
                    continue
                set_power = int(input("Введите текущую мощность(Вт): "))
                if set_power < new_vacuum_cleaner.engine.min_power or set_power > new_vacuum_cleaner.engine.max_power:
                    raise exception.InvalidPowerValue("‼️ Введены неверные значения")
                new_vacuum_cleaner.start_vacuum(set_power)
                CLIController.save_vacuum_cleaners(vacuum_cleaners)
            elif choose_action == "2":
                choose_nozzle = input("Выберите насадку(Turbo Brush, Slot Nozzle, Round Nozzle, Laminate Nozzle, "
                                      "Corner Nozzle).\nВведите аналогично предложенным: ")
                new_vacuum_cleaner.put_on_nozzle(choose_nozzle)
                CLIController.save_vacuum_cleaners(vacuum_cleaners)
            elif choose_action == "3":
                new_vacuum_cleaner.check_filter_health()
            elif choose_action == "4":
                new_vacuum_cleaner.check_dust_container()
            elif choose_action == "5":
                new_vacuum_cleaner.show_nozzle()
            elif choose_action == "6":
                wishful_power = int(input("Введите мощность, которую хотите установить: "))
                if wishful_power < new_vacuum_cleaner.engine.min_power or wishful_power > new_vacuum_cleaner.engine.max_power:
                    raise exception.InvalidPowerValue("‼️ Введены неверные значения")
                new_vacuum_cleaner.control_buttons.change_power(wishful_power)
                CLIController.save_vacuum_cleaners(vacuum_cleaners)
            elif choose_action == "7":
                new_vacuum_cleaner.show_power()
            elif choose_action == "8":
                choose_length = int(input("Введите размеры комнаты, которую хотите убрать\nДлина: "))
                choose_width = int(input("Ширина: "))
                if not new_vacuum_cleaner.control_buttons.start_b:
                    raise exception.InvalidButtonException("‼️Пылесос не запущен. Запустите его")
                elif new_vacuum_cleaner.dust_container.isFilled:
                    raise exception.InvalidButtonException("‼️ Контейнер для пыли заполнен. Очистите его")
                elif new_vacuum_cleaner.filter.health <= 0:
                    raise exception.InvalidFilterHealthValue("‼️ Фильтр засорен. Сначала очистите его.")
                elif not (new_vacuum_cleaner.nozzle.slot_nozzle or new_vacuum_cleaner.nozzle.turbo_brush or
                          new_vacuum_cleaner.nozzle.round_nozzle or new_vacuum_cleaner.nozzle.corner_nozzle or
                          new_vacuum_cleaner.nozzle.laminate_nozzle):
                    raise exception.InvalidNozzleValue("‼️ У вас отсутствует насадка. Сначала наденьте её.")
                else:
                    dust_room = Room(choose_length, choose_width)
                    new_vacuum_cleaner.clean_room(dust_room)
                CLIController.save_vacuum_cleaners(vacuum_cleaners)
            elif choose_action == "9":
                new_vacuum_cleaner.change_filter()
                CLIController.save_vacuum_cleaners(vacuum_cleaners)
            elif choose_action == "10":
                new_vacuum_cleaner.clean_dust_container()
                CLIController.save_vacuum_cleaners(vacuum_cleaners)
            elif choose_action == "11":
                choose_nozzle = input("Выберите насадку(Turbo Brush, Slot Nozzle, Round Nozzle, Laminate Nozzle, "
                                      "Corner Nozzle).\nВведите аналогично предложенным: ")
                new_vacuum_cleaner.change_nozzle(choose_nozzle)
                CLIController.save_vacuum_cleaners(vacuum_cleaners)
            elif choose_action == "12":
                new_vacuum_cleaner.technical_work()
                CLIController.save_vacuum_cleaners(vacuum_cleaners)
            elif choose_action == "13":
                break
            else:
                print("‼️ Неверный ввод ‼️")


class WebController:
    def __init__(self, webserver: WebServer):
        self.webserver = webserver
        self.webserver.add_route(route="/", handler_func=self.hello, methods=["GET"])
        self.webserver.add_route(route="/submit_vc", handler_func=self.submit_vc, methods=["POST"])
        self.webserver.add_route(route="/main", handler_func=self.main_page, methods=["GET"])
        self.webserver.add_route(route="/start_vc", handler_func=self.start_vc, methods=["GET"])
        self.webserver.add_route(route="/choose_nozzle", handler_func=self.choose_nozzle, methods=["GET", "POST"])
        self.webserver.add_route(route="/check_filter", handler_func=self.check_filter, methods=["GET", "POST"])
        self.webserver.add_route(route="/check_dust_container", handler_func=self.check_dust_container, methods=["GET", "POST"])
        self.webserver.add_route(route="/check_nozzle", handler_func=self.check_nozzle, methods=["GET"])
        self.webserver.add_route(route="/change_power", handler_func=self.change_power, methods=["GET", "POST"])
        self.webserver.add_route(route="/check_power", handler_func=self.check_power, methods=["GET"])
        self.webserver.add_route(route="/clean_room", handler_func=self.clean_room, methods=["GET"])
        self.webserver.add_route(route="/change_filter", handler_func=self.change_filter, methods=["GET"])
        self.webserver.add_route(route="/change_dust_container", handler_func=self.change_dust_container, methods=["GET"])
        self.webserver.add_route(route="/change_nozzle", handler_func=self.change_nozzle, methods=["GET", "POST"])
        self.webserver.add_route(route="/technical_work", handler_func=self.technical_work, methods=["GET"])
        self.webserver.add_route(route="/leave", handler_func=self.leave, methods=["GET"])

    def perform(self):
        self.webserver.run()

    @staticmethod
    def load_vacuum_cleaners():
        if os.path.isfile("resources/data.pickle"):
            with open("resources/data.pickle", "rb") as file:
                try:
                    vacuum_cleaners = pickle.load(file)
                    if not isinstance(vacuum_cleaners, list):
                        vacuum_cleaners = [vacuum_cleaners]
                except EOFError:
                    vacuum_cleaners = []
        else:
            vacuum_cleaners = []
        return vacuum_cleaners

    @staticmethod
    def save_vacuum_cleaners(vacuum_cleaners):
        with open("resources/data.pickle", "wb") as file:
            pickle.dump(vacuum_cleaners, file)

    def hello(self):
        vacuum_cleaners = self.load_vacuum_cleaners()
        return render_template('hello.html', vacuum_cleaners=vacuum_cleaners)

    def submit_vc(self):
        color = request.form['color']
        firm = request.form['firm']
        min_power = int(request.form['min_power'])
        max_power = int(request.form['max_power'])

        new_vacuum_cleaner = VacuumCleaner(color, firm)
        new_vacuum_cleaner.engine.set_power(min_power, max_power)

        vacuum_cleaners = self.load_vacuum_cleaners()
        vacuum_cleaners.append(new_vacuum_cleaner)
        self.save_vacuum_cleaners(vacuum_cleaners)

        return redirect(url_for('hello'))

    def main_page(self):
        vacuum_id = int(request.args.get('existing_vacuum'))
        vacuum_cleaners = self.load_vacuum_cleaners()
        selected_vacuum = vacuum_cleaners[vacuum_id]

        return render_template('main.html', vacuum=selected_vacuum, existing_vacuum=vacuum_id)

    def start_vc(self):
        vacuum_id = int(request.args.get('existing_vacuum'))
        vacuum_cleaners = self.load_vacuum_cleaners()
        selected_vacuum = vacuum_cleaners[vacuum_id]

        if request.method == 'GET' and 'power' not in request.args:
            return render_template('start_vc.html', existing_vacuum=vacuum_id)

        set_power = request.args.get('power', type=int)
        if selected_vacuum.control_buttons.start_b:
            message = "Пылесос уже запущен."
        elif set_power is None or set_power < selected_vacuum.engine.min_power or set_power > selected_vacuum.engine.max_power:
            message = "‼️ Введены неверные значения мощности."
        else:
            selected_vacuum.start_vacuum(set_power)
            self.save_vacuum_cleaners(vacuum_cleaners)
            message = "Пылесос запущен."

        return render_template('start_vc.html', existing_vacuum=vacuum_id, message=message)

    def choose_nozzle(self):
        if request.method == 'GET':
            vacuum_id = int(request.args.get('existing_vacuum'))
            return render_template('choose_nozzle.html', existing_vacuum=vacuum_id)

        vacuum_id = int(request.form['existing_vacuum'])
        nozzle = request.form['nozzle']
        vacuum_cleaners = self.load_vacuum_cleaners()
        selected_vacuum = vacuum_cleaners[vacuum_id]

        selected_vacuum.put_on_nozzle(nozzle)
        self.save_vacuum_cleaners(vacuum_cleaners)

        return redirect(url_for('main_page', existing_vacuum=vacuum_id))

    def check_filter(self):
        vacuum_id = int(request.args.get('existing_vacuum'))
        vacuum_cleaners = self.load_vacuum_cleaners()
        selected_vacuum = vacuum_cleaners[vacuum_id]

        filter_health = selected_vacuum.filter.health
        if filter_health > 0:
            message = f"Осталось {filter_health} чисток до замены фильтра"
        else:
            message = "Фильтр засорен. Требуется замена"

        return render_template('check_filter.html', vacuum=selected_vacuum, message=message, existing_vacuum=vacuum_id)

    def check_dust_container(self):
        vacuum_id = int(request.args.get('existing_vacuum'))
        vacuum_cleaners = self.load_vacuum_cleaners()
        selected_vacuum = vacuum_cleaners[vacuum_id]

        if selected_vacuum.dust_container.isFilled:
            message = "Контейнер для пыли заполнен."
        else:
            message = "Контейнер для пыли пуст."

        return render_template('check_dust_container.html', vacuum=selected_vacuum, message=message,
                               existing_vacuum=vacuum_id)

    def check_nozzle(self):
        vacuum_id = int(request.args.get('existing_vacuum'))
        vacuum_cleaners = self.load_vacuum_cleaners()
        selected_vacuum = vacuum_cleaners[vacuum_id]

        if selected_vacuum.nozzle.turbo_brush:
            message = "У вас надета насадака: Turbo Brush"
        elif selected_vacuum.nozzle.slot_nozzle:
            message = "У вас надета насадака: Slot Nozzle"
        elif selected_vacuum.nozzle.round_nozzle:
            message = "У вас надета насадака: Round Nozzle"
        elif selected_vacuum.nozzle.laminate_nozzle:
            message = "У вас надета насадака: Laminate Nozzle"
        elif selected_vacuum.nozzle.corner_nozzle:
            message = "У вас надета насадака: Corner Nozzle"
        else:
            message = "У вас нет никакой насадки."

        return render_template('check_nozzle.html', vacuum=selected_vacuum, message=message,
                               existing_vacuum=vacuum_id)

    def change_power(self):
        if request.method == 'GET':
            vacuum_id = int(request.args.get('existing_vacuum'))
            vacuum_cleaners = self.load_vacuum_cleaners()
            selected_vacuum = vacuum_cleaners[vacuum_id]
            return render_template('change_power.html', vacuum=selected_vacuum, existing_vacuum=vacuum_id)

        vacuum_id = int(request.form['existing_vacuum'])
        wishful_power = int(request.form['power'])
        vacuum_cleaners = self.load_vacuum_cleaners()
        selected_vacuum = vacuum_cleaners[vacuum_id]

        if wishful_power < selected_vacuum.engine.min_power or wishful_power > selected_vacuum.engine.max_power:
            message = "‼️ Введены неверные значения мощности."
        else:
            selected_vacuum.control_buttons.change_power(wishful_power)
            self.save_vacuum_cleaners(vacuum_cleaners)
            message = "Мощность изменена."

        return render_template('change_power.html', vacuum=selected_vacuum, message=message, existing_vacuum=vacuum_id)

    def check_power(self):
        vacuum_id = int(request.args.get('existing_vacuum'))
        vacuum_cleaners = self.load_vacuum_cleaners()
        selected_vacuum = vacuum_cleaners[vacuum_id]

        current_power = selected_vacuum.control_buttons.using_power
        message = f"Текущая мощность: {current_power} Вт"

        return render_template('check_power.html', vacuum=selected_vacuum, message=message, existing_vacuum=vacuum_id)

    def clean_room(self):
        vacuum_id = int(request.args.get('existing_vacuum'))
        vacuum_cleaners = self.load_vacuum_cleaners()
        selected_vacuum = vacuum_cleaners[vacuum_id]

        if request.method == 'GET' and ('length' not in request.args or 'width' not in request.args):
            return render_template('clean_room.html', vacuum=selected_vacuum, existing_vacuum=vacuum_id, message="")

        length = int(request.args.get('length'))
        width = int(request.args.get('width'))

        if not selected_vacuum.control_buttons.start_b:
            message = "‼️ Пылесос не запущен. Запустите его."
        elif selected_vacuum.dust_container.isFilled:
            message = "‼️ Контейнер для пыли заполнен. Очистите его."
        elif selected_vacuum.filter.health <= 0:
            message = "‼️ Фильтр засорен. Сначала очистите его."
        elif not (selected_vacuum.nozzle.slot_nozzle or selected_vacuum.nozzle.turbo_brush or
                  selected_vacuum.nozzle.round_nozzle or selected_vacuum.nozzle.corner_nozzle or
                  selected_vacuum.nozzle.laminate_nozzle):
            message = "‼️ У вас отсутствует насадка. Сначала наденьте её."
        else:
            dust_room = Room(length, width)
            selected_vacuum.clean_room(dust_room)
            self.save_vacuum_cleaners(vacuum_cleaners)
            message = "Уборка выполнена."

        return render_template('clean_room.html', vacuum=selected_vacuum, existing_vacuum=vacuum_id, message=message)

    def change_filter(self):
        vacuum_id = int(request.args.get('existing_vacuum'))
        vacuum_cleaners = self.load_vacuum_cleaners()
        selected_vacuum = vacuum_cleaners[vacuum_id]

        selected_vacuum.filter.renew_filter()
        self.save_vacuum_cleaners(vacuum_cleaners)
        message = "Фильтр заменен."

        return render_template('change_filter.html', vacuum=selected_vacuum, message=message, existing_vacuum=vacuum_id)

    def change_dust_container(self):
        vacuum_id = int(request.args.get('existing_vacuum'))
        vacuum_cleaners = self.load_vacuum_cleaners()
        selected_vacuum = vacuum_cleaners[vacuum_id]

        selected_vacuum.clean_dust_container()
        self.save_vacuum_cleaners(vacuum_cleaners)
        message = "Контейнер для пыли очищен."

        return render_template('change_dust_container.html', vacuum=selected_vacuum, message=message,
                               existing_vacuum=vacuum_id)

    def change_nozzle(self):
        if request.method == 'GET':
            vacuum_id = int(request.args.get('existing_vacuum'))
            return render_template('change_nozzle.html', existing_vacuum=vacuum_id)

        vacuum_id = int(request.form['existing_vacuum'])
        nozzle = request.form['nozzle']
        vacuum_cleaners = self.load_vacuum_cleaners()
        selected_vacuum = vacuum_cleaners[vacuum_id]

        selected_vacuum.change_nozzle(nozzle)
        self.save_vacuum_cleaners(vacuum_cleaners)

        return redirect(url_for('main_page', existing_vacuum=vacuum_id))

    def technical_work(self):
        vacuum_id = int(request.args.get('existing_vacuum'))
        vacuum_cleaners = self.load_vacuum_cleaners()
        selected_vacuum = vacuum_cleaners[vacuum_id]

        selected_vacuum.technical_work()
        self.save_vacuum_cleaners(vacuum_cleaners)
        message = "Техническое обслуживание выполнено."

        return render_template('technical_work.html', vacuum=selected_vacuum, message=message,
                               existing_vacuum=vacuum_id)

    @staticmethod
    def leave():
        return render_template('leave.html')
