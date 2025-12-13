package com.mihneacristian.project_tracker.RestControllers;

import com.mihneacristian.project_tracker.DTO.TypeDTO;
import com.mihneacristian.project_tracker.Services.TypeService;
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
public class TypeControllerTest {

    @Mock
    private TypeService typeService;

    @InjectMocks
    private TypeController typeController;

    private TypeDTO typeDTO;

    @BeforeEach
    void setUp() {
        typeDTO = new TypeDTO();
        typeDTO.typeId = 1;
        typeDTO.typeName = "Bug";
    }

    @Test
    void testGetAllTypes() {
        // Arrange
        List<TypeDTO> typeDTOs = Arrays.asList(typeDTO);
        when(typeService.getAllTypes()).thenReturn(typeDTOs);

        // Act
        ResponseEntity<List<TypeDTO>> response = typeController.getAllTypes();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("Bug", response.getBody().get(0).typeName);
        verify(typeService, times(1)).getAllTypes();
    }
}