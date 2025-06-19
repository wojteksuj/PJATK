import org.example.s31087tpo10.model.Link;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LinkRepository extends CrudRepository<Link, String> {
    Optional<Link> findByName(String name);
    boolean existsByTargetUrl(String targetUrl);


}
