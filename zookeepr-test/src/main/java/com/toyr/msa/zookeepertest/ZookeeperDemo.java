package com.toyr.msa.zookeepertest;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author unisk1123
 * @Description
 * @create 2020-05-09 3:54 PM
 */
public class ZookeeperDemo {

    private static final String CONNECTION_STRING = "127.0.0.1:2181";
    private static final int SESSION_TIMEOUT = 5000;
    private static CountDownLatch latch = new CountDownLatch(1);
    private static CountDownLatch latch2 = new CountDownLatch(1);
    private static CountDownLatch latch3 = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {

        // 连接Zookeeper
        ZooKeeper zk = new ZooKeeper(CONNECTION_STRING, SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                    latch.countDown();
                }
            }
        });

        latch.await();
        // 获取ZooKeeper客户端对象
        System.out.println(zk);

        // 同步方式获取根节点下所有子节点
        List<String> children = zk.getChildren("/", null);
        for (String node : children) {
            System.out.println(node);
        }

        // 异步方式获取根节点下所有子节点
        zk.getChildren("/", null, new AsyncCallback.ChildrenCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, List<String> children) {
                // rc表示ResultCode，用于识别方法调用的响应结果 0：成功，-4：客户端与服务端已断开连接，-110：指定节点已存在 -112：会话已过期
                for (String node : children) {
                    System.out.println("rc:" + rc + "path:" + path + "ctx:" + ctx + node);
                }
                latch2.countDown();
            }
        }, null);
        latch2.await();

        // 可以获取Stat对象
        zk.getChildren("/", null, new AsyncCallback.Children2Callback() {
            @Override
            public void processResult(int i, String s, Object o, List<String> list, Stat stat) {
                for (String node : list) {
                    System.out.println(node);
                }
                latch3.countDown();
            }
        }, null);
        latch3.await();


        // 通过同步方式判断节点是否已存在
        Stat stat = zk.exists("/", null);
        if (stat != null) {
            System.out.println("node exists");
        } else {
            System.out.println("node does not exists");
        }

        // 通过异步方式判断节点是否已存在
        zk.exists("/", null, new AsyncCallback.StatCallback() {
            @Override
            public void processResult(int i, String s, Object o, Stat stat) {
                if (stat != null) {
                    System.out.println("node exists");
                } else {
                    System.out.println("node does not exists");
                }
            }
        }, null);

        String name = zk.create("/foo", "hello".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zk.create("/foo", "hello".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, new AsyncCallback.StringCallback() {
            @Override
            public void processResult(int i, String s, Object o, String s1) {
                System.out.println(s1);
            }
        }, null);
    }
}
