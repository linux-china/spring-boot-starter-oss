package org.mvnsearch.boot.oss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import javax.activation.DataSource;

/**
 * aliyun oss health indicator
 *
 * @author linux_china
 */
@Component
public class AliyunOssHealthIndicator implements HealthIndicator {
    @Autowired
    private FileStorageService fileStorageService;

    public Health health() {
        try {
            DataSource ds = fileStorageService.get("ok.txt");
            if (ds != null) {
                return Health.up().build();
            } else {
                return Health.down().withDetail("oss", "ok.txt not found").build();
            }
        } catch (Exception e) {
            return Health.down().withDetail("oss", e.getMessage()).build();
        }
    }
}
