package sql;

import java.util.HashMap;
import java.util.Map;

public class Query {
    private String databaseName;
    private String tableName;
    private String columns;
    private String condition;
    private String type;
    private String primarykey;
    private Map<String, String> conditionMap;
    private Map<String, String> optionMap;
    private Map<String, String> valueMap;
    private Map<String,String> FKMap = new HashMap<>();
    private Map<String, String> columnNameType;

    public Map<String, String> getFKMap() {
        return FKMap;
    }

    public void setFKMap(Map<String, String> FKMap) {
        this.FKMap = FKMap;
    }

    public String getPrimarykey() {
        return primarykey;
    }

    public void setPrimarykey(String primarykey) {
        this.primarykey = primarykey;
    }

    public Map<String, String> getColumnNameType() {
        return columnNameType;
    }

    public Map<String, String> getValueMap() {
        return valueMap;
    }

    public void setColumnNameType(Map<String, String> columnNameType) {
        this.columnNameType = columnNameType;
    }

    public String getType(){return type;}

    public void setType(String type) {
        this.type = type;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Map<String, String> getConditionMap() {
        return conditionMap;
    }

    public void setConditionMap(Map<String, String> conditionMap) {
        this.conditionMap = conditionMap;
    }

    public Map<String, String> getOptionMap() {
        return optionMap;
    }

    public void setOptionMap(Map<String, String> optionMap) {
        this.optionMap = optionMap;
    }

    public void setValueMap(Map<String,String> valueMap)
    {
        this.valueMap=valueMap;
    }
}
