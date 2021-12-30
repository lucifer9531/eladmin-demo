package com.google.service.impl;

import com.google.exception.BadRequestException;
import com.google.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.xcontent.XContentType;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author iris
 */
@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final RestHighLevelClient restHighLevelClient;

    @Override
    public Boolean indexIsExist(String indexName) throws IOException {
        GetIndexRequest request = new GetIndexRequest(indexName);
        return restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
    }

    @Override
    public void deleteIndex(String indexName) throws IOException {
        Boolean isExist = this.indexIsExist(indexName);
        if (!isExist) {
            throw new BadRequestException("此索引库不存在！");
        }
        DeleteIndexRequest request = new DeleteIndexRequest(indexName);
        restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
    }

    @Override
    public void createIndex(String mappings, String indexName) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        request.source(mappings, XContentType.JSON);
        restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
    }
}
