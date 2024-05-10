#ifndef GUISCHELL_HPP
#define GUISCHELL_HPP

#include <string>

class GuiShellInterface {
public:
  static void run();
  static void displayBootScreen();
  static std::string generatePrompt();
  static bool executeCommand(const std::string &command);
};

#endif
