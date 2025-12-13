package com.mihneacristian.project_tracker.RestControllers;

import com.mihneacristian.project_tracker.DTO.StatusDTO;
import com.mihneacristian.project_tracker.Services.StatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StatusControllerTest {

    @Mock
    private StatusService statusService;

    @InjectMocks
    private StatusController statusController;

    private StatusDTO statusDTO;

    @BeforeEach
    void setUp() {
        statusDTO = new StatusDTO();
        statusDTO.statusId = 1;
        statusDTO.statusName = "Open";
    }

    @Test
    void testGetAllStatus() {
        // Arrange
        List<StatusDTO> statusDTOs = Arrays.asList(statusDTO);
        when(statusService.getAllStatus()).thenReturn(statusDTOs);

        // Act
        ResponseEntity<List<StatusDTO>> response = statusController.getAllStatus();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("Open", response.getBody().get(0).statusName);
        verify(statusService, times(1)).getAllStatus();
    }
}
