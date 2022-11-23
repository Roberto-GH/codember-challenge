package com.roberto.codember;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.roberto.codember.controller.Challenge04;
import com.roberto.codember.utils.Challenge04Module;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

  private static final Logger LOG = LoggerFactory.getLogger(Main.class);
  private static final Injector injector = Guice.createInjector(new Challenge04Module());

  public static void main(String[] args) {
    LOG.info("****** Codember challenge 04 ******");
    Challenge04 challenge04 = injector.getInstance(Challenge04.class);
    challenge04.mineBitcoin();
  }

}