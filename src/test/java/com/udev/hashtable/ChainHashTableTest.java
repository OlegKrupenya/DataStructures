package com.udev.hashtable;

import com.udev.hashtable.ChainHashTable;
import com.udev.hashtable.IHashTable;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * User: oleg.krupenya
 * Date: 11/20/13
 * Time: 5:54 PM
 */
public class ChainHashTableTest {
    private IHashTable<String, Integer> map;

    @Before
    public void setUp() {
        this.map = new ChainHashTable<String, Integer>();
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
