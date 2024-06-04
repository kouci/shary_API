package fr.ynov.shary.repository;


import fr.ynov.shary.models.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLogRepository extends JpaRepository<UserLog,Long> {
}
