package com.mihneacristian.project_tracker.RestControllers;

import com.mihneacristian.project_tracker.DTO.TeamMembersDTO;
import com.mihneacristian.project_tracker.Entities.TeamMembers;
import com.mihneacristian.project_tracker.Services.TeamMembersService;
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
public class TeamMembersControllerTest {

    @Mock
    private TeamMembersService teamMembersService;

    @InjectMocks
    private TeamMembersController teamMembersController;

    private TeamMembersDTO memberDTO;
    private TeamMembers member;

    @BeforeEach
    void setUp() {
        member = new TeamMembers();
        member.setMemberId(1);
        member.setFirstName("John");
        member.setLastName("Doe");
        member.setEmailAddress("john.doe@example.com");

        memberDTO = new TeamMembersDTO();
        memberDTO.teamMemberid = 1;
        memberDTO.teamMemberFirstName = "John";
        memberDTO.teamMemberLastName = "Doe";
        memberDTO.teamMemberEmailAddress = "john.doe@example.com";
    }

    @Test
    void testGetAllMembers() {
        // Arrange
        List<TeamMembersDTO> memberDTOs = Arrays.asList(memberDTO);
        when(teamMembersService.getAllMembers()).thenReturn(memberDTOs);

        // Act
        ResponseEntity<List<TeamMembersDTO>> response = teamMembersController.getAllMembers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(teamMembersService, times(1)).getAllMembers();
    }

    @Test
    void testGetTeamMemberByFirstName_Success() {
        // Arrange
        when(teamMembersService.findByFirstName("John")).thenReturn(member);

        // Act
        ResponseEntity<TeamMembersDTO> response = teamMembersController.getTeamMemberByFirstName("John");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("John", response.getBody().teamMemberFirstName);
    }

    @Test
    void testGetTeamMemberByFirstName_NotFound() {
        // Arrange
        when(teamMembersService.findByFirstName("Unknown")).thenReturn(null);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> {
            teamMembersController.getTeamMemberByFirstName("Unknown");
        });
    }

    @Test
    void testGetTeamMemberByLastName_Success() {
        // Arrange
        when(teamMembersService.findByLastName("Doe")).thenReturn(member);

        // Act
        ResponseEntity<TeamMembersDTO> response = teamMembersController.getTeamMemberByLastName("Doe");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testGetTeamMemberByLastName_NotFound() {
        // Arrange
        when(teamMembersService.findByLastName("Unknown")).thenReturn(null);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> {
            teamMembersController.getTeamMemberByLastName("Unknown");
        });
    }

    @Test
    void testGetTeamMemberByEmailAddress_Success() {
        // Arrange
        when(teamMembersService.findByEmailAdddress("john.doe@example.com")).thenReturn(member);

        // Act
        ResponseEntity<TeamMembersDTO> response = teamMembersController.getTeamMemberByEmailAddress("john.doe@example.com");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testGetTeamMemberByEmailAddress_NotFound() {
        // Arrange
        when(teamMembersService.findByEmailAdddress("unknown@example.com")).thenReturn(null);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> {
            teamMembersController.getTeamMemberByEmailAddress("unknown@example.com");
        });
    }

    @Test
    void testCreateMember() {
        // Arrange
        when(teamMembersService.saveTeamMember(any(TeamMembersDTO.class))).thenReturn(member);

        // Act
        ResponseEntity<TeamMembers> response = teamMembersController.createMember(memberDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(teamMembersService, times(1)).saveTeamMember(any(TeamMembersDTO.class));
    }

    @Test
    void testDeleteMemberById() {
        // Arrange
        doNothing().when(teamMembersService).deleteTeamMemberById(1);

        // Act
        teamMembersController.deleteMemberById(1);

        // Assert
        verify(teamMembersService, times(1)).deleteTeamMemberById(1);
    }

    @Test
    void testUpdateMemberById_Success() {
        // Arrange
        when(teamMembersService.getMemberById(1)).thenReturn(member);
        when(teamMembersService.updateMemberById(eq(1), any(TeamMembersDTO.class))).thenReturn(member);

        // Act
        ResponseEntity<TeamMembers> response = teamMembersController.updateMemberById(1, memberDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(teamMembersService, times(1)).updateMemberById(eq(1), any(TeamMembersDTO.class));
    }

    @Test
    void testUpdateMemberById_NotFound() {
        // Arrange
        when(teamMembersService.getMemberById(999)).thenReturn(null);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> {
            teamMembersController.updateMemberById(999, memberDTO);
        });
    }
}
