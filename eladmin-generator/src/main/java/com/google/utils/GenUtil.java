package com.google.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SuppressWarnings({"unchecked", "all"})
public class GenUtil {

    private static final String TIMESTAMP = "Timestamp";

    private static final String BIGDECIMAL = "BigDecimal";

    public static final String PK = "PRI";

    public static final String EXTRA = "auto_increment";

    /**
     * 获取后端代码模板名称
     * @return List
     */
    private static List<String> getAdminTemplateNames() {
        List<String> templateNames = new ArrayList<>();
        templateNames.add("Entity");
        templateNames.add("Dto");
        templateNames.add("Mapper");
        templateNames.add("Controller");
        templateNames.add("QueryCriteria");
        templateNames.add("Service");
        templateNames.add("ServiceImpl");
        templateNames.add("Repository");
        return templateNames;
    }

    /**
     * 获取前端代码模板名称
     *
     * @return List
     */
    private static List<String> getFrontTemplateNames() {
        List<String> templateNames = new ArrayList<>();
        templateNames.add("index");
        templateNames.add("api");
        return templateNames;
    }
}

