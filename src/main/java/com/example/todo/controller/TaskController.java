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
            long nextid = tasks.size()+1;
            task.setId(nextid);

            tasks.add(task);
            return task;
        }
    }