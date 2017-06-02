package org.automation.testing;
import org.junit.Assert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Matching {
 // you can use comparable interface also.. 
 public static <E1,E2>int compare(List<E1> original, List<E2> expected){
        int i = -1;
       
        if(expected.size()==original.size())
        {
            i++;
            for (int j =0;j<original.size();j++) {
                System.out.println("Checking " + original.get(j).toString() + " with " + expected.get(j).toString());
                if (original.get(j).equals(expected.get(j))) {
                    i++;
                }
            }
            if(i!=original.size()) {//all items should be matched.
                i = -1;
            }
        }
        return i;
    }
  public static <T> void compare(T a, T b) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        AssertionError error = null;
        Class A = a.getClass();
        Class B = a.getClass();
        for (Method mA : A.getDeclaredMethods()) {
            if (mA.getName().startsWith("get")) {
                System.out.println("Method name in B = " + B.getMethod(mA.getName(), null));
                Method mB = B.getMethod(mA.getName(),null );
                try {
                    Assert.assertEquals("Not Matched = ",mA.invoke(a),mB.invoke(b));
                }catch (AssertionError e){
                    if(error==null){
                        error = new AssertionError(e);
                    }
                    error.addSuppressed(e);
                }
            }
        }
        if(error!=null){
            throw error ;
        }
    }
}
