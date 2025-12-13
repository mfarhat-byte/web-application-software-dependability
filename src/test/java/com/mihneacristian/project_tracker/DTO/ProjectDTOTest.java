package com.mihneacristian.project_tracker.DTO;

import com.mihneacristian.project_tracker.Entities.Project;
import com.mihneacristian.project_tracker.Entities.Status;
import com.mihneacristian.project_tracker.Entities.TeamMembers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectDTOTest {

    private Project project;
    private Status status;
    private TeamMembers teamMember;

    @BeforeEach
    void setUp() {
        status = new Status("InProgress");
        status.setStatusId(1);

        teamMember = new TeamMembers();
        teamMember.setMemberId(1);
        teamMember.setFirstName("John");
        teamMember.setLastName("Doe");
        teamMember.setEmailAddress("john.doe@example.com");

        project = new Project();
        project.setProjectId(1);
        project.setName("Test Project");
        project.setDescription("Test Description");
        project.setStatusOfProject(status);
        project.setTeamMemberOfProject(teamMember);
    }

    @Test
    void testProjectDTOConstructor_WithValidProject() {
        // Act
        ProjectDTO dto = new ProjectDTO(project);

        // Assert
        assertEquals(1, dto.projectId);
        assertEquals("Test Project", dto.projectName);
        assertEquals("Test Description", dto.description);
        assertEquals("InProgress", dto.statusName);
        assertEquals(1, dto.teamMemberId);
        assertEquals("John", dto.teamMemberOfProjectFirstName);
        assertEquals("Doe", dto.teamMemberOfProjectLastName);
        assertEquals("john.doe@example.com", dto.teamMemberOfProjectEmailAddress);
    }

    @Test
    void testProjectDTOConstructor_WithNullStatus() {
        // Arrange
        project.setStatusOfProject(null);

        // Act
        ProjectDTO dto = new ProjectDTO(project);

        // Assert
        assertEquals("Undefined Status", dto.statusName);
    }

    @Test
    void testProjectDTODefaultConstructor() {
        // Act
        ProjectDTO dto = new ProjectDTO();

        // Assert
        assertNotNull(dto);
        assertEquals(0, dto.projectId);
        assertNull(dto.projectName);
    }

    @Test
    void testProjectDTOGettersAndSetters() {
        // Arrange
        ProjectDTO dto = new ProjectDTO();

        // Act
        dto.setProjectId(2);
        dto.setProjectName("New Project");
        dto.setDescription("New Description");
        dto.setStatusName("Open");
        dto.setTeamMemberId(2);
        dto.setTeamMemberOfProjectFirstName("Jane");
        dto.setTeamMemberOfProjectLastName("Smith");
        dto.setTeamMemberOfProjectEmailAddress("jane@example.com");

        // Assert
        assertEquals(2, dto.getProjectId());
        assertEquals("New Project", dto.getProjectName());
        assertEquals("New Description", dto.getDescription());
        assertEquals("Open", dto.getStatusName());
        assertEquals(2, dto.getTeamMemberId());
        assertEquals("Jane", dto.getTeamMemberOfProjectFirstName());
        assertEquals("Smith", dto.getTeamMemberOfProjectLastName());
        assertEquals("jane@example.com", dto.getTeamMemberOfProjectEmailAddress());
    }

    @Test
    void testProjectDTOToString() {
        // Arrange
        ProjectDTO dto = new ProjectDTO(project);

        // Act
        String result = dto.toString();

        // Assert
        assertTrue(result.contains("Test Project"));
        assertTrue(result.contains("InProgress"));
        assertTrue(result.contains("John"));
    }
}