package com.poseidon.cis3760.spring.template.contaminants;

// import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



// TODO: remove
import static org.junit.jupiter.api.Assertions.assertNotNull;

// @TestPropertySource("classpath:application-test.properties")
public class IntegrationTest {

    // /api/data/contaminants/all
    @Test
    public void test_All() {
        // TODO: Simulate request
        // TODO: Compare JSON response to expected
        assertNotNull(1);
    }

    // /api/data/contaminants/count
    @Test
    public void test_ContaminantsCount() {
        // TODO: Simulate request
        // TODO: Compare JSON response to expected
        assertNotNull(1);
    }

    // /api/data/contaminants/get/{id}
    @Test
    public void test_ContaminantsGetId_BadId() {
        // TODO: simulate request where id does not exist
        // TODO: Compare JSON response to expected
        assertNotNull(1);
    }
    @Test
    public void test_ContaminantsGetId_Good() {
        // TODO: simulate request where id exists
        // TODO: Compare JSON response to expected
        assertNotNull(1);
    }

    // /api/data/contaminants/{sector}/{contaminant}/minmax
    @Test
    public void test_ContaminantsMinMax_BadSector() {
        // TODO: Simulate request where sector does not exist
        // TODO: Compare JSON response to expected
        assertNotNull(1);
    }
    @Test
    public void test_ContaminantsMinMax_BadContaminant() {
        // TODO: Simulate request where contaminant does not exist
        // TODO: Compare JSON response to expected
        assertNotNull(1);
    }
    @Test
    public void test_ContaminantsMinMax_Good() {
        // TODO: Simulate request where there are multiple records with the specified sector and contaminant
        // TODO: Compare JSON response to expected
        assertNotNull(1);
    }

    
    // /api/data/sewage/count
    @Test
    public void test_SewageCount() {
        // TODO: Simulate request
        // TODO: Compare JSON response to expected
        assertNotNull(1);
    }

    // /api/data/sewage/all
    @Test
    public void test_SewageAll() {
        // TODO: Simulate request
        // TODO: Compare JSON response to expected
        assertNotNull(1);
    }
    
    // /api/data/sewage/get/{id}
    @Test
    public void test_SewageGetId_BadId() {
        // TODO: Simulate request where id does not exist
        // TODO: Compare JSON response to expected
        assertNotNull(1);
    }
    @Test
    public void test_SewageGetId_Good() {
        // TODO: Simulate request where id exists
        // TODO: Compare JSON response to expected
        assertNotNull(1);
    }

    // /api/data/sewage/{sector}/{contaminant}
    @Test
    public void test_SewageSectorContaminant_BadSector() {
        // TODO: Simulate request where sector does not exist
        // TODO: Compare JSON response to expected
        assertNotNull(1);
    }
    @Test
    public void test_SewageSectorContaminant_BadContaminant() {
        // TODO: Simulate request where contaminant does not exist
        // TODO: Compare JSON response to expected
        assertNotNull(1);
    }
    @Test
    public void test_SewageSectorContaminant_Good() {
        // TODO: Simulate request where there are multiple records for the specified sector and contaminant
        // TODO: Compare JSON response to expected
        assertNotNull(1);
    }
    
    // /api/data/sewage/{sector}/allcontaminants
    @Test
    public void test_SewageSectorAllcontaminants_BadSector() {
        // TODO: Simulate request where the sector does not exist
        // TODO: Compare JSON response to expected
        assertNotNull(1);
    }
    public void test_SewageSectorAllcontaminants_Good() {
        // TODO: Simulate request where the sector has multiple exceedances across multiple records
        // TODO: Compare JSON response to expected
        assertNotNull(1);
    }

}
