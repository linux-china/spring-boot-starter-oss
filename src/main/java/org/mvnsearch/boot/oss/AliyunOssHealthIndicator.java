package org.mvnsearch.boot.oss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
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
public class AliyunOssHealthIndicator extends AbstractHealthIndicator {
    @Autowired
    private FileStorageService fileStorageService;

    protected void doHealthCheck(Health.Builder builder) throws Exception {
        DataSource ds = fileStorageService.get("ok.txt");
        if (ds != null) {
            builder.up().withDetail("ok.txt", "Found");
        } else {
            builder.down().withDetail("ok.txt", "Not found");
        }
    }

}
