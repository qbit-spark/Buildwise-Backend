package com.qbitspark.buildwisebackend.organisation_service.organisation_mng.payloads;

import lombok.Data;

import java.util.UUID;

@Data
public class OrganisationResponse {
    private String organisationId;
    private String organisationName;
    private UUID ownerId;
    private String ownerUserName;
    private String description;
    private boolean isActive;
    private boolean isDeleted;
    private String createdAt;
    private String updatedAt;
}
