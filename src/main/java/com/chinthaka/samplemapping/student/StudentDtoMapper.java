package com.chinthaka.samplemapping.student;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentDtoMapper {

    StudentDtoMapper mapper = Mappers.getMapper(StudentDtoMapper.class);

    Student dtoToModel(StudentDto studentDto);
    StudentDto modelToDto(Student student);
}
