package com.soarclient.utils;

import java.util.LinkedList;
import java.util.Queue;

public class CpsCounter {
    private static final Queue<Long> clicks = new LinkedList<>();
    private static final int MAX_CLICKS = 20;

    public static void recordClick() {
        long now = System.currentTimeMillis();
        clicks.add(now);

        // 移除超过1秒的点击记录
        while (!clicks.isEmpty() && now - clicks.peek() > 1000) {
            clicks.poll();
        }

        while (clicks.size() > MAX_CLICKS) {
            clicks.poll();
        }
    }

    public static int getCps() {
        long now = System.currentTimeMillis();

        while (!clicks.isEmpty() && now - clicks.peek() > 1000) {
            clicks.poll();
        }

        return clicks.size();
    }
}
