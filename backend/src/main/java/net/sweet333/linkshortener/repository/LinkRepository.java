package net.sweet333.linkshortener.repository;

import net.sweet333.linkshortener.model.Link;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LinkRepository extends CrudRepository<Link, Long> {

    Optional<Link> findByLinkUrl(String linkUrl);
}
