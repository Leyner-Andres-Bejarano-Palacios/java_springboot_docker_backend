package net.javaguides.springboot.application;

import java.util.*;
import lombok.NoArgsConstructor;
import net.javaguides.springboot.model.Task;
import org.springframework.stereotype.Component;


@Component
@NoArgsConstructor
public class TaskPriorizer {

    PriorityQueue<Task> minHeap = new PriorityQueue<>();
    HashMap<Integer, Task> hmap = new HashMap<Integer, Task>();

    public String fn_priorize_task(Task task) {
        minHeap.add(task);
        hmap.put(task.getPriority(), task);
        return "Succefully priorized task";
    }

    public Task fn_get_first_task() {
        return minHeap.poll();
        // minHeap.poll()
        // minHeap.add(taskId);
        // hmap.put(taskId, task);
        // return "Succefully priorized task";
    }

    public HashMap<Integer, Task> fn_get_all_task() {
        return hmap;
        // minHeap.poll()
        // minHeap.add(taskId);
        // hmap.put(taskId, task);
        // return "Succefully priorized task";
    }


    // public get_list_tak
}