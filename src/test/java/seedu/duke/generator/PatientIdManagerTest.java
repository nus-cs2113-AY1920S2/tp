package seedu.duke.generator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class PatientIdManagerTest {

    private Map<Integer, Integer> patientIdMap = new HashMap<>();

    @BeforeEach
    void setUp() {

        for (int i = 0; i < 10; i++) {
            patientIdMap.put(i * 2, 1);
        }

    }

    @AfterEach
    void tearDown() {
        patientIdMap.clear();
        PatientIdManager.setNextTopNewNumber(0);
        PatientIdManager.getNextNumberQueueThing().clear();
        PatientIdManager.getPatientIdMap().clear();
    }

    @Test
    void getPatientIdMap() {
        PatientIdManager.setPatientIdMap(patientIdMap);
        Map<Integer, Integer> tempmap = PatientIdManager.getPatientIdMap();
        assertEquals(tempmap, patientIdMap);

    }

    @Test
    void getPatientIdMapOnAdd() {
        PatientIdManager.setPatientIdMap(patientIdMap);
        PatientIdManager.setNextTopNewNumber(100);
        PatientIdManager.getNextPatientId();
        Map<Integer, Integer> tempmap = PatientIdManager.getPatientIdMap();
        patientIdMap.put(100, 1);
        assertEquals(patientIdMap, tempmap);

    }

    @Test
    void getPatientIdMapOnAddAndDelete() {
        PatientIdManager.setPatientIdMap(patientIdMap);
        PatientIdManager.setNextTopNewNumber(100);
        PatientIdManager.getNextPatientId();
        PatientIdManager.addBackPatientId(2);
        PatientIdManager.addBackPatientId(10);
        Map<Integer, Integer> tempmap = PatientIdManager.getPatientIdMap();
        Map<Integer, Integer> patientIdMap2 = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            if (i != 1 && i != 5) {
                patientIdMap2.put(i * 2, 1);
            } else {
                patientIdMap2.put(i * 2, null);
            }
        }
        patientIdMap2.put(100, 1);
        assertEquals(patientIdMap2, tempmap);

    }

    @Test
    void setPatientIdMap() {
        PatientIdManager.setPatientIdMap(patientIdMap);
        Map<Integer, Integer> tempmap = PatientIdManager.getPatientIdMap();
        assertEquals(tempmap, patientIdMap);
    }

    @Test
    void getNextTopNewNumber() {
        PatientIdManager.setNextTopNewNumber(23);
        assertEquals(23, PatientIdManager.getNextTopNewNumber());
    }

    @Test
    void getNextTopNewNumberNew() {
        assertEquals(0, PatientIdManager.getNextTopNewNumber());
    }

    @Test
    void getNextTopNewNumberUponAddPatient() {
        for (int i = 0; i < 10; i++) {
            int temp = PatientIdManager.getNextPatientId();
        }
        assertEquals(10, PatientIdManager.getNextTopNewNumber());
    }

    @Test
    void getNextTopNewNumberUponAddAndDeletePatient() {
        for (int i = 0; i < 10; i++) {
            int temp = PatientIdManager.getNextPatientId();
        }
        PatientIdManager.addBackPatientId(2);
        PatientIdManager.addBackPatientId(3);
        PatientIdManager.addBackPatientId(4);
        int temp = PatientIdManager.getNextPatientId();

        assertEquals(10, PatientIdManager.getNextTopNewNumber());
    }

    @Test
    void setNextTopNewNumber() {
        PatientIdManager.setNextTopNewNumber(23);
        assertEquals(23, PatientIdManager.getNextTopNewNumber());
    }

    @Test
    void getNextNumberQueueThingDeletePatient() {
        for (int i = 0; i < 10; i++) {
            PatientIdManager.getNextPatientId();
        }
        PatientIdManager.addBackPatientId(2);
        Queue<Integer> tempQueue = PatientIdManager.getNextNumberQueueThing();
        assertEquals(2, tempQueue.remove());

    }

    @Test
    void getNextNumberQueueThing() {

        Queue<Integer> tempQueue = PatientIdManager.getNextNumberQueueThing();
        Queue<Integer> emptyQueue = new LinkedList<>();
        assertEquals(emptyQueue, tempQueue);

    }

    @Test
    void getNextPatientId() {
        PatientIdManager.setNextTopNewNumber(100);
        assertEquals(100, PatientIdManager.getNextPatientId());
    }

    @Test
    void getNextPatientIdOnAdd() {
        PatientIdManager.setNextTopNewNumber(100);
        int temp = PatientIdManager.getNextPatientId();
        assertEquals(101, PatientIdManager.getNextPatientId());
    }

    @Test
    void getNextPatientIdOnAddAndDelete() {
        PatientIdManager.setNextTopNewNumber(100);
        int temp = PatientIdManager.getNextPatientId();
        PatientIdManager.addBackPatientId(2);
        PatientIdManager.addBackPatientId(11);
        PatientIdManager.addBackPatientId(2);
        PatientIdManager.addBackPatientId(12);
        PatientIdManager.addBackPatientId(13);
        PatientIdManager.addBackPatientId(34);
        PatientIdManager.addBackPatientId(36);
        int temp2 = PatientIdManager.getNextPatientId();
        PatientIdManager.addBackPatientId(42);
        PatientIdManager.addBackPatientId(61);
        assertEquals(11, PatientIdManager.getNextPatientId());
    }

    @Test
    void getNextPatientIdOnAddAndDelete2() {
        PatientIdManager.setNextTopNewNumber(100);
        assertEquals(100, PatientIdManager.getNextPatientId());
        PatientIdManager.addBackPatientId(2);
        PatientIdManager.addBackPatientId(11);
        PatientIdManager.addBackPatientId(2);
        PatientIdManager.addBackPatientId(12);
        PatientIdManager.addBackPatientId(13);
        PatientIdManager.addBackPatientId(34);
        PatientIdManager.addBackPatientId(36);
        assertEquals(2, PatientIdManager.getNextPatientId());
        assertEquals(11, PatientIdManager.getNextPatientId());
        assertEquals(12, PatientIdManager.getNextPatientId());
        assertEquals(13, PatientIdManager.getNextPatientId());
        assertEquals(34, PatientIdManager.getNextPatientId());
        assertEquals(36, PatientIdManager.getNextPatientId());
        assertEquals(101, PatientIdManager.getNextPatientId());
        assertEquals(102, PatientIdManager.getNextPatientId());
    }

    @Test
    void getNextPatientIdOnError() {
        PatientIdManager.setNextTopNewNumber(100);
        PatientIdManager.addBackPatientId(-300);
        assertEquals(100, PatientIdManager.getNextPatientId());
        PatientIdManager.addBackPatientId(1000);
        assertEquals(101, PatientIdManager.getNextPatientId());
    }

    @Test
    void addBackPatientId() {
        PatientIdManager.setNextTopNewNumber(100);
        PatientIdManager.addBackPatientId(2);
        assertEquals(2, PatientIdManager.getNextPatientId());
    }

    @Test
    void checkPatientIdUsed() {
        PatientIdManager.setNextTopNewNumber(100);
        int temp = PatientIdManager.getNextPatientId();
        PatientIdManager.addBackPatientId(2);
        PatientIdManager.addBackPatientId(11);
        PatientIdManager.addBackPatientId(2);
        PatientIdManager.addBackPatientId(12);
        PatientIdManager.addBackPatientId(13);
        int temp2 = PatientIdManager.getNextPatientId();
        int temp3 = PatientIdManager.getNextPatientId();
        assertTrue(PatientIdManager.checkPatientIdUsed(100));
        assertTrue(PatientIdManager.checkPatientIdUsed(2));
        assertTrue(PatientIdManager.checkPatientIdUsed(11));
        assertFalse(PatientIdManager.checkPatientIdUsed(12));
        assertFalse(PatientIdManager.checkPatientIdUsed(13));
        assertFalse(PatientIdManager.checkPatientIdUsed(101));

    }

    @Test
    void clearPatientId() {
        PatientIdManager.clearPatientId();
        Queue<Integer> emptyQueue = new LinkedList<>();
        Map<Integer, Integer> emptyPatientIdMap = new HashMap<>();
        assertEquals(0, PatientIdManager.getNextTopNewNumber());
        assertEquals(emptyQueue, PatientIdManager.getNextNumberQueueThing());
        assertEquals(emptyPatientIdMap, PatientIdManager.getPatientIdMap());
    }
}