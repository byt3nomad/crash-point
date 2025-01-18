package com.crash_point.server.round;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoundService {
    private final static SecureRandom RANDOM = new SecureRandom();
    private final static double BASE_MULTIPLIER = 1.0;
    private final static double GROWTH_RATE = 0.07;


    private final RoundRepository roundRepository;
    private final SimpMessagingTemplate messagingTemplate;

    @Async("roundExecutor")
    public void startRoundGenerationLoop() {
        log.info("Starting round generation loop on {}", Thread.currentThread());
        while (true) {
            var round = createRound();

            startRound(round);
        }
    }

    private Round createRound() {
        var crashPoint = generateCrashPoint();
        Round round = Round.builder()
                .id(UUID.randomUUID())
                .crashPoint(RoundUtil.round(crashPoint))
                .created(Instant.now())
                .build();
        return roundRepository.save(round);
    }

    private void startRound(Round round) {
        var startTime = System.currentTimeMillis();
        var crashPoint = round.getCrashPoint();
        double currentMultiplier;

        while (true) {
            var secondsPassed = (double) (System.currentTimeMillis() - startTime) / 1000;
            currentMultiplier = BASE_MULTIPLIER * Math.pow(1 + GROWTH_RATE, secondsPassed);

            if (currentMultiplier >= crashPoint) {
                messagingTemplate.convertAndSend("/topic/round", RoundUtil.round(crashPoint));
                break;
            }

            messagingTemplate.convertAndSend("/topic/round", RoundUtil.round(currentMultiplier));
            RoundUtil.sleep(200);
        }
    }

    private double generateCrashPoint() {
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

        return crashPoint;
    }
}
