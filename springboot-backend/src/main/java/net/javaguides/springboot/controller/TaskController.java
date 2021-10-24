package net.javaguides.springboot.controller;

import java.util.Random;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;
import java.util.Calendar;
import java.text.SimpleDateFormat;

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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Task;
import net.javaguides.springboot.repository.TaskRepository;
import net.javaguides.springboot.application.TaskPriorizer;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class TaskController {
	private TaskRepository taskRepository;
	private TaskPriorizer taskPriorizer;
	public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";


	public TaskController(
		TaskRepository taskRepository,
		TaskPriorizer taskPriorizer
	){
		this.taskRepository = taskRepository;
		this.taskPriorizer  = taskPriorizer;

	}


	
	// get all tasks
	@GetMapping("/tasks")
	public List<Task> getAllTasks(){
		return taskRepository.findAll();
	}

	@PostMapping("/tasks")
	public Task createTask(@RequestBody Task task) {
		task.setEndOk(3);
		task.setTimeOfSchedule(now());
		Task savedTask = taskRepository.save(task);
		String response = taskPriorizer.fn_priorize_task(savedTask);
		return savedTask;
	}

	@GetMapping("/tasks/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
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
		task.setTimeOfEnding(taskDetails.getTimeOfEnding());
		task.setEndOk(taskDetails.getEndOk());
		task.setMachineLog(taskDetails.getMachineLog());
		task.setNumRetries(taskDetails.getNumRetries());
		task.setMachineDescription(taskDetails.getMachineDescription());
		task.setRequestedUrl(taskDetails.getRequestedUrl());
		task.setIsTest(taskDetails.getIsTest());
		
		Task updatedTask = taskRepository.save(task);
		taskPriorizer.fn_add_task_TimeOfEnding(updatedTask);
		return ResponseEntity.ok(updatedTask);
	}


	@PostMapping("/taskss")
	public ResponseEntity<Task> updateTaskv2(@RequestBody Task taskDetails){
		Task task = taskRepository.findById(taskDetails.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Task not exist with id :" + taskDetails.getId()));
		
		task.setTaskName(taskDetails.getTaskName());
		task.setPriority(taskDetails.getPriority());
		task.setTimeOfSchedule(taskDetails.getTimeOfSchedule());
		task.setTimeOfExecution(taskDetails.getTimeOfExecution());
		task.setTimeOfEnding(taskDetails.getTimeOfEnding());
		task.setEndOk(taskDetails.getEndOk());
		task.setMachineLog(taskDetails.getMachineLog());
		task.setNumRetries(taskDetails.getNumRetries());
		task.setMachineDescription(taskDetails.getMachineDescription());
		task.setRequestedUrl(taskDetails.getRequestedUrl());
		task.setIsTest(taskDetails.getIsTest());
		
		Task updatedTask = taskRepository.save(task);
		taskPriorizer.fn_add_task_TimeOfEnding(updatedTask);
		return ResponseEntity.ok(updatedTask);
	}


	@DeleteMapping("/tasks/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteTask(@PathVariable Long id){
		Task task = taskRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Task not exist with id :" + id));
		
		taskRepository.delete(task);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/tasks-priorizer")
	public String priorizeTask(@RequestBody Task task) {
		return taskPriorizer.fn_priorize_task(task);
	}

	@GetMapping("/tasks-priorizer")
	public Task getFirstTask(){
		return taskPriorizer.fn_get_first_task();
	}

	@GetMapping("/tasks-priorize-all")
	public HashMap<Integer, Task> getAllTask(){
		return taskPriorizer.fn_get_all_task();
	}

	public int establishPrority(Task task) {
		int result;
		HttpPost post = new HttpPost("http://graduation_neural_network_image:5000");
        StringBuilder json = new StringBuilder();
        json.append("{\"petal_length\":");
		json.append(String.valueOf(task.getIsTest()));
        json.append("}");
		try{
			post.setEntity(new StringEntity(json.toString()));
		}
		catch(Exception e){
			System.out.print(e);
		}
		
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
            result = Integer.parseInt(EntityUtils.toString(response.getEntity()));
        }
		catch(Exception e) {
			result = new Random().nextInt(1000);
		}
		return result;
	}

	public static String now() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		return sdf.format(cal.getTime());
	}
}
