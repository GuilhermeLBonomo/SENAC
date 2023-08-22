from os import system

class Shutdown:
    def __init__() -> None:
        shutdown_command = 'shutdown -s -f -t 0'
        system(shutdown_command)

if __name__ == '__main__':
    s = Shutdown()

