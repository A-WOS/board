package study.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.board.domain.entity.File;

public interface FileRepository extends JpaRepository<File, Long> {

}
