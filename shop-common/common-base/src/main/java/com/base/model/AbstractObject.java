 package com.base.model;
 
 import java.io.Serializable;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public abstract class AbstractObject
   implements Serializable, Cloneable
 {
   public static final long serialVersionUID = 1L;
 
   public String toString()
   {
     return ToStringBuilder.reflectionToString(this);
   }
 
   public Object clone()
   {
     Object obj = null;
     try {
       obj = super.clone();
     } catch (CloneNotSupportedException e) {
       e.printStackTrace();
     }
     return obj;
   }
 }

