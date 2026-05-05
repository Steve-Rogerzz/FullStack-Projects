package com.stitch.eventhub.repository;

import com.stitch.eventhub.entity.Registration;
import com.stitch.eventhub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByUser(User user);
    boolean existsByUserIdAndEventId(Long userId, Long eventId);
    Optional<Registration> findByUserIdAndEventId(Long userId, Long eventId);
    void deleteByUserIdAndEventId(Long userId, Long eventId);
}
