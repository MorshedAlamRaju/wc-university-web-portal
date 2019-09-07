package bd.edu.seu.academicserver.controller;

import bd.edu.seu.academicserver.exception.ResourceAlreadyExistException;
import bd.edu.seu.academicserver.exception.ResourceDoesNotExistException;
import bd.edu.seu.academicserver.model.Course;
import bd.edu.seu.academicserver.model.Program;
import bd.edu.seu.academicserver.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/programs")
public class ProgramController {
    @Autowired
    private ProgramService programService;

    @GetMapping(value = "")
    public ResponseEntity<List<Program>> findAll(){
        try{
            List<Program> programs = programService.findAll();
            return ResponseEntity.ok().body(programs);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<Program> findByName(@PathVariable String name){
        try{
            Program program = programService.findByName(name);
            return ResponseEntity.ok().body(program);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<Program> save(@RequestBody Program program){
        try{
            Program saved = programService.save(program);
            return ResponseEntity.ok().body(saved);
        } catch (ResourceAlreadyExistException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(value = "/courses/{programName}")
    public ResponseEntity<Program> addCourse(@PathVariable String programName, @RequestBody Course course){
        try{
            Program program = programService.addCourse(programName, course);
            return ResponseEntity.ok().body(program);
        } catch(ResourceDoesNotExistException e){
            return ResponseEntity.notFound().build();
        }
    }
}
