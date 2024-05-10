#ifndef SYSTEMINFO_HPP
#define SYSTEMINFO_HPP

#include <string>

class SystemInfoInterface {
public:
  static std::string getOSName();
  static std::string getOSVersion();
  static std::string getUserName();
  static std::string getCurrentWorkingDirectory();
  static std::string getTime();
};

#endif
