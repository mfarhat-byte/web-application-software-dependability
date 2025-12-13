package com.mihneacristian.project_tracker.RestControllers;

import com.mihneacristian.project_tracker.DTO.ProjectDTO;
import com.mihneacristian.project_tracker.Entities.Project;
import com.mihneacristian.project_tracker.Entities.Status;
import com.mihneacristian.project_tracker.Entities.TeamMembers;
import com.mihneacristian.project_tracker.Services.ProjectService;

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
public class ProjectControllerTest {

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    private ProjectDTO projectDTO;
    private Project project;

    @BeforeEach
    void setUp() {
        project = new Project();
        project.setProjectId(1);
        project.setName("Test Project");
        project.setDescription("Test Description");

        projectDTO = new ProjectDTO();
        projectDTO.projectId = 1;
        projectDTO.projectName = "Test Project";
        projectDTO.description = "Test Description";
    }

    @Test
    void testGetAllProjects() {
        List<ProjectDTO> projectDTOs = Arrays.asList(projectDTO);
        when(projectService.getAllProjects()).thenReturn(projectDTOs);

        ResponseEntity<List<ProjectDTO>> response = projectController.getAllProjects();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(projectService, times(1)).getAllProjects();
    }

    @Test
    void testGetProjectById_Success() {
        Status status = new Status("InProgress");

        TeamMembers teamMember = new TeamMembers();
        teamMember.setMemberId(1);
        teamMember.setFirstName("John");
        teamMember.setLastName("Doe");
        teamMember.setEmailAddress("john@example.com");

        project.setStatusOfProject(status);
        project.setTeamMemberOfProject(teamMember);

        when(projectService.getProjectById(1)).thenReturn(project);

        ResponseEntity<ProjectDTO> response = projectController.getProjectById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(projectService, times(1)).getProjectById(1);
    }

    @Test
    void testGetProjectById_NotFound() {
        when(projectService.getProjectById(999)).thenReturn(null);

        assertThrows(ResponseStatusException.class, () -> {
            projectController.getProjectById(999);
        });
    }

    @Test
    void testCreateProject() {
        when(projectService.saveProject(any(ProjectDTO.class))).thenReturn(project);

        ResponseEntity<Project> response = projectController.createProject(projectDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(projectService, times(1)).saveProject(any(ProjectDTO.class));
    }

    @Test
    void testUpdateProjectById_Success() {
        when(projectService.getProjectById(1)).thenReturn(project);
        when(projectService.updateProjectById(eq(1), any(ProjectDTO.class))).thenReturn(project);

        ResponseEntity<Project> response = projectController.updateProjectById(1, projectDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(projectService, times(1)).updateProjectById(eq(1), any(ProjectDTO.class));
    }

    @Test
    void testUpdateProjectById_NotFound() {
        when(projectService.getProjectById(999)).thenReturn(null);

        assertThrows(ResponseStatusException.class, () -> {
            projectController.updateProjectById(999, projectDTO);
        });
    }

    @Test
    void testDeleteProjectById_Success() {
        when(projectService.isProjectIdPresent(1)).thenReturn(true);
        doNothing().when(projectService).deleteProjectById(1);

        projectController.deleteProjectById(1);

        verify(projectService, times(1)).deleteProjectById(1);
    }

    @Test
    void testDeleteProjectById_NotFound() {
        when(projectService.isProjectIdPresent(999)).thenReturn(false);

        assertThrows(ResponseStatusException.class, () -> {
            projectController.deleteProjectById(999);
        });
    }
}
