package com.mihneacristian.project_tracker.Entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemEntityTest {

    @Test
    void testItemGettersAndSetters() {
        // Arrange
        Item item = new Item();
        Status status = new Status("Open");
        Type type = new Type("Bug");
        TeamMembers member = new TeamMembers();

        // Act
        item.setItemId(1);
        item.setTitle("Test Item");
        item.setDescription("Description");
        item.setStatusOfItem(status);
        item.setTypeOfItem(type);
        item.setTeamMemberOfItem(member);

        // Assert
        assertEquals(1, item.getItemId());
        assertEquals("Test Item", item.getTitle());
        assertEquals("Description", item.getDescription());
        assertEquals(status, item.getStatusOfItem());
        assertEquals(type, item.getTypeOfItem());
        assertEquals(member, item.getTeamMemberOfItem());
    }

    @Test
    void testItemToString() {
        // Arrange
        Item item = new Item();
        item.setItemId(1);
        item.setTitle("Test");

        // Act
        String result = item.toString();

        // Assert
        assertTrue(result.contains("Test"));
        assertTrue(result.contains("1"));
    }
}