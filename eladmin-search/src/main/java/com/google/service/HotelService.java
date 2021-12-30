package com.google.service;

import java.io.IOException;

/**
 * @author iris
 */
public interface HotelService {
    Boolean indexIsExist(String indexName) throws IOException;

    void deleteIndex(String indexName) throws IOException;

    void createIndex(String mappings, String indexName) throws IOException;
}
