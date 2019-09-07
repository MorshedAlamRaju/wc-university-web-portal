package bd.edu.seu.grademanagementserver.service;

import bd.edu.seu.grademanagementserver.exception.ResourceAlreadyExistException;
import bd.edu.seu.grademanagementserver.exception.ResourceDoesNotExistException;
import bd.edu.seu.grademanagementserver.model.Grade;
import bd.edu.seu.grademanagementserver.model.StudentGrade;
import bd.edu.seu.grademanagementserver.repository.StudentGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentGradeService {

    @Autowired
    private StudentGradeRepository studentGradeRepository;

    public StudentGrade findById(long id) throws ResourceDoesNotExistException {
        Optional<StudentGrade> optionalStudentGrade = studentGradeRepository.findById(id);
        if(optionalStudentGrade.isPresent())return optionalStudentGrade.get();
        else throw new ResourceDoesNotExistException(id + "");
    }

    public List<StudentGrade> findAll(){
        List<StudentGrade> studentGradeList = new ArrayList<>();
        studentGradeRepository.findAll().forEach(studentGradeList::add);
        return studentGradeList;
    }


    public List<StudentGrade> findByEarnedCredits(double credits){

        return  studentGradeRepository.findByEarnedCredits(credits)
                .stream()
                .filter(studentGrade -> studentGrade.getEarnedCredits() - credits > 0)
                .collect(Collectors.toList());
    }

    public List<StudentGrade> findByEarnedCgpa(double cgpa){

        return studentGradeRepository.findByEarnedCgpa(cgpa)
                .stream()
                .filter(studentGrade -> studentGrade.getEarnedCgpa() - cgpa > 0)
                .collect(Collectors.toList());
    }

    public StudentGrade update(long id, Grade grade) throws ResourceDoesNotExistException {
        Optional<StudentGrade> optionalStudentGrade = studentGradeRepository.findById(id);
        if(optionalStudentGrade.isPresent() && optionalStudentGrade.get().getGradeList().contains(grade)){
            StudentGrade studentGrade = optionalStudentGrade.get();
            if(studentGrade.getGradeList().contains(grade)){
                List<Grade> gradeList = studentGrade.getGradeList();
                Grade grade1 = gradeList.get(gradeList.indexOf(grade));
                if((int)(grade1.getPoints() * 100)  < ((int)grade.getPoints() * 100)){
                    studentGrade.getGradeList().remove(grade1);
                    return studentGradeRepository.save(calculateNewGrade(studentGrade, grade));
                }
                else return studentGrade;
            } else return studentGrade;
        }
        else throw new ResourceDoesNotExistException(id + "");
    }

    public StudentGrade save(Grade grade) throws ResourceAlreadyExistException {
        Optional<StudentGrade> optionalStudentGrade = studentGradeRepository.findById(grade.getStudentId());
        if (optionalStudentGrade.isPresent()){
                List<Grade> gradeList = optionalStudentGrade.get().getGradeList();

                if(!gradeList.contains(grade)){
                    StudentGrade studentGrade = calculateNewGrade(optionalStudentGrade.get(), grade);
                    return studentGradeRepository.save(studentGrade);
                }
                else throw new ResourceAlreadyExistException(grade.getCourse().getCode());
        }
        else return studentGradeRepository.save(calculateNewGrade(new StudentGrade(), grade));
    }

    private StudentGrade calculateNewGrade(StudentGrade studentGrade, Grade grade) {
        studentGrade.setId(grade.getStudentId());
        studentGrade.getGradeList().add(grade);
        studentGrade.setEarnedCgpa(0.0);
        studentGrade.setAttemptedCredits(0.0);
        studentGrade.setEarnedCredits(0.0);
        double attempted = 0, earned = 0, weightedGpa = 0, forCgCount = 0;
        for (Grade grade1 : studentGrade.getGradeList()) {
            forCgCount += !grade1.getCourse().getCourseType().equals("NonCredit") ? grade1.getCourse().getCredits() : 0;
            attempted += grade1.getCourse().getCredits();
            earned += ((int) grade1.getPoints() * 100 >= 200) ? grade1.getCourse().getCredits() : 0;
            weightedGpa += !grade1.getCourse().getCourseType().equals("NonCredit") ? grade1.getCourse().getCredits() * grade1.getPoints() : 0;
        }
        double cgpa = Math.round(weightedGpa * 100.0 / forCgCount);
        studentGrade.setEarnedCredits(earned);
        studentGrade.setAttemptedCredits(attempted);
        studentGrade.setEarnedCgpa(cgpa);
            return studentGrade;
    }

    private double getPoint(int marks) {
        return marks >= 80 ? 4.0 : marks >= 75 ? 3.75 : marks >= 70 ? 3.50 :
                marks >= 65 ? 3.25 : marks >= 60 ? 3.00 : marks >= 55 ? 2.75 :
                marks >= 50 ? 2.50 : marks >= 45 ? 2.25 : marks >= 40 ? 2.00 : 0.0;
    }


}
