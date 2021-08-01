package databaseFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatabaseStructures {
    // table : <colname, value>
    public Map<String, List<Map<String, String>>> databaseData = new HashMap<>();
    // table : <col, datatype>
    public Map<String, Map<String, String>> tableStructures = new HashMap<>();
    public Map<String, String> primaryKeyMap = new HashMap<>();
    public Map<String, String> foreignKeyMap = new HashMap<>(); // doubtful

    String pathToDatabases = "src/main/java/databaseFiles";

    /*
    Loads the database structures from files
     */
    public void loadDatabase (String databaseName) {
        File dir = new File(pathToDatabases+"/"+databaseName);
        /*
        load databaseData
         */
        for (File table : dir.listFiles()) {
            String tableName = table.getName().split("\\.")[0];
            if (tableName.equals("METADATA"))
                continue;
            List<Map<String, String>> tableData = new ArrayList<>();

            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(table));
                String line = bufferedReader.readLine();
                String[] columns = {};
                boolean firstLine = true;
                while (line!=null) {
                    if (firstLine) {
                        columns = line.split("\\|");
//                        System.out.println(line);
//                        System.out.println(columns.length);
                        firstLine = false;
                    }
                    else {
                        String[] cells = line.split("\\|");
                        Map<String, String> row = new HashMap<>();
                        for (int i = 0; i < columns.length; i++) {
                            row.put(columns[i], cells[i]);
                        }
                        tableData.add(row);
                    }
                    line = bufferedReader.readLine();
                }
            }
            catch (IOException io) {
                System.out.println("Exception in reading table file : "+io.getMessage());
            }
            if (!tableData.isEmpty()) {
                databaseData.put(tableName, tableData);
//                System.out.println("Table entered to dbData...");
            }
        }

        /*
        load tableStructures
         */
        loadTableStructure(dir);

    }

    private void loadTableStructure (File directory) {
        String fileName = "";
        for (File file : directory.listFiles()) {
            fileName = file.getName().split("\\.")[0];
            if (fileName.equals("METADATA")) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    String line = bufferedReader.readLine();
                    while (line!=null) {
                        Map<String, String> table = new HashMap<>();
//                        Pattern pattern = Pattern.compile("(.*)\\s*\\=\\s*\\{(.*)\\s*\\}");
                        Pattern pattern = Pattern.compile("(.*)\\s+\\=\\s+\\{(.*)\\s+}\\s+\\|\\s+\\{\\s+(.*)\\s+}");
                        Matcher matcher = pattern.matcher(line);
                        matcher.find();

                        String tableName = matcher.group(1);
                        String data = matcher.group(2).replaceAll("\\s+", "");
                        String keys = matcher.group(3).replaceAll("\\s+", "");

                        String[] cols = data.split(",");
                        for (String col : cols) {
                            String[] parts = col.split(":");
                            table.put(parts[0], parts[1]);
                        }
                        tableStructures.put(tableName, table);

                        if (keys.length()!=0) {
                            // call set PK and FK
                            loadKeys(keys, tableName);
                        }

                        line = bufferedReader.readLine();
                    }
                }
                catch (IOException io) {
                    System.out.println("Exception reading the METADATA file : "+io.getMessage());
                }
                break;
            }
        }
    }

    private void loadKeys (String keys, String tableName) {
        keys = keys.replaceAll("\\s+", "");
        String[] keysParts = keys.split("-");
        String PK = keysParts[0];
        primaryKeyMap.put(tableName, PK.split(":")[0]);

        if (keysParts.length>1) {
            String FK = keysParts[1];
        }
    }

    /*
    Write the database structures back to files
     */
    public void storeDatabase (String databaseName) {

    }
}
