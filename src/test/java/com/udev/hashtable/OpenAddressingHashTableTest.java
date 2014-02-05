package com.udev.hashtable;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * User: oleg.krupenya
 * Date: 12/4/13
 * Time: 8:29 PM
 */
public class OpenAddressingHashTableTest {
    private IHashTable<String, Integer> map;

    @Before
    public void setUp() {
        this.map = new OpenAddressingHashTable<String, Integer>();
    }

    @After
    public void tearDown() {
        this.map = null;
    }

    @Test
    public void allOperations_hp() {
        map.put("1", 1);
        Assert.assertTrue(map.size() == 1);
        Assert.assertTrue(map.containsKey("1"));
        Assert.assertTrue(map.containsValue(1));
        map.put("1", 3);
        Assert.assertTrue(map.size() == 1);
        Assert.assertTrue(map.containsKey("1"));
        Assert.assertTrue(map.containsValue(3));
        Assert.assertFalse(map.containsValue(1));
        map.put("2", 3);
        Assert.assertTrue(map.size() == 2);
        Assert.assertTrue(map.containsKey("2"));
        Assert.assertTrue(map.containsValue(3));
        Integer value = map.get("1");
        Assert.assertTrue(map.size() == 2);
        Assert.assertEquals(Integer.valueOf(3), value);
        Assert.assertTrue(map.containsKey("1"));
        Assert.assertTrue(map.containsValue(3));
        Assert.assertTrue(map.containsKey("2"));
        map.put("0", 4);
        map.remove("0");
        map.put("0", 4);
        map.remove("0");
        Integer removedValue = map.remove("1");
        Assert.assertTrue(map.size() == 1);
        Assert.assertEquals(Integer.valueOf(3), removedValue);
        Assert.assertFalse(map.containsKey("1"));
        Assert.assertTrue(map.containsValue(3));
        Assert.assertTrue(map.containsKey("2"));
        Assert.assertFalse(map.isEmpty());
        removedValue = map.remove("2");
        Assert.assertTrue(map.size() == 0);
        Assert.assertEquals(Integer.valueOf(3), removedValue);
        Assert.assertFalse(map.containsKey("1"));
        Assert.assertFalse(map.containsValue(3));
        Assert.assertFalse(map.containsKey("2"));
        Assert.assertTrue(map.isEmpty());
    }
}
