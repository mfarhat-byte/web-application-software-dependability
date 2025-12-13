package com.mihneacristian.project_tracker.RestControllers;

import com.mihneacristian.project_tracker.DTO.ItemDTO;
import com.mihneacristian.project_tracker.Entities.Item;
import com.mihneacristian.project_tracker.Entities.Status;
import com.mihneacristian.project_tracker.Entities.TeamMembers;
import com.mihneacristian.project_tracker.Entities.Type;
import com.mihneacristian.project_tracker.Services.ItemService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemControllerTest {

    @Mock
    private ItemService itemService;

    @InjectMocks
    private ItemController itemController;

    private ItemDTO itemDTO;
    private Item item;

    @BeforeEach
    void setUp() {
        item = new Item();
        item.setItemId(1);
        item.setTitle("Test Item");
        item.setDescription("Test Description");

        itemDTO = new ItemDTO();
        itemDTO.itemId = 1;
        itemDTO.title = "Test Item";
        itemDTO.description = "Test Description";
    }

    @Test
    void testGetAllItems() {
        List<ItemDTO> itemDTOs = Arrays.asList(itemDTO);
        when(itemService.getAllItems()).thenReturn(itemDTOs);

        ResponseEntity<List<ItemDTO>> response = itemController.getAllItems();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(itemService, times(1)).getAllItems();
    }

    @Test
    void testGetItemById_Success() {
        Status status = new Status("Open");
        Type type = new Type("Bug");

        TeamMembers teamMember = new TeamMembers();
        teamMember.setMemberId(1);
        teamMember.setFirstName("John");
        teamMember.setLastName("Doe");
        teamMember.setEmailAddress("john@example.com");

        item.setStatusOfItem(status);
        item.setTypeOfItem(type);
        item.setTeamMemberOfItem(teamMember);

        when(itemService.findByItemId(1)).thenReturn(item);

        ResponseEntity<ItemDTO> response = itemController.getItemById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(itemService, times(1)).findByItemId(1);
    }


    @Test
    void testGetItemById_NotFound() {
        when(itemService.findByItemId(999)).thenReturn(null);

        assertThrows(ResponseStatusException.class, () -> {
            itemController.getItemById(999);
        });
    }

    @Test
    void testCreateItem() {
        when(itemService.saveNewItem(any(ItemDTO.class))).thenReturn(item);

        ResponseEntity<Item> response = itemController.createItem(itemDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(itemService, times(1)).saveNewItem(any(ItemDTO.class));
    }

    @Test
    void testUpdateItemById_Success() {
        when(itemService.findByItemId(1)).thenReturn(item);
        when(itemService.updateItemById(eq(1), any(ItemDTO.class))).thenReturn(item);

        ResponseEntity<Item> response = itemController.updateProjectById(1, itemDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(itemService, times(1)).updateItemById(eq(1), any(ItemDTO.class));
    }

    @Test
    void testUpdateItemById_NotFound() {
        when(itemService.findByItemId(999)).thenReturn(null);

        assertThrows(ResponseStatusException.class, () -> {
            itemController.updateProjectById(999, itemDTO);
        });
    }

    @Test
    void testDeleteItemById_Success() {
        when(itemService.isItemIdPresent(1)).thenReturn(true);
        doNothing().when(itemService).deleteItemById(1);

        itemController.deleteProjectById(1);

        verify(itemService, times(1)).deleteItemById(1);
    }

    @Test
    void testDeleteItemById_NotFound() {
        when(itemService.isItemIdPresent(999)).thenReturn(false);

        assertThrows(ResponseStatusException.class, () -> {
            itemController.deleteProjectById(999);
        });
    }
}
