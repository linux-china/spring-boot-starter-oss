package org.mvnsearch.boot.oss;

import javax.activation.DataSource;
import java.io.IOException;

/**
 * file storage service
 *
 * @author linux_china
 */
public interface FileStorageService {

    /**
     * save file
     *
     * @param ds data source
     * @return file name
     * @throws IOException IO Exception
     */
    public String save(DataSource ds) throws IOException;

    /**
     * delete file
     *
     * @param fileName file name
     * @throws IOException IO Exception
     */
    public void delete(String fileName) throws IOException;

    /**
     * get file data source
     *
     * @param fileName file name
     * @return file data source
     * @throws IOException IO Exception
     */
    public DataSource get(String fileName) throws IOException;

    public void rename(String oldName, String newName) throws IOException;
}
