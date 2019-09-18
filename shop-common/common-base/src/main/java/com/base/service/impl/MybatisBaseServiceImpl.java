package com.base.service.impl;

import com.base.dao.MyBatisBaseDao;
import com.base.model.Page;
import com.base.service.MybatisBaseService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class MybatisBaseServiceImpl implements MybatisBaseService
{

  public static int type = 1;
  public static final int MYSQL = 1;
  public static final int ORACLE = 2;

  public abstract MyBatisBaseDao getDao();

  public <T> int insert(T parameter)
  {
    return getDao().insert(parameter);
  }

  public <T> int update(T parameter)
  {
    return getDao().update(parameter);
  }

  public <T> int delete(T parameter)
  {
    return getDao().delete(parameter);
  }

  public <T, P> T selectObject(P parameter)
  {
    return getDao().selectObject(parameter);
  }

  public <T, P> List<T> selectObjectList(P parameter)
  {
    return getDao().selectObjectList(parameter);
  }

  public <V, P> Map<String, V> selectMap(P parameter)
  {
    return getDao().selectMap(parameter);
  }

  public <V, P> List<Map<String, V>> selectMapList(P parameter)
  {
    return getDao().selectMapList(parameter);
  }

  public <T> Page<T> page(Map<String, Object> map, int pageIndex, int pageSize)
  {
    int count = getDao().pageCount(map);

    int offset = (pageIndex - 1) * pageSize;

    if (type == 1) {
      map.put("offset", Integer.valueOf(offset));
      map.put("rows", Integer.valueOf(pageSize));
    } else if (type == 2) {
      map.put("begin", Integer.valueOf(offset));
      map.put("end", Integer.valueOf(offset + pageSize));
    }

    List list = getDao().page(map);

    return new Page(pageIndex, pageSize, count, list);
  }

  public <T> Page<T> page(String pageSelectId, String pageCountSelectId, Map<String, Object> map, int pageIndex, int pageSize)
  {
    MyBatisBaseDao dao = getDao();

    int count = 0;

    Class clazz = dao.getClass();
    Method method = null;
    try {
      method = clazz.getDeclaredMethod(pageCountSelectId, new Class[] { Map.class });
    } catch (NoSuchMethodException e1) {
        e1.printStackTrace();
    }
    catch (SecurityException e1) {
      e1.printStackTrace();
    }
    try
    {
      try {
        count = ((Integer)method.invoke(dao, new Object[] { map })).intValue();
      } catch (ClassCastException e) {
          e.printStackTrace();
      }
    } catch (IllegalAccessException e1) {
      e1.printStackTrace();
    } catch (IllegalArgumentException e1) {
      e1.printStackTrace();
    } catch (InvocationTargetException e1) {
        e1.printStackTrace();
    }

    int offset = (pageIndex - 1) * pageSize;

    if (type == 1) {
      map.put("offset", Integer.valueOf(offset));
      map.put("rows", Integer.valueOf(pageSize));
    } else if (type == 2) {
      map.put("begin", Integer.valueOf(offset));
      map.put("end", Integer.valueOf(offset + pageSize));
    }

    List list = null;

    Class clazz2 = dao.getClass();
    Method method2 = null;
    try {
      method2 = clazz2.getDeclaredMethod(pageSelectId, new Class[] { Map.class });
    } catch (NoSuchMethodException e) {
        e.printStackTrace();
    }
    catch (SecurityException e) {
      e.printStackTrace();
    }
    try
    {
      try {
        list = (ArrayList)method2.invoke(dao, new Object[] { map });
      } catch (ClassCastException e) {
          e.printStackTrace();
      }
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
        e.printStackTrace();
    }

    return new Page(pageIndex, pageSize, count, list);
  }


  public String dbThreadName()
  {
    String threadName = "DB_" + getClass().getSimpleName().replace("ServiceImpl", "").toUpperCase() + "_THREAD";
    return threadName;
  }
}

