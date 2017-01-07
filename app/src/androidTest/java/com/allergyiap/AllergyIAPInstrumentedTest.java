package com.allergyiap;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.allergyiap.db.DB;
import com.allergyiap.service.AllergyLevelService;
import com.allergyiap.service.AllergyService;
import com.allergyiap.service.CustomerService;
import com.allergyiap.service.PharmacyService;
import com.allergyiap.service.ProductCatalogService;
import com.allergyiap.service.RelationPharmaciesCustomersService;
import com.allergyiap.service.StationService;
import com.allergyiap.service.UserAllergyService;
import com.allergyiap.service.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class AllergyIAPInstrumentedTest {
    @Test
    public void AllergyService() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        DB.setCurrentContext(appContext);
        assertNotEquals(0, AllergyService.getAll().size());
    }
    @Test
    public void AllergyLevelService() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        DB.setCurrentContext(appContext);
        assertNotEquals(0, AllergyLevelService.getAll().size());
    }
    @Test
    public void CustomerService() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        DB.setCurrentContext(appContext);
        assertNotEquals(0, CustomerService.getAll().size());
    }
    @Test
    public void PharmacyService() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        DB.setCurrentContext(appContext);
        assertNotEquals(0, PharmacyService.getAll().size());
    }
    @Test
    public void ProductCatalogService() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        DB.setCurrentContext(appContext);
        assertNotEquals(0, ProductCatalogService.getAll().size());
    }
    @Test
    public void RelationPharmaciesCustomersService() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        DB.setCurrentContext(appContext);
        assertNotEquals(0, RelationPharmaciesCustomersService.getAll().size());
    }
    @Test
    public void StationService() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        DB.setCurrentContext(appContext);
        assertNotEquals(0, StationService.getAll().size());
    }
    @Test
    public void UserAllergyService() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        DB.setCurrentContext(appContext);
        assertNotEquals(0, UserAllergyService.getAll().size());
    }
    @Test
    public void UserService() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        DB.setCurrentContext(appContext);
        assertNotEquals(0, UserService.getAll().size());
    }
}
