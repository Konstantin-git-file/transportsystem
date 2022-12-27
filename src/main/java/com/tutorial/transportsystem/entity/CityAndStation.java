package com.tutorial.transportsystem.entity;

// не знаю хорошее это решение или нет

// плохое. нужно переделать на Entity и таблицу t_town и t_station

public enum CityAndStation {
  MOSCOW,
  SAINT_PETERSBURG,
  KAZAN;

  public enum MOSCOW {
    LENINGRADSKY,
    KAZANSKY,
    YAROSLAVLSKY;
  }

  public enum SAINT_PETERSBURG {
    MOSKOVSKY,
    VITEBSKY,
    LADOZHSKY;
  }

  public enum KAZAN {
    KAZAN_PASSAZHIRSKAYA;
  }
}
