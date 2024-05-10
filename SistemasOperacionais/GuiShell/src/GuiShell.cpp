#include "../include/GuiShell.hpp"
#include "SystemInfo.cpp"
#include <cstdlib>
#include <iostream>

class GuiShell : public GuiShellInterface {
public:
  static void run() {
    try {
      std::printf("\nIniciando...\n\n");
      std::string input;
      displayBootScreen();
      while (true) {
        std::string prompt = generatePrompt();
        if (prompt.empty()) {
          throw std::runtime_error("Error generating prompt");
        }

        std::cout << prompt;
        std::getline(std::cin, input);

        if (input == "exit") {
          std::cout << "Saindo do GuiShell. Adeus!" << std::endl;
          break;
        } else {
          if (!executeCommand(input)) {
            throw std::runtime_error("Error executing command");
          }
        }
      }
    } catch (const std::exception &e) {
      std::cerr << "Error: " << e.what() << std::endl;
    }
  }

private:
  static void displayBootScreen() {
    try {
      printf("Sistema: %s %s\n", SystemInfo::getOSName().c_str(),
             SystemInfo::getOSVersion().c_str());
      printf("Usuário: %s\n", SystemInfo::getUserName().c_str());
      printf("Diretório de trabalho: %s\n",
             SystemInfo::getCurrentWorkingDirectory().c_str());
      printf("Hora atual: %s\n\n", SystemInfo::getTime().c_str());
    } catch (const std::exception &e) {
      std::cerr << "Error: " << e.what() << std::endl;
    }
  }

  static std::string generatePrompt() {
    std::string username = SystemInfo::getUserName();
    std::string cwd = SystemInfo::getCurrentWorkingDirectory();
    std::string time = SystemInfo::getTime();
    return "@" + username + "[" + cwd + "]" + "|" + time + ">>";
  }

  static bool executeCommand(const std::string &command) {
    bool output = true;
    const char *command_cstr = command.c_str();
    int exitCode = std::system(command_cstr);
    if (exitCode != 0) {
      output = false;
      std::cerr << "Erro ao executar o comando." << std::endl;
    }
    return output;
  }
};
