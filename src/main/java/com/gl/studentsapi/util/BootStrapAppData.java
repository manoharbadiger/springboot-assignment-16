package com.gl.studentsapi.util;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.gl.studentsapi.model.Student;
import com.gl.studentsapi.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BootStrapAppData implements ApplicationListener<ApplicationReadyEvent>{
	
	private final StudentRepository studentRepository;
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		// implement faker here
		for(int i=0; i<10; i++) {
			Student student = new Student();
			student.setFirstName("Sameer" + i);
			student.setLastName("asd" + i);
			student.setCountry("In" + i);
			student.setCourse("IT" + i);
			studentRepository.save(student);
		}
		
		
	}

}
