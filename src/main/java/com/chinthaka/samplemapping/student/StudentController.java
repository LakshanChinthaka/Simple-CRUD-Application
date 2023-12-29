package com.chinthaka.samplemapping.student;

import com.chinthaka.samplemapping.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("create/student")
    public ResponseEntity<StudentDto> createStudent(@Validated @RequestBody StudentDto studentDto){
        return studentService.createStudent(studentDto);
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentDto>> getAllStudent(){
        return studentService.getAllStudent();
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<StudentDto> getStudentById(@Validated @PathVariable Integer id){
       return studentService.getStudentById(id);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StudentDto> updateStudent(@Validated @RequestBody StudentDto studentDto,
                                                    @PathVariable Integer id){

       return studentService.updateStudent(studentDto,id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletStudent(@PathVariable Integer id){
        return studentService.deleteStudent(id);
    }
}
