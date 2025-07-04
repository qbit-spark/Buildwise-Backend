package com.qbitspark.buildwisebackend.projectmng_service.service;

import com.qbitspark.buildwisebackend.globeadvice.exceptions.ItemNotFoundException;
import com.qbitspark.buildwisebackend.projectmng_service.payloads.*;
import org.springframework.data.domain.Page;
import java.util.UUID;

public interface ProjectService {
    ProjectResponse createProject(ProjectCreateRequest request, UUID creatorMemberId, UUID organisationId) throws ItemNotFoundException;
    ProjectResponse getProjectById(UUID projectId, UUID requesterId) throws ItemNotFoundException;
    ProjectResponse updateProject(UUID projectId, UUID organisationId, ProjectUpdateRequest request, UUID updaterMemberId) throws ItemNotFoundException;
    String deleteProject(UUID projectId, UUID deleterMemberId) throws ItemNotFoundException;
    Page<ProjectListResponse> getOrganisationProjects(UUID organisationId, UUID requesterId, int page, int size, String sortBy, String sortDirection) throws ItemNotFoundException;
    Page<ProjectListResponse> getAllProjects(int page, int size, String sortBy, String sortDirection);
    Page<ProjectListResponse> searchProjects(ProjectSearchRequest searchRequest, UUID requesterId) throws ItemNotFoundException;
    ProjectResponse updateProjectTeam(UUID projectId, ProjectTeamUpdateRequest request, UUID updaterMemberId) throws ItemNotFoundException;
    ProjectResponse removeTeamMember(UUID projectId, UUID memberToRemoveId, UUID removerMemberId) throws ItemNotFoundException;
    Page<ProjectListResponse> getMemberProjects(UUID memberId, int page, int size) throws ItemNotFoundException;
    ProjectStatisticsResponse getProjectStatistics(UUID organisationId, UUID requesterId) throws ItemNotFoundException;
}