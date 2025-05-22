package com.student_service.student_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchoolResponse {
    private UUID id;
    private String name;
    private String principal;
}
