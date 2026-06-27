# Omnixys Commons

Zero-dependency shared platform contracts: enums, exceptions, DTOs, and utilities.

## Features

- Error code enum and exception hierarchy with context (requestId, correlationId, traceId, actorId, tenantId)
- Core DTOs: Address, GeoLocation, PhoneNumber, PaginationRequest/Response, LogEntry
- Enums: UserType, AddressType, LogLevel, EventRoleType, StatusType, and more
- Kafka and Contract envelopes
- Utilities: StringUtils, ValidationUtils, RandomUtils, DateTimeUtils

## Installation

```xml
<dependency>
    <groupId>com.omnixys</groupId>
    <artifactId>commons</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Exception Usage

```java
throw new UserNotFoundException("User 42 not found");

throw new BaseOmnixysException(ErrorCode.FORBIDDEN, "Access denied",
    new ExceptionContext() {
        public String requestId() { return "req-123"; }
        public String correlationId() { return "corr-456"; }
        public String traceId() { return "trace-789"; }
        public String actorId() { return "user-1"; }
        public String tenantId() { return "tenant-a"; }
        public Map<String, Object> metadata() { return Map.of("reason", "no permission"); }
    });
```
