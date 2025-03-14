# Installation

_The service provides resources for retrieving information about uninstalled installations within Sundsvall municipality._

## Getting Started

### Prerequisites

- **Java 21 or higher**
- **Maven**
- **MariaDB**
- **Git**
- **[Dependent Microservices](#dependencies)**

### Installation

1. **Clone the repository:**

```bash

git clone https://github.com/Sundsvallskommun/api-service-installation.git
cd api-service-installation
```

2. **Configure the application:**

   Before running the application, you need to set up configuration settings.
   See [Configuration](#configuration)

   **Note:** Ensure all required configurations are set; otherwise, the application may fail to start.

3. **Ensure dependent services are running:**

   If this microservice depends on other services, make sure they are up and accessible. See [Dependencies](#dependencies) for more details.

4. **Build and run the application:**

   ```bash
   mvn spring-boot:run
   ```

## Dependencies

This microservice depends on the following services:

- **DataWarehouseReader**
  - **Purpose:** Used for reading information about uninstalled installations.
  - **Repository:** [https://github.com/Sundsvallskommun/api-service-datawarehousereader](https://github.com/Sundsvallskommun/api-service-datawarehousereader)
  - **Setup Instructions:** See documentation in repository above for installation and configuration steps.

Ensure that these services are running and properly configured before starting this microservice.

## API Documentation

See `openapi.yaml` located in directory `src\test\resources\api` or access the API documentation via Swagger UI:

- **Swagger UI:** [http://localhost:8080/api-docs](http://localhost:8080/api-docs)

## Usage

### API Endpoints

See the [API Documentation](#api-documentation) for detailed information on available endpoints.

### Example Request

```bash
curl -X GET https://installation-af-installation.apps.ocp101.sundsvall.se/2281/installations?category=ELECTRICITY&facilityId=12345&installed=true&dateFrom=2022-01-01&sortDirection=ASC&page=1&limit=15
```

## Configuration

Configuration is crucial for the application to run successfully. Ensure all necessary settings are configured in `application.yml`.

### Key Configuration Parameters

- **Server Port:**

```yaml
server:
  port: 8080
```

- **External Service URLs**

```yaml
  integration:
    datawarehousereader:
      url: <service-url>

  spring:
    security:
      oauth2:
        client:
          registration:
            datawarehousereader:
              client-id: <client-id>
              client-secret: <client-secret>
          provider:
            datawarehousereader:
              token-uri: <token-url>
```

### Additional Notes

- **Application Profiles:**

  Use Spring profiles (`dev`, `prod`, etc.) to manage different configurations for different environments.

- **Logging Configuration:**

  Adjust logging levels if necessary.

## Contributing

Contributions are welcome! Please see [CONTRIBUTING.md](https://github.com/Sundsvallskommun/.github/blob/main/.github/CONTRIBUTING.md) for guidelines.

## License

This project is licensed under the [MIT License](LICENSE).

## Status

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Sundsvallskommun_api-service-installation&metric=alert_status)](https://sonarcloud.io/summary/overall?id=Sundsvallskommun_api-service-installation)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=Sundsvallskommun_api-service-installation&metric=reliability_rating)](https://sonarcloud.io/summary/overall?id=Sundsvallskommun_api-service-installation)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=Sundsvallskommun_api-service-installation&metric=security_rating)](https://sonarcloud.io/summary/overall?id=Sundsvallskommun_api-service-installation)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=Sundsvallskommun_api-service-installation&metric=sqale_rating)](https://sonarcloud.io/summary/overall?id=Sundsvallskommun_api-service-installation)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=Sundsvallskommun_api-service-installation&metric=vulnerabilities)](https://sonarcloud.io/summary/overall?id=Sundsvallskommun_api-service-installation)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=Sundsvallskommun_api-service-installation&metric=bugs)](https://sonarcloud.io/summary/overall?id=Sundsvallskommun_YOUR-PROJECT-ID)

## 

Copyright (c) 2024 Sundsvalls kommun
