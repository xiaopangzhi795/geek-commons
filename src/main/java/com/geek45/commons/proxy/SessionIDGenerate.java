package com.geek45.commons.proxy;

import com.google.common.util.concurrent.RateLimiter;

/**
 *  sessio编号生成
 */
public class SessionIDGenerate extends NumberGenerate {

    // 每秒最大支持生成编号个数
    private int limit = 99999;
    // 位数同limit相等
    private String format = "%07d";

    private RateLimiter rateLimiter = RateLimiter.create(limit);

    private static SessionIDGenerate instance;

    public synchronized static NumberGenerate getInstance() {
        if (instance == null) {
            instance = new SessionIDGenerate();
        }
        return instance;
    }

    private SessionIDGenerate() {
    }

    @Override
    public int getLimit() {
        return limit;
    }

    @Override
    public String getFormat() {
        return format;
    }

    @Override
    public RateLimiter getRateLimiter() {
        return rateLimiter;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 103; i++) {
            System.out.println(SessionIDGenerate.getInstance().generateId());
            if (i == 50) {
                Thread.sleep(1000);
            }
        }
    }
}
