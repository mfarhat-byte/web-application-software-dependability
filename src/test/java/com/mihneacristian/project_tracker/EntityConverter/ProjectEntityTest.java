package com.mihneacristian.project_tracker.Entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectEntityTest {

    @Test
    void testProjectGettersAndSetters() {
        // Arrange
        Project project = new Project();
        Status status = new Status("Open");
        TeamMembers member = new TeamMembers();

        // Act
        project.setProjectId(1);
        project.setName("Test Project");
        project.setDescription("Description");
        project.setStatusOfProject(status);
        project.setTeamMemberOfProject(member);

        // Assert
        assertEquals(1, project.getProjectId());
        assertEquals("Test Project", project.getName());
        assertEquals("Description", project.getDescription());
        assertEquals(status, project.getStatusOfProject());
        assertEquals(member, project.getTeamMemberOfProject());
    }

    @Test
    void testProjectToString() {
        // Arrange
        Project project = new Project();
        project.setProjectId(1);
        project.setName("Test");

        // Act
        String result = project.toString();

        // Assert
        assertTrue(result.contains("Test"));
        assertTrue(result.contains("1"));
    }
}