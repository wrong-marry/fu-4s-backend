package core.fu4sbackend.repository;

import core.fu4sbackend.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, String> {
    Subject findByCode(String subjectCode);
    public List<Subject> findAllByOrderBySemesterAsc();

    Integer countByIsActive(boolean isActive);

    public List<Subject> findBySemester(int semester);
}
