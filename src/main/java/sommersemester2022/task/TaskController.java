package sommersemester2022.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
  @Autowired
  private TaskRepo taskRepo;

  @PostMapping("/task/add")
  public TaskEntity addTask(@RequestBody TaskEntity task) {
    return taskRepo.save(task);
  }

  @GetMapping("/task/{id}")
  public TaskEntity getTaskById(@PathVariable int id) {
    return taskRepo.findById(id).get();
  }

  @PutMapping("/task/edit/{id}")
  public TaskEntity updateTask(@PathVariable int id, @RequestBody TaskEntity task) {
    task.setId(id);
    return taskRepo.save(task);
  }
  @GetMapping("/task")
  public List<TaskEntity> getAll() {
    return taskRepo.findAll();
  }

  @DeleteMapping("/task/{id}")
  public void deleteTask(@PathVariable int id) {
    taskRepo.deleteById(id);
  }

  public List<TaskEntity> getAllTasksForGeneratedTraining(TaskCategory category, TaskDifficulty difficulty) {
    return taskRepo.findAllByCategoryAndDifficulty(category, difficulty);
  }
}