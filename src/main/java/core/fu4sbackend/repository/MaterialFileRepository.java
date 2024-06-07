package core.fu4sbackend.repository;

import core.fu4sbackend.entity.MaterialFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialFileRepository extends JpaRepository<MaterialFile, Integer> {
}
