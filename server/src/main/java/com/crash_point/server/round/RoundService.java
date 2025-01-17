package com.crash_point.server.round;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoundService {
    private final RoundRepository roundRepository;
    private final static SecureRandom RANDOM = new SecureRandom();

    public Round createRound() {
        var chance = RANDOM.nextDouble();
        double crashPoint;

        // 50% of 1.0-2.0
        if (chance < 0.50) {
            crashPoint = 1.0 + RANDOM.nextDouble();
        }
        // 30% of 2.0-10.0
        else if (chance < 0.80) {
            crashPoint = 2.0 + RANDOM.nextDouble() * 8;
        }
        // 15% of 10.0-50.0
        else if (chance < 0.95) {
            crashPoint = 10.0 + RANDOM.nextDouble() * 40;
        }
        // 5% of 50.0-100.0
        else {
            crashPoint = 50.0 + RANDOM.nextDouble() * 50;
        }

        double roundedCrashPoint = (double) Math.round(crashPoint*100)/100;
        Round round = Round.builder()
                .id(UUID.randomUUID())
                .crashPoint(roundedCrashPoint)
                .created(Instant.now())
                .build();
        return roundRepository.save(round);
    }
}
