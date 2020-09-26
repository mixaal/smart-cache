package net.mikc.storage;

import lombok.extern.slf4j.Slf4j;
import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.FailsafeExecutor;
import net.jodah.failsafe.RetryPolicy;

import javax.ws.rs.core.Response;
import java.time.Duration;

@Slf4j
public class ApplicationFactory {
    private static final RetryPolicy<Response> retryPolicy = new RetryPolicy<Response>()
            .withDelay(Duration.ofSeconds(1))
            .onFailedAttempt(responseExecutionAttemptedEvent -> log.warn("Failed attempt"))
            .handle(Throwable.class)
            .handleResult(null)
            //.withBackoff(1, 10, ChronoUnit.SECONDS)
            .withMaxRetries(10);

    public static final FailsafeExecutor<Response> RestRetryPolicyExecutor = Failsafe.with(retryPolicy);
}
