package com.poseidon.cis3760.spring.template.contaminants;

import com.poseidon.cis3760.spring.template.contaminants.dao.ContaminantsDataDao;
import com.poseidon.cis3760.spring.template.contaminants.models.ContaminantsData;
import com.poseidon.cis3760.spring.template.contaminants.models.ContaminantsMinMaxData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ContaminantsDataServiceImplTest {

    @Mock
    private ContaminantsDataDao contaminantsDataDao;

    @InjectMocks
    private ContaminantsDataServiceImpl contaminantsDataService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetContaminantsData() {
        // Given
        ContaminantsData data = new ContaminantsData();
        data.setId(1);
        when(contaminantsDataDao.findById(1)).thenReturn(Optional.of(data));

        // When
        ContaminantsData result = contaminantsDataService.getContaminantsData(1);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(contaminantsDataDao, times(1)).findById(1);
    }

    @Test
    public void testMinmaxContaminantsData() {
        // Given
        ContaminantsData dataMax = new ContaminantsData();
        dataMax.setId(1);
        dataMax.setSampleDate("01");
        dataMax.setComponentType("MAXIMUM");
        dataMax.setValue(1.0);

        ContaminantsData dataMin = new ContaminantsData();
        dataMin.setId(2);
        dataMin.setSampleDate(dataMax.getSampleDate());
        dataMin.setComponentType("MINIMUM");
        dataMin.setValue(3.0);

        ContaminantsData dataAvg = new ContaminantsData();
        dataAvg.setId(3);
        dataAvg.setSampleDate(dataMax.getSampleDate());
        dataAvg.setComponentType("AVERAGE");
        dataAvg.setValue(2.0);

        when(contaminantsDataDao.getMinMaxContaminant("test_sector", "test_contaminant")).thenReturn(Arrays.asList(dataMax, dataMin, dataAvg));

        // When
        Iterable<ContaminantsMinMaxData> result = contaminantsDataService.minmaxContaminantsData("test_sector", "test_contaminant");
        ContaminantsMinMaxData firstResult = result.iterator().next();

        // Then
        assertNotNull(result);
        assertEquals(1, ((Collection<?>) result).size());
        assertEquals(dataMin.getValue(), firstResult.getMinimum());
        assertEquals(dataMax.getValue(), firstResult.getMaximum());
        assertEquals(dataAvg.getValue(), firstResult.getAverage());
        verify(contaminantsDataDao, times(1)).getMinMaxContaminant("test_sector", "test_contaminant");
    }

    @Test
    public void testAllContaminantsData() {
        // Given
        ContaminantsData data1 = new ContaminantsData();
        data1.setId(1);
        ContaminantsData data2 = new ContaminantsData();
        data2.setId(2);
        when(contaminantsDataDao.findAll()).thenReturn(Arrays.asList(data1, data2));

        // When
        Iterable<ContaminantsData> result = contaminantsDataService.allContaminantsData();

        // Then
        assertNotNull(result);
        assertEquals(2, ((Collection<?>) result).size());
        verify(contaminantsDataDao, times(1)).findAll();
    }

    @Test
    public void testCount() {
        // Given
        when(contaminantsDataDao.getCount()).thenReturn(5);

        // When
        Integer count = contaminantsDataService.count();

        // Then
        assertEquals(5, count);
        verify(contaminantsDataDao, times(1)).getCount();
    }
}
