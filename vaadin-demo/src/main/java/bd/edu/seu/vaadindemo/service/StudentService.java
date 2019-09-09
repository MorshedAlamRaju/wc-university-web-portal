package bd.edu.seu.vaadindemo.service;

import bd.edu.seu.vaadindemo.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    Map<Long, Student> studentMap;

    public StudentService(){
        studentMap = new HashMap<>();
    }



    public List<Student> findAll(){
        return studentMap.values().stream().collect(Collectors.toList());
    }

    public Student save(Student student){
        long id = student.getId();
        studentMap.put(id, student);
        return studentMap.get(id);
    }

    public Student findById(long id){
        return studentMap.getOrDefault(id, null);
    }

    public Student update(long id, Student student){
        Student student1 = studentMap.get(id);
        if(student != null){
            student.setId(student.getId());
            studentMap.put(student.getId(), student);
            return studentMap.get(student.getId());
        }
        return null;
    }

    public Student delete(long id){
        Student student = studentMap.get(id);
        studentMap.remove(student);
        return student;
    }








}
