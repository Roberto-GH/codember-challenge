package com.roberto.codember.utils;

import java.util.List;
import java.util.stream.Collectors;

public class Challenge04Util {

  private static final int VALID_SIZE = 5;
  private static final int MIN_VALID_FIVES = 2;

  public boolean validIsHi5(List<String> splitPassword) {
    return splitPassword.size() == VALID_SIZE;
  }

  public boolean validIsPairFives(List<String> splitPassword) {
    List<String> fives = splitPassword
      .stream()
      .filter(cc -> cc.equals("5"))
      .collect(Collectors.toList());
    return fives.size() >= MIN_VALID_FIVES;
  }

  public boolean validIsAscendingNumber(List<String> splitPassword) {
    boolean result = true;
    for (int i = 0; i < splitPassword.size(); i++) {
      if (i + 1 < splitPassword.size()) {
        if (Integer.parseInt(splitPassword.get(i + 1)) < Integer.parseInt(splitPassword.get(i))) result = false;
      }
    }
    return result;
  }

}
