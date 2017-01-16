public class DebugUtils {
 public static <T> void printAll(Collection<T> records){
        for(T t : records){
        System.out.println(t.toString());
        }
    }
}
