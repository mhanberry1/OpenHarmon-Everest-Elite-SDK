/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author rhotate
 */
public class ANCAwarenessPresetNGTest {
    
    public ANCAwarenessPresetNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of values method, of class ANCAwarenessPreset.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        ANCAwarenessPreset[] expResult = null;
        ANCAwarenessPreset[] result = ANCAwarenessPreset.values();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class ANCAwarenessPreset.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "";
        ANCAwarenessPreset expResult = null;
        ANCAwarenessPreset result = ANCAwarenessPreset.valueOf(name);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of from method, of class ANCAwarenessPreset.
     */
    @Test
    public void testFrom() {
        System.out.println("from");
        int value = 0;
        ANCAwarenessPreset expResult = null;
        ANCAwarenessPreset result = ANCAwarenessPreset.from(value);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of value method, of class ANCAwarenessPreset.
     */
    @Test
    public void testValue() {
        System.out.println("value");
        ANCAwarenessPreset instance = null;
        int expResult = 0;
        int result = instance.value();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
