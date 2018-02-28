package com.yufeng.strategy;

import org.jooq.tools.StringUtils;
import org.jooq.util.DefaultGeneratorStrategy;
import org.jooq.util.Definition;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author KevinSmith 2017.11.12
 */
@Configuration
public class MyStrategy extends DefaultGeneratorStrategy {
    private String tablePrefix = "tb_";

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    @Override
    public String getJavaClassName(Definition definition, Mode mode) {
        StringBuilder result = new StringBuilder();

        String outputName = definition.getOutputName();
        if (!StringUtils.isBlank(tablePrefix) && outputName.startsWith(tablePrefix)) {
            outputName = outputName.substring(tablePrefix.length());
        }
        result.append(StringUtils.toCamelCase(outputName));

        if (mode == Mode.RECORD) {
            result.append("Record");
        } else if (mode == Mode.DAO) {
            result.append("Dao");
        } else if (mode == Mode.INTERFACE) {
            result.insert(0, "I");
        }
        return result.toString();
    }
}
