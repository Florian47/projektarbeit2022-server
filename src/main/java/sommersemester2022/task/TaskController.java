package sommersemester2022.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TaskController ist die Controller-Klasse für die Entität der Aufgabe.
 * Sie kontrolliert alle Aktivitäten, welche mit den Lösungen ausgeführt werden können und gibt die
 * Informationen an das Repository weiter.
 * @author Tobias Esau, Alexander Kiehl, Florian Weinert
 * @see TaskRepo
 */
@RestController
public class TaskController {
  @Autowired
  private TaskRepo taskRepo;

  /**
   * Erstellt eine neue Aufgabe im System.
   * @param task Frontend Daten für die Aufgabe
   * @return erstellte Aufgabe
   */
  @PreAuthorize("hasRole('ROLE_TEACHER')")
  @PostMapping("/task/add")
  public TaskEntity addTask(@RequestBody TaskEntity task) {
    return taskRepo.save(task);
  }

  /**
   * Gibt die Aufgabe mit der gewünschten ID aus der Datenbank heraus.
   * @param id ID der Aufgabe
   * @return Aufgabe mit gewünschter ID
   */
  @PreAuthorize("hasRole('ROLE_TEACHER')")
  @GetMapping("/task/{id}")
  public TaskEntity getTaskById(@PathVariable int id) {
    return taskRepo.findById(id).get();
  }

  /**
   * Bearbeitet eine vorhandene Aufgabe.
   * @param id ID der Aufgabe
   * @param task Frontend Daten der Aufgabe
   * @return bearbeitete Aufgabe
   */
//  @PreAuthorize("hasRole({'ROLE_TEACHER','ROLE_STUDENT'})")
  @PutMapping("/task/edit/{id}")
  public TaskEntity updateTask(@PathVariable int id, @RequestBody TaskEntity task) {
    task.setId(id);
    return taskRepo.save(task);
  }

  /**
   * Gibt alle im System vorhandenen Aufgaben zurück.
   * @return Liste mit allen im System existierenden Aufgaben
   */
  @PreAuthorize("hasRole('ROLE_TEACHER')")
  @GetMapping("/task")
  public List<TaskEntity> getAll() {
    return taskRepo.findAllByIndividualFalse();
  }

  /**
   * Löscht die Aufgabe mit der entsprechenden ID.
   * @param id ID der Aufgabe
   */
  @PreAuthorize("hasRole('ROLE_TEACHER')")
  @DeleteMapping("/task/{id}")
  public void deleteTask(@PathVariable int id) {
    taskRepo.deleteById(id);
  }

  /**
   * Gibt alle Aufgaben mit der angegebenen Kategorie und dem angegebenen Schwierigkeitsgrad zurück.
   * @param category Kategorie der Aufgaben
   * @param difficulty Schwierigkeit der Aufgaben
   * @return Liste aller Aufgaben mit entsprechender Kategorie und Schwierigkeit
   */
  @PreAuthorize("hasRole('ROLE_TEACHER')")
  public List<TaskEntity> getAllTasksForGeneratedTraining(TaskCategory category, TaskDifficulty difficulty) {
    return taskRepo.findAllByCategoryAndDifficultyAndIndividualFalse(category, difficulty);
  }
}
