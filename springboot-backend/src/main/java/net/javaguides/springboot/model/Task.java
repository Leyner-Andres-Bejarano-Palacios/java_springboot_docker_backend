package net.javaguides.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class Task  implements Comparable<Task> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "execution_id")
	private String executionId;

	@Column(name = "task_name")
	private String taskName;

	@Column(name = "priority")
	private int priority;

	@Column(name = "time_of_schedule")
	private String timeOfSchedule;

	@Column(name = "time_of_execution")
	private String timeOfExecution;

	@Column(name = "time_of_ending")
	private String timeOfEnding;

	@Column(name = "end_ok")
	private long endOk;

	@Column(name = "machine_log")
	private String machineLog;

	@Column(name = "num_retries")
	private long numRetries;

	@Column(name = "machine_description")
	private String machineDescription;

	@Column(name = "requested_url")
	private String requestedUrl;

	@Column(name = "is_test")
	private long isTest;

	public Task() {
		
	}
	
	public Task(String executionId,
                String taskName,
                int priority,
                String timeOfSchedule,
                String timeOfExecution,
                String timeOfEnding,
                long endOk,
                String machineLog,
                long numRetries,
                String machineDescription,
				String requestedUrl,
                long isTest) {
		super();
		this.executionId = executionId;
		this.taskName = taskName;
		this.priority = priority;
        this.timeOfSchedule = timeOfSchedule;
        this.timeOfExecution = timeOfExecution;
        this.timeOfEnding = timeOfEnding;
        this.endOk = endOk;
        this.machineLog = machineLog;
        this.numRetries = numRetries;
        this.machineDescription = machineDescription;
		this.requestedUrl = requestedUrl;
        this.isTest = isTest;
	}



	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getExecutionId() {
		return executionId;
	}
	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getTimeOfSchedule() {
		return timeOfSchedule;
	}
	public void setTimeOfSchedule(String timeOfSchedule) {
		this.timeOfSchedule = timeOfSchedule;
	}
	public String getTimeOfExecution() {
		return timeOfExecution;
	}
	public void setTimeOfExecution(String timeOfExecution) {
		this.timeOfExecution = timeOfExecution;
	}
	public String getTimeOfEnding() {
		return timeOfEnding;
	}
	public void setTimeOfEnding(String timeOfEnding) {
		this.timeOfEnding = timeOfEnding;
	}
	public long getEndOk() {
		return endOk;
	}
	public void setEndOk(long endOk) {
		this.endOk = endOk;
	}                  
	public String getMachineLog() {
		return machineLog;
	}
	public void setMachineLog(String machineLog) {
		this.machineLog = machineLog;
	}
    public long getNumRetries() {
		return numRetries;
	}
	public void setNumRetries(long numRetries) {
		this.numRetries = numRetries;
	}
	public String getMachineDescription() {
		return machineDescription;
	}
	public void setRequestedUrl(String requestedUrl) {
		this.requestedUrl = requestedUrl;
	}

	public String getRequestedUrl() {
		return requestedUrl;
	}
	public void setMachineDescription(String machineDescription) {
		this.machineDescription = machineDescription;
	}
    public long getIsTest() {
		return isTest;
	}
	public void setIsTest(long isTest) {
		this.isTest = isTest;
	}

	@Override
  	public boolean equals(Object o) {
		if (!(o instanceof Task)) {
			return false;
		}
		Task task = (Task) o;
		if (this.executionId == task.getExecutionId()) {
			return true;
		}
		return false;
  	}


	@Override
    public int compareTo(Task task) {
		int taskPriority = task.getPriority();
		if (this.getPriority() < taskPriority) {
    	  return -1;
    	}
		if (this.getPriority() > taskPriority) {
      		return 1;
    	}
        return 0;
    }

}