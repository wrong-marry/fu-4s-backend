package core.fu4sbackend.repository;

import core.fu4sbackend.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, String> {
    public List<Subject> findAllByOrderBySemesterAsc();


    @Query("SELECT s FROM Subject s WHERE s.isActive = true ORDER BY s.semester ASC")
    List<Subject> findAllActiveOrderBySemesterAsc();

    Integer countByIsActive(boolean isActive);

    public List<Subject> findBySemester(int semester);

    boolean existsByCode(String code);

    Subject findByCode(String subjectCode);
}
