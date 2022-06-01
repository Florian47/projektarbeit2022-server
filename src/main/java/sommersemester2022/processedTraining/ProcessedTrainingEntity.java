package sommersemester2022.processedTraining;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.transaction.annotation.Transactional;
import sommersemester2022.task.TaskEntity;
import sommersemester2022.training.TrainingEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Transactional
public class ProcessedTrainingEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private int score;
  @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  private List<TaskEntity> processedSolutionTasks;
  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
  private TrainingEntity originTraining;

  private Integer studentId;

  public ProcessedTrainingEntity() {}

  public ProcessedTrainingEntity(List<TaskEntity> processedSolutionTasks, TrainingEntity originTraining) {
    this.processedSolutionTasks = processedSolutionTasks;
    this.originTraining = originTraining;
  }


  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public List<TaskEntity> getProcessedSolutionTasks() {
    return processedSolutionTasks;
  }

  public void setProcessedSolutionTasks(List<TaskEntity> processedSolutionTasks) {
    this.processedSolutionTasks = processedSolutionTasks;
  }

  public TrainingEntity getOriginTraining() {
    return originTraining;
  }

  public void setOriginTraining(TrainingEntity originTraining) {
    this.originTraining = originTraining;
  }

  public void setStudentId(Integer stdId) {
  }
  public Integer getStudentId() {
    return studentId;
  }
}


