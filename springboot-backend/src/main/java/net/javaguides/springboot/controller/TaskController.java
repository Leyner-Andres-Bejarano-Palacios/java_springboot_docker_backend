package net.javaguides.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Task;
import net.javaguides.springboot.repository.TaskRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class TaskController {

	@Autowired
	private TaskRepository taskRepository;
	
	// get all tasks
	@GetMapping("/tasks")
	public List<Task> getAllTasks(){
		return taskRepository.findAll();
	}

	@PostMapping("/tasks")
	public Task createEmployee(@RequestBody Task task) {
		return taskRepository.save(task);
	}

	@GetMapping("/tasks/{id}")
	public ResponseEntity<Task> getEmployeeById(@PathVariable Long id) {
		Task task = taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task not exist with id :" + id));
		return ResponseEntity.ok(task);
	}


		// update employee rest api
	
	@PutMapping("/tasks/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails){
		Task task = taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task not exist with id :" + id));
		
		task.setTaskName(taskDetails.getTaskName());
		task.setPriority(taskDetails.getPriority());
		task.setTimeOfSchedule(taskDetails.getTimeOfSchedule());
		task.setTimeOfExecution(taskDetails.getTimeOfExecution());
		task.setTimeOfEnding(taskDetails.setTimeOfEnding());
		task.setEndOk(taskDetails.getEndOk());
		task.setMachineLog(taskDetails.getMachineLog());
		task.setNumRetries(taskDetails.getNumRetries());
		task.setMachineDescription(taskDetails.getMachineDescription());
		task.setIsTest(taskDetails.getIsTest());
		
		Task updatedTask = taskRepository.save(task);
		return ResponseEntity.ok(updatedTask);
	}
}
