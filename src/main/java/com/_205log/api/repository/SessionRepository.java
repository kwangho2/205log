package com._205log.api.repository;

import com._205log.api.domain.Session;
import com._205log.api.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SessionRepository extends CrudRepository<Session, Long> {

}