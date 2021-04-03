package com.geek45.commons.db;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;

@Slf4j
@ToString(exclude = {"connection", "statement", "hasResultSet"}, callSuper = true)
public class DbUtil {

    @Getter
    @Setter
    private String url;
    @Getter
    @Setter
    private String driverName;
    @Getter
    @Setter
    private String userName;
    @Getter
    @Setter
    private String userPwd;
    private Connection connection;
    private Statement statement;
    @Getter
    private boolean hasResultSet;

    private DbUtil(String url, String driverName, String userName, String userPwd) {
        this.url = url;
        this.driverName = driverName;
        this.userName = userName;
        this.userPwd = userPwd;
    }


    public static DbUtil createDefault(String userName, String password, String diverName, String url) {
        return new DbUtil(url, diverName, userName, password);
    }

    public boolean conn() {
        try {
            if (StringUtils.isBlank(url) || StringUtils.isBlank(driverName) || StringUtils.isBlank(userName) || StringUtils.isBlank(userPwd)) {
                log.info("信息不全，无法打开链接，请先补全db信息，driver，url，username，password四个信息");
                return false;
            }
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, userName, userPwd);
            statement = connection.createStatement();
            log.info("打开数据库连接");
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public boolean close() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            log.info("数据库连接关闭成功");
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public boolean execute(String sql) {
        try {
            if (statement == null) {
                log.info("请先打开数据库连接");
                return false;
            }
            hasResultSet = statement.execute(sql);
            return hasResultSet;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public JSONArray getResult() {
        try {
            if (statement == null) {
                log.info("请先执行sql再获取结果");
                return null;
            }
            JSONArray jsonArray = new JSONArray();
            if (hasResultSet) {
                try (ResultSet rs = statement.getResultSet()) {
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnCount = rsmd.getColumnCount();
                    while (rs.next()) {
                        JSONObject data = new JSONObject();
                        for (int i = 0; i < columnCount; i++) {
                            String columnName = rsmd.getColumnName(i + 1);
                            Object columnValue = rs.getObject(i + 1);
                            data.put(columnName, columnValue);
                        }
                        jsonArray.add(data);
                    }
                    return jsonArray;
                }
            } else {
                JSONObject data = new JSONObject();
                data.put("count", statement.getUpdateCount());
                jsonArray.add(data);
                return jsonArray;
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

}
