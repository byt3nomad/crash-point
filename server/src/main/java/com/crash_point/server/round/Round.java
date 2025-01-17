package com.crash_point.server.round;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Round {
    @Id
    private UUID id;

    @Column(name = "crash_point")
    private double crashPoint;

    @Column
    private Instant created;
}
