#include "../include/SystemInfo.hpp"
#include <cstdlib>
#include <ctime>
#include <sys/utsname.h>
#include <unistd.h>

class SystemInfo : SystemInfoInterface {
public:
  static std::string getOSName() {
    struct utsname unameData;
    if (uname(&unameData) != -1) {
      return unameData.sysname;
    }
    return "Unknown";
  }
  static std::string getOSVersion() {
    struct utsname unameData;
    if (uname(&unameData) != -1) {
      return unameData.release;
    }
    return "Unknown";
  }

  static std::string getUserName() {
    char *username = getenv("USER");
    if (username != nullptr) {
      return username;
    }
    return "Unknown";
  }

  static std::string getCurrentWorkingDirectory() {
    char cwd[1024];
    if (getcwd(cwd, sizeof(cwd)) != nullptr) {
      return std::string(cwd);
    }
    return "Unknown";
  }

  static std::string getTime() {
    std::time_t now = std::time(nullptr);
    std::tm *timeinfo = std::localtime(&now);
    char buffer[9];
    std::strftime(buffer, sizeof(buffer), "%H:%M:%S", timeinfo);
    return buffer;
  }
};
