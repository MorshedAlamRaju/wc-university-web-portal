package bd.edu.seu.studentadmissionserver.service;

import bd.edu.seu.studentadmissionserver.exception.ResourceAlreadyExistException;
import bd.edu.seu.studentadmissionserver.exception.ResourceDoesNotExistException;
import bd.edu.seu.studentadmissionserver.model.Student;
import bd.edu.seu.studentadmissionserver.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student findById(long id) throws ResourceDoesNotExistException {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isPresent())
            return optionalStudent.get();
        else throw new ResourceDoesNotExistException(id + "");
    }

    public List<Student> findAll(){
        List<Student> studentList = new ArrayList<>();
        studentRepository.findAll().forEach(studentList::add);
        return studentList;
    }

    public Student save(Student student) throws ResourceAlreadyExistException {
        Optional<Student> optionalStudent = studentRepository.findById(student.getStudentId());
        if(optionalStudent.isPresent())
            throw new ResourceAlreadyExistException(student.getStudentId() + "");
        else return studentRepository.save(student);
    }

    public long delete(long id) throws ResourceDoesNotExistException {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isPresent()){
            studentRepository.deleteById(id);
            return id;
        }
        else throw new  ResourceDoesNotExistException(id + "");
    }

    public Student updateStudent(long id, Student student) throws ResourceDoesNotExistException {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if(optionalStudent.isPresent()){
            student.setStudentId(optionalStudent.get().getStudentId());
            return studentRepository.save(student);
        }
        else throw new ResourceDoesNotExistException(id + "");
    }

    public List<Student> findByProgramName(String programName){
        List<Student> studentList = new ArrayList<>();
        studentRepository.findByProgramName(programName).forEach(studentList::add);
        return  studentList;
    }

    public List<Student> findByBatch(int batch){
        List<Student> studentList = new ArrayList<>();
        studentRepository.findByBatch(batch).forEach(studentList::add);
        return studentList;
    }

}
