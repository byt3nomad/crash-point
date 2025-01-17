package com.crash_point.server.round;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoundRepository extends JpaRepository<Round,UUID> {
}
