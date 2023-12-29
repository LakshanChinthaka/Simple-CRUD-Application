package com.chinthaka.samplemapping.student;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {

    ResponseEntity<StudentDto> createStudent(StudentDto studentDto);

    ResponseEntity<List<StudentDto>> getAllStudent();

    ResponseEntity<StudentDto> getStudentById(Integer id);

    ResponseEntity<StudentDto> updateStudent(StudentDto studentDto, Integer id);

    ResponseEntity<String> deleteStudent(Integer id);
}
