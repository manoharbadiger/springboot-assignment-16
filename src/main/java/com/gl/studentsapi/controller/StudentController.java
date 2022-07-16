package com.gl.studentsapi.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.gl.studentsapi.model.Student;
import com.gl.studentsapi.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<Student> fetchStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping("/sort")
    @ResponseBody
    public List<Student> sortedOrder(@RequestParam(name = "order") String order) {
        if (order.equalsIgnoreCase("asc")) {
            List<Student> sortedStudents = studentService.findAllStudents();
            Collections.sort(sortedStudents, Comparator.comparing(Student::getFirstName));
            return sortedStudents;
        } else if (order.equalsIgnoreCase("desc")) {
            List<Student> sortedStudents = studentService.findAllStudents();
            Collections.sort(sortedStudents, Comparator.comparing(Student::getFirstName).reversed());
            return sortedStudents;
        }
        return studentService.findAllStudents();
    }

    @GetMapping("/{id}")
    public Student fetchStudent(@PathVariable("id") long studentId) {
        return studentService.findById(studentId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@RequestBody Student student, @PathVariable("id") long studentId) {
        return studentService.updateStudent(student, studentId);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable("id") long studentId) {
        studentService.deleteStudent(studentId);
        return "Deleted student Id -" + studentId;
    }

}
