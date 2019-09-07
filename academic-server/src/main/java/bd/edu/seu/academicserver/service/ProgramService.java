package bd.edu.seu.academicserver.service;

import bd.edu.seu.academicserver.exception.ResourceAlreadyExistException;
import bd.edu.seu.academicserver.exception.ResourceDoesNotExistException;
import bd.edu.seu.academicserver.model.Course;
import bd.edu.seu.academicserver.model.Program;
import bd.edu.seu.academicserver.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.PrimitiveIterator;

@Service
public class ProgramService {
    @Autowired
    private ProgramRepository  programRepository;

    public List<Program> findAll(){
        return programRepository.findAll();
    }

    public Program findByName(String name){
        return programRepository.findByName(name);
    }

    public List<Course> Courses(String name) throws ResourceDoesNotExistException {
        Program program = programRepository.findByName(name);
        if(program != null){
            return program.getCourseList();
        }
        else throw new ResourceDoesNotExistException(name);
    }

    public Program save(Program program) throws ResourceAlreadyExistException {
        Program program1 = programRepository.findByName(program.getName());
        if(program1 == null){
            return programRepository.save(program);
        }
        else throw new ResourceAlreadyExistException(program.getName());

    }

    public Program update(String name, Program program) throws ResourceDoesNotExistException {
        Program program1 = programRepository.findByName(name);
        if(program1 != null){
            program.setName(program1.getName());
            return programRepository.save(program);
        }
        else throw new ResourceDoesNotExistException(name);
    }

    public Program addCourse(String name, Course course) throws ResourceDoesNotExistException {
        Program program = programRepository.findByName(name);
        if(program != null){
            program.getCourseList().add(course);
            return update(program.getName(), program);
        }
        else throw new ResourceDoesNotExistException(name);
    }

    public Program removeCourse(String name, Course course) throws ResourceDoesNotExistException {
        Program program = programRepository.findByName(name);
        if(program != null){
            program.getCourseList().remove(course);
            return update(name, program);
        }
        else throw new ResourceDoesNotExistException(name);
    }

}
