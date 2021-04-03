package com.geek45.commons.ssh;


import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.geek45.commons.http.HttpClientUtil;
import com.geek45.commons.serializ.SerializableUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SSHUtil {
    public static void main(String[] args) {
        HttpClientUtil.sendDingdingMsgUrl("https://oapi.dingtalk.com/robot/send?access_token=1e33d71987216b7b2dbc26974f0ee1da74c3e94669cea3fb40bcd1c47a4f3edb", "【预警工具类】订单号请求次数超过5次:");
    }

    private long dateFlg = 0L;

    public long diffDateFlg(){
        return System.currentTimeMillis() - dateFlg;
    }

    public Map<String, Object> getData(){
        Map<String, Object> maps = new HashMap<>();
        maps.put("data", map);
        return maps;
    }

    private String username;
    private String password;
    private String ip;
    private Integer port = null;
    private Connection conn = null;
    private Session ss = null;
    private boolean isConn = false;
    public SSHUtil createConnNoPort(String ip, String username, String password) {
        this.username = username;
        this.password = password;
        this.ip = ip;
        conn = new Connection(ip);
        initData();
        return init();
    }

    public SSHUtil createConn(String ip, Integer port, String username, String password) {
        this.username = username;
        this.password = password;
        this.ip = ip;
        this.port = port;
        conn = new Connection(ip, port);
        initData();
        return init();
    }

    public void close(){
        conn.close();
        isConn = false;
        shutdown();
    }



    public void reConn(){
        log.info("重新连接");
        init();
    }

    public boolean isConn() {
        return isConn;
    }

    public Map<String, Integer> map = new HashMap<>();

    public void getMap(){
        System.err.println(JSONObject.toJSONString(map));
    }

    public void clear(){
        map.clear();
    }

    private void addOrder(String order) {
        if (map.containsKey(order)) {
            Integer count = map.get(order);
            map.put(order, count + 1);
            if (count > 15) {
                HttpClientUtil.sendDingdingMsgUrl("https://oapi.dingtalk.com/robot/send?access_token=1e33d71987216b7b2dbc26974f0ee1da74c3e94669cea3fb40bcd1c47a4f3edb", "【预警工具类】订单号请求次数超过5次:" + order);
            }
        } else {
            map.put(order, 1);
        }
    }

    public JSONArray execute(String command) {
        JSONArray lines = new JSONArray();
        try {
            ss=conn.openSession();
            ss.execCommand(command);
            InputStream is = new StreamGobbler(ss.getStdout());
            BufferedReader bs = new BufferedReader(new InputStreamReader(is));
            while(true){
                String line=bs.readLine();
                if (StringUtils.isBlank(line)) {
                    break;
                } else {
                    if (line.contains(":开始请求Rep-mobile")) {
                        String orderNum = line.substring(line.indexOf("T"), line.lastIndexOf(":"));
                        addOrder(orderNum);
                    }
                    System.out.println(line);
                    dateFlg = System.currentTimeMillis();
                    lines.add(line);
                }
            }
            bs.close();
        } catch (Exception e) {
            log.error("执行异常");
            close();
            lines.add("执行异常");
        }finally {
            ss.close();
        }
        return lines;
    }

    private Thread thread = null;

    public void executeSync(String command) {
        thread = new Thread(() -> execute(command));
        thread.start();
    }

    private void initData(){
        Map<String, Integer> maps = (Map<String, Integer>) SerializableUtil.deserialization("F:\\serialization\\data");
        if (maps == null) {
            map = new HashMap<>();
        }else{
            map = maps;
        }
    }

    private SSHUtil init() {
        try {
            conn.connect();
            isConn = conn.authenticateWithPassword(this.username, password);
        } catch (Exception e) {
            log.info("连接失败");
        }
        return this;
    }


    public void shutdown() {
        thread.interrupt();
        log.info("结束");
    }
}
