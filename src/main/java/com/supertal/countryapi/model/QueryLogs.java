package com.supertal.countryapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

/**
 * @author Jabed Hasan<jabedhasan21@gmail.com>
 * @created on 27/01/2022 - 17:27
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "query_logs")
public class QueryLogs {
    @Id
    private ObjectId id;
    private Country country;
    private String userEmail;
    private Instant createdAt = Instant.now();
}
