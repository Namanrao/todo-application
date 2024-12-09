    package com.example.todo.controller;

    import com.example.todo.model.Task;
    import org.springframework.web.bind.annotation.*;

    import java.util.ArrayList;
    import java.util.List;

    @RestController
    @RequestMapping("/tasks")
    public class TaskController {
        private final List<Task> tasks = new ArrayList<>();

        @GetMapping("/")
        public String home() {
            return "Welcome to the To-Do List API!";
        }

        @GetMapping
        public List<Task> getAllTasks() {
            return tasks;
        }

        @PostMapping
        public Task addTask(@RequestBody Task task) {
            if (task.getPriority() == null || task.getPriority().isEmpty()) {
                task.setPriority("MEDIUM"); // Default to MEDIUM if priority is not provided
            }
            long nextid = tasks.size()+1;
            task.setId(nextid);
            tasks.add(task);
            return task;
        }



        //        Sorted by priority
        @GetMapping("/sorted-by-priority")
        public List<Task> getTasksSortedByPriority() {
            return tasks.stream()
                    .sorted((t1, t2) -> t1.getPriority().compareToIgnoreCase(t2.getPriority()))
                    .toList();
        }

        // Update a task by ID
        @PutMapping("/{id}")
        public Task updateTask(@PathVariable long id, @RequestBody Task updatedTask) {
            // Loop through the tasks list to find the task with the matching ID
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getId() == id) {
                    // Update the found task with the new data from updatedTask
                    tasks.get(i).setDescription(updatedTask.getDescription());
                    tasks.get(i).setCompleted(updatedTask.isCompleted());
                    tasks.get(i).setPriority(updatedTask.getPriority());
                    return tasks.get(i);
                }
            }
            // If no task was found with the given ID, throw an exception
            throw new RuntimeException("Task not found");
        }



        // Delete a task by ID
        @DeleteMapping("/{id}")
        public String deleteTask(@PathVariable long id) {
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getId() == id) {
                    tasks.remove(i);
                    return "Task deleted successfully";
                }
            }
            throw new RuntimeException("Task not found");
        }

    }