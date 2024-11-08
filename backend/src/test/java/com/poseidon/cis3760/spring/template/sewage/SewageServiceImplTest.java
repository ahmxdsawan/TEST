package com.poseidon.cis3760.spring.template.sewage;

import com.poseidon.cis3760.spring.template.sewage.dao.SewageDataDao;
import com.poseidon.cis3760.spring.template.sewage.models.SewageData;
import com.poseidon.cis3760.spring.template.sewage.models.ContaminantExceedances;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collection;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class SewageServiceImplTest {

    @Mock
    private SewageDataDao sewageDataDao;

    @InjectMocks
    private SewageServiceImpl sewageService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetSewageData() {
        // Given
        SewageData data = new SewageData();
        data.setId(1);
        data.setSectorDisplayName("Municipal");
        when(sewageDataDao.findById(1)).thenReturn(Optional.of(data));

        // When
        SewageData result = sewageService.getSewageData(1);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Municipal", result.getSectorDisplayName());
        verify(sewageDataDao, times(1)).findById(1);
    }

    @Test
    public void testAllNotes() {
        // Given
        SewageData data1 = new SewageData();
        data1.setId(1);
        data1.setSectorDisplayName("Industrial");
        SewageData data2 = new SewageData();
        data2.setId(2);
        data2.setSectorDisplayName("Commercial");
        when(sewageDataDao.findAll()).thenReturn(Arrays.asList(data1, data2));

        // When
        Iterable<SewageData> result = sewageService.allNotes();

        // Then
        assertNotNull(result);
        assertEquals(2, ((Collection<?>) result).size());
        verify(sewageDataDao, times(1)).findAll();
    }

    @Test
    public void testCount() {
        // Given
        when(sewageDataDao.getCount()).thenReturn(5);

        // When
        Integer count = sewageService.count();

        // Then
        assertEquals(5, count);
        verify(sewageDataDao, times(1)).getCount();
    }

    // Test the sector and contaminant method
    @Test
    public void testSectorContaminant() {
        // Given
        SewageData data1 = new SewageData();
        data1.setId(1);
        data1.setSectorDisplayName("Industrial");
        data1.setContaminantDisplayName("Ammonia");

        SewageData data2 = new SewageData();
        data2.setId(2);
        data2.setSectorDisplayName("Industrial");
        data2.setContaminantDisplayName("Ammonia");

        when(sewageDataDao.getSectorContaminant("Industrial", "Ammonia")).thenReturn(Arrays.asList(data1, data2));

        // When
        Iterable<SewageData> result = sewageService.sectorContaminant("Industrial", "Ammonia");

        // Then
        assertNotNull(result);
        assertEquals(2, ((Collection<?>) result).size());
        verify(sewageDataDao, times(1)).getSectorContaminant("Industrial", "Ammonia");
    }

    @Test
    public void testContaminantExceedances(){

        // Given
        SewageData data1 = new SewageData();
        data1.setId(1);
        data1.setSectorDisplayName("Industrial");
        data1.setContaminantDisplayName("Ammonia");
        data1.setNumExceedances(5);

        SewageData data2 = new SewageData();
        data2.setId(2);
        data2.setSectorDisplayName("Industrial");
        data2.setContaminantDisplayName("Ammonia");
        data2.setNumExceedances(4);

        SewageData data3 = new SewageData();
        data3.setId(1);
        data3.setSectorDisplayName("Industrial");
        data3.setContaminantDisplayName("Ammonia");
        data3.setNumExceedances(1);

        SewageData data4 = new SewageData();
        data4.setId(2);
        data4.setSectorDisplayName("Industrial");
        data4.setContaminantDisplayName("Sulphur");
        data4.setNumExceedances(3);

        SewageData data5 = new SewageData();
        data5.setId(2);
        data5.setSectorDisplayName("Industrial");
        data5.setContaminantDisplayName("Sulphur");
        data5.setNumExceedances(15);

        sewageDataDao.saveAll(Arrays.asList(data1, data2, data3, data4, data5));

        // When
        Iterable<ContaminantExceedances> results = sewageDataDao.getContaminantExceedances("Industrial");

        // Then
        for (ContaminantExceedances result : results) {
            if ("Ammonia".equals(result.getContaminant())) {
                assertEquals(10, result.getExceedances());
            } else if ("Sulphur".equals(result.getContaminant())) {
                assertEquals(18, result.getExceedances());
            }
        }


    }
}
