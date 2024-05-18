package core.fu4sbackend.repository;

import core.fu4sbackend.entity.MaterialImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialImageRepository extends JpaRepository<MaterialImage, Integer> {
}
