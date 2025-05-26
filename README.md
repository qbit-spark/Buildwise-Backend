# BuildWise API Documentation Guide 

## Backend API Testing Information
For testing the API endpoints, you can use the Postman or Insomnia collection available at:
[Build_Wise_Backend_Endpoints_Collections](https://github.com/qbit-spark/Buildwise-Backend/)

BuildWise Backend Endpoints Collection

This collection includes all necessary requests to test authentication, project management, and user management endpoints. Import it into Postman or Insomnia to get started quickly.

---
🚀 Overview
The Project Management System Backend API supports:

- Authentication: JWT-based login with role-based access (ORG_ADMIN, PROJECT_MANAGER, TEAM_MEMBER).
- Project Management: Create and manage projects through a 7-stage workflow.
- User Management: Manage user profiles, timezones, and notifications.
- Real-time Updates: WebSocket events for project and notification updates.
- This guide focuses on setting up the backend, authenticating users, and creating projects.

### Prerequisites
Before you begin:
- Install Java (JDK 17 or higher)
- Set up PostgreSQL (v15 or higher) for database
- Install Redis for session management
- Have Insomnia for API testing
- Configure Qbit SMS and Qbit Email API credentials
- Prepare environment variables (see below)

### Step 1: Authentication Setup
1. Register a User
2. Open Insomnia or Postman and import the provided collection.
3. Send a POST request to /api/auth/register with:
```json
{
  "email": "user@example.com",
  "password": "YourSecurePassword123",
  "firstName": "John",
  "lastName": "Doe",
  "role": "TEAM_MEMBER",
  "timezone": "Africa/Nairobi"
}
```

### Step 2: Log In
1. Send a POST request to /api/auth/login with:
```json
{
  "email": "user@example.com",
  "password": "YourSecurePassword123"
}
```

2. Save the accessToken and refreshToken from the response.

### Step 3: If 2FA is enabled, verify with a POST request to /api/auth/verify-2fa:
```json
{
"email": "user@example.com",
"code": "123456"
}
```

### Step 4: Enable Two-Factor Authentication
- Send a POST request to /api/auth/enable-2fa with the JWT token in the Authorization header (Bearer <accessToken>).
- Choose SMS or Email for 2FA and follow the verification steps.

### Creating a Project
## Step 1: Create a New Project
1. Send a POST request to /api/projects with the JWT token in the Authorization header.
2. Use the example below of request body:

```json
{
  "name": "New Office Build",
  "description": "Construction of a new office space",
  "type": "CONSTRUCTION",
  "startDate": "2025-06-01T00:00:00Z",
  "expectedEndDate": "2025-12-01T00:00:00Z",
  "priority": "HIGH"
}
```

3. Copy the projectId from the response.

## Step 2: Assign Team Members
1. Send a POST request to /api/projects/:projectId/team with:
```json
{
  "userId": "<user-id>",
  "role": "PROJECT_MANAGER"
}
```

## Step 3: Manage Project Budget
1. Send a POST request to /api/projects/{projectId}/budget with:
```json
{
  "totalBudget": 100000,
  "currency": "USD",
  "budgetBreakdown": [
    {
      "category": "Materials",
      "amount": 40000,
      "description": "Construction materials"
    }
  ]
}
```

## Step 4: Approve Project (ORGANIZATION_ADMIN only)
1. Send a POST request to /api/projects/{projectId}/approve with:
```json
{
  "comments": "Approved for construction",
  "approvedBy": "<admin-user-id>"
}
```


### Key API Endpoints
 ## Authentication
  - POST /api/auth/register - Register a new user
  - POST /api/auth/verify-account - Verify account with code
  - POST /api/auth/login - Log in and get JWT tokens
  - POST /api/auth/refresh - Refresh JWT token
  - POST /api/auth/logout - Log out
  - POST /api/auth/forgot-password - Request password reset
  - POST /api/auth/reset-password - Reset password
  - POST /api/auth/enable-2fa - Enable 2FA

 ## User Management
  - PUT /api/users/profile - Update user profile
  - PUT /api/users/password - Change password
  - PUT /api/users/deactivate - Deactivate account
  - GET /api/users/notifications - View security notifications

 ## Project Management
  - POST /api/projects - Create a project
  - GET /api/projects/{id} - View project details
  - POST /api/projects/{id}/team - Assign team members
  - POST /api/projects/{id}/budget - Manage budget
  - POST /api/projects/{id}/approve - Approve project
For a full list, import the Insomnia or Postman collection.

### Security Features
- Password Policy: Enforces strong passwords (min 8 characters, mixed case, numbers, symbols).
- Account Lockout: Locks account after 5 failed login attempts for 15 minutes.
- Session Management: Limits to 3 concurrent sessions per user.
- Audit Logging: Tracks security events (login, role changes, project approvals).
- Rate Limiting: 5 login attempts per 15 minutes, 100 API requests per 15 minutes per IP.
- Device Fingerprinting: Monitors user devices for suspicious activity.


### Troubleshooting

## Setup Issues
- **Database connection failed**: Verify PostgreSQL is running and credentials in application.properties are correct.
- **Redis not connecting**: Ensure Redis is active (redis-server).
- **Qbit API errors**: Check API keys and internet connection.

## Authentication Issues
- **Invalid JWT token**: Include Bearer <token> in the Authorization header.
- **Account locked**: Wait 15 minutes or use /api/auth/forgot-password.
- **2FA code invalid**: Ensure phone/email is correct and retry.

## Project Issues
- **Cannot create project**: Verify user role (NORMAL_USER may need ORGANIZATION_ADMIN approval).
- **Budget update fails**: Ensure all required fields are provided.

### Important Tips
- **Security**: Use strong JWT secrets and Qbit API keys. Enable 2FA for all users.
- **Timezone**: All timestamps are in UTC for consistency.
- **Testing**: Use mvn test to run unit and integration tests.

[//]: # (API Documentation: Access Swagger docs at http://localhost:8080/swagger-ui.html.)


