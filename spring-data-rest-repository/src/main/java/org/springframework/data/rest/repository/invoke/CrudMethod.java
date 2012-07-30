package org.springframework.data.rest.repository.invoke;

import java.lang.reflect.Method;

/**
 * @author Jon Brisbin
 */
public enum CrudMethod {

  COUNT,
  DELETE_ALL,
  DELETE_ONE,
  DELETE_SOME,
  FIND_ALL,
  FIND_ONE,
  FIND_SOME,
  SAVE_ONE,
  SAVE_SOME;

  public static CrudMethod fromMethod(Method m) {
    String s = m.getName();
    Class<?>[] paramTypes = m.getParameterTypes();
    boolean some = (paramTypes.length > 0 && Iterable.class.isAssignableFrom(paramTypes[0]));
    if("count".equals(s)) {
      return COUNT;
    } else if("delete".equals(s)) {
      return (some ? DELETE_SOME : DELETE_ONE);
    } else if("deleteAll".equals(s)) {
      return DELETE_ALL;
    } else if("findAll".equals(s)) {
      return (some ? FIND_SOME : FIND_ALL);
    } else if("findOne".equals(s)) {
      return FIND_ONE;
    } else if("save".equals(s)) {
      return (some ? SAVE_SOME : SAVE_ONE);
    } else {
      return null;
    }
  }

  public String toMethodName() {
    switch(this) {
      case COUNT:
        return "count";
      case DELETE_ALL:
        return "deleteAll";
      case DELETE_ONE:
      case DELETE_SOME:
        return "delete";
      case FIND_ALL:
      case FIND_SOME:
        return "findAll";
      case FIND_ONE:
        return "findOne";
      case SAVE_ONE:
      case SAVE_SOME:
        return "save";
      default:
        return null;
    }
  }

}
