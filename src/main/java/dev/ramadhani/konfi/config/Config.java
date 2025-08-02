package dev.ramadhani.konfi.config;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Map;

@Document
@NoArgsConstructor
@Data
public class Config {
    @Id
    private String id;
    private String name;
    private ConfigSourceType configSourceType;
    @CreatedDate
    private Instant createdDate;
    @LastModifiedDate
    private Instant updatedDate;
    private Map<String, Object> config;
}
