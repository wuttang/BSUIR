import threading
from server import WebServer
from controller import CLIController, WebController


def run_web_server():
    server = WebServer()
    web_controller = WebController(server)
    web_controller.perform()


def run_cli():
    cli = CLIController()
    cli.perform()


if __name__ == '__main__':
    cli_thread = threading.Thread(target=run_cli, args=())
    flask_thread = threading.Thread(target=run_web_server, args=())

    cli_thread.start()
    flask_thread.start()

    cli_thread.join()
    flask_thread.join()
