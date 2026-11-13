package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.ToDo;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<ToDo, Long> {

    List<ToDo> findAllByUser_Username(String username);

    Optional<ToDo> findByIdAndUser_Username(
            Long id,
            String username
    );
}
