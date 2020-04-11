package utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PairTest {

    @Test
    public void test_pairOfMethod_constructNormally() {
        Pair<Integer, Double> firstPair = Pair.of(1, 0.5);
        Pair<Integer, Double> secondPair = Pair.of(1, 0.5);
        
        assertEquals(firstPair.first(), secondPair.first());
        assertEquals(firstPair.second(), secondPair.second());
        
        Pair<String, String> thirdPair = Pair.of("rice", "banana");
        Pair<String, String> fourthPair = Pair.of("rice", "chicken");
        
        assertEquals(thirdPair.first(), fourthPair.first());
        assertFalse(thirdPair.second().equals(fourthPair.second()));        
    }
    
    @Test 
    public void test_equals_TrueIfBothPairsHaveSameFirstAndSecondValue() {       
        Pair<Integer, Double> firstPair = Pair.of(1, 0.5);
        Pair<Integer, Double> secondPair = Pair.of(1, 0.5);
        
        assertTrue(firstPair.equals(secondPair));        
    }
    
    @Test 
    public void test_equals_TrueIfBothPairsHaveSameTypings() {
        Pair<Integer, Double> firstPair = Pair.of(1, 0.5);
        Pair<Integer, Double> secondPair = Pair.of(1, 0.5);
        Pair<String, String> thirdPair = Pair.of("rice", "banana");
        Pair<String, String> fourthPair = Pair.of("rice", "chicken");
        
        assertFalse(thirdPair.equals(fourthPair));
        assertFalse(firstPair.equals(thirdPair));
        assertFalse(secondPair.equals(fourthPair));
    }
    
}
