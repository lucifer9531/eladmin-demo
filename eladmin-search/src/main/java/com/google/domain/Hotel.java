package com.google.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author iris
 */
@Document(indexName = "hotel", shards = 1, replicas = 0)
@Data
public class Hotel implements Serializable {
    @Id
    @Field(type = FieldType.Keyword)
    private Long id;

    @Field(analyzer = "ik_max_world", type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Keyword, analyzer = "false")
    private String address;

    @Field(type = FieldType.Integer)
    private Integer price;

    @Field(type = FieldType.Integer)
    private Integer score;

    @Field(type = FieldType.Keyword)
    private String brand;

    @Field(type = FieldType.Keyword)
    private String city;

    @Field(type = FieldType.Keyword)
    private String starName;

    @Field(type = FieldType.Keyword)
    private String business;

    @Field(type = FieldType.Keyword, analyzer = "false")
    private String pic;
}
