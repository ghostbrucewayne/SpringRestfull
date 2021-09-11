package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employ;
import com.example.demo.model.Register;
import com.example.demo.repo.RegisterRepository;

@CrossOrigin(origins = "https://rounaksuthar.github.io/AngularApp/")
@RestController
@RequestMapping("/api/v1/")
public class RegisterController {
	
	@Autowired
	private RegisterRepository RegisterRepository;
	
	@GetMapping("/register")
	public List<Register> getAllRegister(){
		return RegisterRepository.findAll();
	}
	
	@PostMapping("/register")
	public Register createEmployee(@RequestBody Register register) {
		return RegisterRepository.save(register);
	}
	
	@GetMapping("/register/{emailID}")
	public ResponseEntity<Register> getRegisterByEmail(@PathVariable String emailID){
		Register register = RegisterRepository.findById(emailID).orElseThrow(()
				-> new ResourceNotFoundException("Employee not exist with id : "+emailID));
		return ResponseEntity.ok(register);
	}
}
