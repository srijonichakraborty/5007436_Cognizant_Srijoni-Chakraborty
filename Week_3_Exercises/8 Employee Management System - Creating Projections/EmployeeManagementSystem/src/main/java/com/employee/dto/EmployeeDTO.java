package com.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private String departmentName;
    
    public EmployeeDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
