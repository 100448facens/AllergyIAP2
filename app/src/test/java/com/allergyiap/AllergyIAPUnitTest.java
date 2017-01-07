package com.allergyiap;

import com.allergyiap.service.AllergyService;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AllergyIAPUnitTest {
    @Test
    public void dbTest() throws Exception {
        System.out.println(AllergyService.getAll());
    }
}