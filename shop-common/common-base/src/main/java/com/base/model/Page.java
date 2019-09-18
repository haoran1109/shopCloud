package com.base.model;

import java.util.Collection;

public class Page<E> extends AbstractObject
{
  private int page;
  private int pageSize;
  private long count;
  public Collection<E> data;

  public Page(int pageIndex, int pageSize, long rowCount, Collection<E> data)
  {
    this.page = pageIndex;
    this.pageSize = pageSize;
    this.count = rowCount;
    this.data = data;
  }

  public int getPage() {
    return this.page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getPageSize() {
    return this.pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public long getCount() {
    return this.count;
  }

  public void setCount(long count) {
    this.count = count;
  }

  public Collection<E> getData() {
    return this.data;
  }

  public void setData(Collection<E> data) {
    this.data = data;
  }
}

