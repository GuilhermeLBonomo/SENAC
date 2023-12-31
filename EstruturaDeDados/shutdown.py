from os import system, getlogin
from sys import platform
from time import sleep


class Shutdown:
    """A class for shutdown the computer."""

    def __init__(self) -> None:
        shutd = Shutdown.get_os_command()
        if shutd != "Invalid System!":
            system(shutd)

    @staticmethod
    def get_os_command() -> str:
        if platform in ("linux", "linux2", "darwin"):
            return "shutdown now"
        elif platform in ("win32", "cygwin", "msys"):
            return "shutdown -s -f -t 0"
        else:
            return "Invalid System!"


if __name__ == "__main__":
    print(f"bye {getlogin()}!")
    sleep(2)
    s = Shutdown()
