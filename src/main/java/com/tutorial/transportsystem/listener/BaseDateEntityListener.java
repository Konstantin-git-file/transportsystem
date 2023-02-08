package com.tutorial.transportsystem.listener;

import jakarta.persistence.*;

public class BaseDateEntityListener {
  @PrePersist
  public void prePersist(Object o) {}

  @PreUpdate
  public void preUpdate(Object o) {}

  @PreRemove
  public void preRemove(Object o) {}

  @PostLoad
  public void postLoad(Object o) {}

  @PostRemove
  public void postRemove(Object o) {}

  @PostUpdate
  public void postUpdate(Object o) {}

  @PostPersist
  public void postPersist(Object o) {}
}
