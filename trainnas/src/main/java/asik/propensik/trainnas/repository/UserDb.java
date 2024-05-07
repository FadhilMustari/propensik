package asik.propensik.trainnas.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import asik.propensik.trainnas.model.UserModel;
import jakarta.transaction.Transactional;

@Repository
@Transactional

public interface UserDb extends JpaRepository<UserModel, UUID> {
    List<UserModel> findByNameContainingIgnoreCase(String name);
    Optional<UserModel> findByUsername(String username);
    // List<UserModel> findByRole(String role);
}
