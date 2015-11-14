package org.mvnsearch.boot.oss;

import org.mvnsearch.boot.oss.impl.FileStorageServiceOssImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * aliyun OSS endpoint
 *
 * @author linux_china
 */
@ConfigurationProperties(prefix = "endpoints.aliyunoss", ignoreUnknownFields = false)
public class AliyunOssEndpoint extends AbstractEndpoint {
    private AliyunOssProperties properties;

    public AliyunOssEndpoint() {
        super("oss", false, true);
    }

    @Autowired
    public void setTairProperties(AliyunOssProperties properties) {
        this.properties = properties;
    }

    public Object invoke() {
        Map<String, Object> info = new HashMap<String, Object>();
        info.put("version", "2.0.6");
        info.put("vendor", "http://www.aliyun.com/");
        info.put("bucket", properties.getBucket());
        info.put("upload.success", FileStorageServiceOssImpl.fileUploadSuccess.get());
        info.put("upload.fail", FileStorageServiceOssImpl.fileUploadFail.get());
        info.put("get.count", FileStorageServiceOssImpl.fileGetCounts.get());
        info.put("delete.count", FileStorageServiceOssImpl.fileDeleteCounts.get());
        return info;
    }
}
