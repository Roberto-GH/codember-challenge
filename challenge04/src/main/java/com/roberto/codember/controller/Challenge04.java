package com.roberto.codember.controller;

import com.google.inject.Inject;
import com.roberto.codember.model.ValidPassword;
import com.roberto.codember.utils.Challenge04Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Challenge04 {

  private static final Logger LOG = LoggerFactory.getLogger(Challenge04.class);
  private static final long RANGE_INIT = 11098;
  private static final long RANGE_FINAL = 98123;
  private static final int TARGET_POSITION = 55;

  @Inject
  private Challenge04Util util;

  public void mineBitcoin() {
    try {
      AtomicInteger total = new AtomicInteger();
      AtomicBoolean isPairFives = new AtomicBoolean(false);
      AtomicBoolean isHi5 = new AtomicBoolean(false);
      AtomicBoolean isAscendingNumber = new AtomicBoolean(true);
      AtomicBoolean isValidPassword = new AtomicBoolean(false);
      AtomicInteger position = new AtomicInteger();
      ArrayList<ValidPassword> validPasswords = new ArrayList<>();

      List<Long> passwords = LongStream
        .range(RANGE_INIT, RANGE_FINAL)
        .boxed()
        .collect(Collectors.toList());

      passwords.forEach(password -> {
        String passwordString = String.valueOf(password);
        List<String> splitPassword = Arrays.stream(passwordString.split("")).collect(Collectors.toList());

        isHi5.set(util.validIsHi5(splitPassword));
        isPairFives.set(util.validIsPairFives(splitPassword));
        isAscendingNumber.set(util.validIsAscendingNumber(splitPassword));
        isValidPassword.set(isPairFives.get() && isHi5.get() && isAscendingNumber.get());

        if (isValidPassword.get()) {
          total.addAndGet(1);
          ValidPassword valid = ValidPassword
            .builder()
            .pass(password)
            .position(position.get())
            .build();
          position.getAndIncrement();
          validPasswords.add(valid);
        }
        isAscendingNumber.set(true); //reset
      });

      LOG.info("Total passwords: ".concat(String.valueOf(total.get())));
      Optional<ValidPassword> valid55 = validPasswords.stream().filter(f -> f.getPosition() == TARGET_POSITION).findFirst();
      if (valid55.isPresent()) {
        LOG.info("Pass: ".concat(String.valueOf(valid55.get().getPass())));
        LOG.info("Position: ".concat(String.valueOf(valid55.get().getPosition())));
        LOG.info("submit ".concat(String.valueOf(total.get())).concat("-").concat(String.valueOf(valid55.get().getPass())));
      }

    } catch (Exception e) {
      LOG.error(e.getMessage());
      throw new RuntimeException();
    }
  }

}
