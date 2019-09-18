package com.base.dao;

import java.util.List;
import java.util.Map;

public abstract interface MyBatisBaseDao
{
  public abstract <P> int insert(P paramP);

  public abstract <P> int update(P paramP);

  public abstract <P> int delete(P paramP);

  public abstract <T, P> T selectObject(P paramP);

  public abstract <T, P> List<T> selectObjectList(P paramP);

  public abstract <K, V, P> Map<K, V> selectMap(P paramP);

  public abstract <K, V, P> List<Map<K, V>> selectMapList(P paramP);

  public abstract <T, P> List<T> page(P paramP);

  public abstract <P> int pageCount(P paramP);
}

