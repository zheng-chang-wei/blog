package com.zcw.blog.common.page;

import com.google.common.base.MoreObjects;
import lombok.Data;

import java.io.Serializable;

@Data
public class QueryRequest implements Serializable {

  private static final long serialVersionUID = -4869594085374385813L;

  private int pageSize;

  private int pageNum;

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("pageSize", pageSize)
        .add("pageNum", pageNum)
        .toString();
  }
}
