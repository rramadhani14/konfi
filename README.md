# Konfi
Konfi is a configuration server based on spring cloud config to save/load configuration for services.
At the moment, configuration will be saved in mongodb and configuration can be retrieved through REST endpoints.
Planned features are:
- Authentication with multi tenancy
- CRUD for configurations
  - Configuration is planned to support multiple storage source like GIT and MongoDB(like in Spring Cloud Config)
- Client to connect to config to receive update event using different mechanisms:
  - HTTP polling
  - HTTP Stream(SSE)