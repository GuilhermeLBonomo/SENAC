#include "GuiShell.cpp"

int main() {
  GuiShell guiShell;
  try {
    guiShell.run();
  } catch (const std::exception &e) {
    std::cerr << "Error: " << e.what() << std::endl;
  }
  return 0;
}
