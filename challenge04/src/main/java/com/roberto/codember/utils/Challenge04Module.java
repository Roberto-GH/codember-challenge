package com.roberto.codember.utils;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.roberto.codember.controller.Challenge04;


public class Challenge04Module extends AbstractModule  {

  @Override
  protected void configure() {
    bind(Challenge04.class).in(Scopes.SINGLETON);
    bind(Challenge04Util.class).in(Scopes.SINGLETON);
  }

}
