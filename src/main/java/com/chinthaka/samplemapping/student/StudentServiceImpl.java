package com.chinthaka.samplemapping.student;

import com.chinthaka.samplemapping.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final StudentDtoMapper studentDtoMapper;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, StudentDtoMapper studentDtoMapper) {
        this.studentRepository = studentRepository;
        this.studentDtoMapper = studentDtoMapper;
    }

    @Override
    public ResponseEntity<StudentDto> createStudent(StudentDto studentDto) {
        final Student student = studentDtoMapper.mapper.dtoToModel(studentDto);
        final Student existingStudent  = studentRepository.findByNic(student.getNic());

        if (existingStudent == null && student.getNic().equals(existingStudent.getNic())){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        final Student saveStudent = studentRepository.save(student);
        final StudentDto convertSaveStudent = studentDtoMapper.mapper.modelToDto(saveStudent);
        return new ResponseEntity<>(convertSaveStudent,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity getAllStudent() {
        final List<Student> foundStudents = studentRepository.findAll();
        if (foundStudents.isEmpty()){
           return new ResponseEntity<>(Collections.EMPTY_LIST,HttpStatus.NOT_FOUND);
        }
        final List<StudentDto> convertFoundStudent = foundStudents
                .stream()
                .map(studentDtoMapper.mapper::modelToDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(convertFoundStudent,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StudentDto> getStudentById(Integer id) {
        final Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found Student with " + id));
        final StudentDto convertFoundStudent = studentDtoMapper.mapper.modelToDto(student);
        return new ResponseEntity<>(convertFoundStudent, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StudentDto> updateStudent(StudentDto studentDto, Integer id) {
        final Student getStudent = studentDtoMapper.mapper.dtoToModel(studentDto);

        if (isStudentExist(id)){
            final Student exsistStudent = studentRepository.findById(id).get();
            exsistStudent.setName(getStudent.getName());
            exsistStudent.setNic(getStudent.getNic());
            exsistStudent.setAge(getStudent.getAge());
            exsistStudent.setEmail(getStudent.getEmail());

            final Student updateStudent = studentRepository.save(exsistStudent);
            final StudentDto convertStudent = studentDtoMapper.mapper.modelToDto(updateStudent);
            return new ResponseEntity<>(convertStudent,HttpStatus.OK);
        }

        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<String> deleteStudent(Integer id) {
//       boolean isExsistStudent = studentRepository.existsById(id);
        String message = null;
       if (isStudentExist(id)){
           studentRepository.deleteById(id);
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
       return new ResponseEntity<>(message = "Not Found",HttpStatus.BAD_REQUEST);
    }

    public boolean isStudentExist(Integer id){
        return studentRepository.existsById(id);
    }
}
