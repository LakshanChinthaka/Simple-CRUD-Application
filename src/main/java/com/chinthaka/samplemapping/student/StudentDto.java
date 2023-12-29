package com.chinthaka.samplemapping.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private Integer id;
    private String name;
    private String age;
    private String nic;
    private String email;
}
