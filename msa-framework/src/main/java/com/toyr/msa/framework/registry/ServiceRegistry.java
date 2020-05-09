package com.toyr.msa.framework.registry;

/**
 * @author unisk1123
 * @Description 服务注册表
 * @create 2020-05-09 5:16 PM
 */
public interface ServiceRegistry {
    /**
     * 服务注册表
     *
     * @param serviceName
     * @param serviceAddress
     */
    void register(String serviceName, String serviceAddress);
}
