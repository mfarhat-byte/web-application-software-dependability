package com.mihneacristian.project_tracker.DTO;

import com.mihneacristian.project_tracker.Entities.TeamMembers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TeamMembersDTOTest {

    @Test
    void testTeamMembersDTOConstructor_WithEntity() {
        // Arrange
        TeamMembers member = new TeamMembers();
        member.setMemberId(1);
        member.setFirstName("John");
        member.setLastName("Doe");
        member.setEmailAddress("john.doe@example.com");

        // Act
        TeamMembersDTO dto = new TeamMembersDTO(member);

        // Assert
        assertEquals(1, dto.teamMemberid);
        assertEquals("John", dto.teamMemberFirstName);
        assertEquals("Doe", dto.teamMemberLastName);
        assertEquals("john.doe@example.com", dto.teamMemberEmailAddress);
    }

    @Test
    void testTeamMembersDTODefaultConstructor() {
        // Act
        TeamMembersDTO dto = new TeamMembersDTO();

        // Assert
        assertNotNull(dto);
        assertEquals(0, dto.teamMemberid);
    }

    @Test
    void testTeamMembersDTOParameterizedConstructor() {
        // Act
        TeamMembersDTO dto = new TeamMembersDTO(1, "Doe", "John", "john@example.com");

        // Assert
        assertEquals(1, dto.teamMemberid);
        assertEquals("Doe", dto.teamMemberLastName);
        assertEquals("John", dto.teamMemberFirstName);
        assertEquals("john@example.com", dto.teamMemberEmailAddress);
    }

    @Test
    void testTeamMembersDTOGettersAndSetters() {
        // Arrange
        TeamMembersDTO dto = new TeamMembersDTO();

        // Act
        dto.setTeamMemberid(2);
        dto.setTeamMemberFirstName("Jane");
        dto.setTeamMemberLastName("Smith");
        dto.setTeamMemberEmailAddress("jane@example.com");

        // Assert
        assertEquals(2, dto.getTeamMemberid());
        assertEquals("Jane", dto.getTeamMemberFirstName());
        assertEquals("Smith", dto.getTeamMemberLastName());
        assertEquals("jane@example.com", dto.getTeamMemberEmailAddress());
    }

    @Test
    void testTeamMembersDTOToString() {
        // Arrange
        TeamMembersDTO dto = new TeamMembersDTO(1, "Doe", "John", "john@example.com");

        // Act
        String result = dto.toString();

        // Assert
        assertTrue(result.contains("John"));
        assertTrue(result.contains("Doe"));
        assertTrue(result.contains("john@example.com"));
    }
}
