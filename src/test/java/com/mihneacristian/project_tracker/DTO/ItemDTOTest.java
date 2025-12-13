package com.mihneacristian.project_tracker.DTO;

import com.mihneacristian.project_tracker.Entities.Item;
import com.mihneacristian.project_tracker.Entities.Status;
import com.mihneacristian.project_tracker.Entities.TeamMembers;
import com.mihneacristian.project_tracker.Entities.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemDTOTest {

    private Item item;
    private Status status;
    private TeamMembers teamMember;
    private Type type;

    @BeforeEach
    void setUp() {
        status = new Status("Open");
        status.setStatusId(1);

        type = new Type("Bug");
        type.setTypeId(1);

        teamMember = new TeamMembers();
        teamMember.setMemberId(1);
        teamMember.setFirstName("John");
        teamMember.setLastName("Doe");
        teamMember.setEmailAddress("john.doe@example.com");

        item = new Item();
        item.setItemId(1);
        item.setTitle("Test Item");
        item.setDescription("Test Description");
        item.setStatusOfItem(status);
        item.setTypeOfItem(type);
        item.setTeamMemberOfItem(teamMember);
    }

    @Test
    void testItemDTOConstructor_WithValidItem() {
        // Act
        ItemDTO dto = new ItemDTO(item);

        // Assert
        assertEquals(1, dto.itemId);
        assertEquals("Test Item", dto.title);
        assertEquals("Test Description", dto.description);
        assertEquals("Open", dto.statusOfItem);
        assertEquals("Bug", dto.typeOfItem);
        assertEquals(1, dto.teamMemberId);
        assertEquals("John", dto.teamMemberOfProjectFirstName);
        assertEquals("Doe", dto.teamMemberOfProjectLastName);
        assertEquals("john.doe@example.com", dto.teamMemberOfProjectEmailAddress);
    }

    @Test
    void testItemDTODefaultConstructor() {
        // Act
        ItemDTO dto = new ItemDTO();

        // Assert
        assertNotNull(dto);
        assertEquals(0, dto.itemId);
        assertNull(dto.title);
    }

    @Test
    void testItemDTOGettersAndSetters() {
        // Arrange
        ItemDTO dto = new ItemDTO();

        // Act
        dto.setItemId(2);
        dto.setTitle("New Item");
        dto.setDescription("New Description");
        dto.setStatusOfItem("Closed");
        dto.setTypeOfItem("Feature");
        dto.setTeamMemberId(2);
        dto.setTeamMemberOfProjectFirstName("Jane");
        dto.setTeamMemberOfProjectLastName("Smith");
        dto.setTeamMemberOfProjectEmailAddress("jane@example.com");

        // Assert
        assertEquals(2, dto.getItemId());
        assertEquals("New Item", dto.getTitle());
        assertEquals("New Description", dto.getDescription());
        assertEquals("Closed", dto.getStatusOfItem());
        assertEquals("Feature", dto.getTypeOfItem());
        assertEquals(2, dto.getTeamMemberId());
        assertEquals("Jane", dto.getTeamMemberOfProjectFirstName());
        assertEquals("Smith", dto.getTeamMemberOfProjectLastName());
        assertEquals("jane@example.com", dto.getTeamMemberOfProjectEmailAddress());
    }

    @Test
    void testItemDTOToString() {
        // Arrange
        ItemDTO dto = new ItemDTO(item);

        // Act
        String result = dto.toString();

        // Assert
        assertTrue(result.contains("Test Item"));
        assertTrue(result.contains("Open"));
        assertTrue(result.contains("Bug"));
    }
}
