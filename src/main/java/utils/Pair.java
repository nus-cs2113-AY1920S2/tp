package utils;

public class Pair<T, U> {
    private final T firstVal;
    private final U secondVal;
    
    private Pair(T firstVal, U secondVal) {
        this.firstVal = firstVal;
        this.secondVal = secondVal;
    }        

    public static <T, U> Pair<T, U> of(T firstVal, U secondVal) {
        return new Pair<T, U>(firstVal, secondVal);
    }
    
    public T first() {
        return this.firstVal;
    }
    
    public U second() {
        return this.secondVal;
    }
} 
