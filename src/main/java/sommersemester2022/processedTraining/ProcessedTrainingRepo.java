package sommersemester2022.processedTraining;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import sommersemester2022.training.TrainingEntity;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * ProcessedTrainingRepo implementiert das JpaRepository und schafft die Verbindung zur Datenbank. Außerdem führt das
 * Repository u.a. alle CRUD-Operationen für die bearbeitetes Training-Entität durch.
 * @author Tobias Esau, Alexander Kiehl
 */
@Transactional
@Service
public interface ProcessedTrainingRepo extends JpaRepository<ProcessedTrainingEntity, Integer> {

  /**
   * Gibt eine Liste aller Trainings zurück, welche bereits von dem Schüler mit der angegebenen ID bearbeitet wurden.
   * @return Liste aller Trainings, welche bearbeitet wurden
   */
  List<ProcessedTrainingEntity> findByStudentId(int id);
  /**
   * Gibt eine Liste aller Trainings zurück, welche zum angegebenen "Ursprungs-"Training gehören.
   * @return Liste aller Trainings, gehören zum Urspungs-Training
   */
  List<ProcessedTrainingEntity> findByOriginTraining(Optional<TrainingEntity> originTraining);
}
