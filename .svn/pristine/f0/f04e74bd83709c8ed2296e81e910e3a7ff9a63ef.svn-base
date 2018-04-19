package com.rayton.gps.configuration;


import com.rayton.gps.service.shiro.ShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class ShiroConfig {

    private Logger logger = LoggerFactory.getLogger(ShiroConfig.class);


    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/home");
        // 未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/");

        // 拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

        // 配置不会被拦截的链接 顺序判断

        // 此处声明关系也可是已配置在数据库中的

        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/code.verify", "anon");

        filterChainDefinitionMap.put("/static/** ", "anon");
        filterChainDefinitionMap.put("/**", "user");
        // filterChainDefinitionMap.put("/example/admin", "authc,roles[admin]");
        // filterChainDefinitionMap.put("/example/admin", "authc,perms[admin:view:*]");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);


        logger.warn("Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm
        securityManager.setRealm(myShiroRealm());
        // 自定义缓存实现
        // securityManager.setCacheManager(cacheManager());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(SessionManager());
        // 注入记住我管理器
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }


    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new
                AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }

    @Bean
    public ShiroRealm myShiroRealm() {
        ShiroRealm myShiroRealm = new ShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }


    // public RedisManager redisManager() {
    //     RedisManager redisManager = new RedisManager();
    //     // redisManager.setHost(host);
    //     // redisManager.setPort(port);
    //     // redisManager.setExpire(1800);
    //     // redisManager.setTimeout(timeout);
    //     // redisManager.setPassword(password);
    //     return redisManager;
    // }

    //
    // public RedisCacheManager cacheManager() {
    //     RedisCacheManager redisCacheManager = new RedisCacheManager();
    //     redisCacheManager.setRedisManager(redisManager());
    //     return redisCacheManager;
    // }

    // @Bean
    // public EhCacheManager ehCacheManager() {
    //     EhCacheManager cacheManager = new EhCacheManager();
    //     // cacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
    //     return cacheManager;
    // }

    // @Bean
    // public RedisSessionDAO redisSessionDAO() {
    //     RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
    //     redisSessionDAO.setRedisManager(redisManager());
    //     return redisSessionDAO;
    // }


    @Bean
    public DefaultWebSessionManager SessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        // sessionManager.setSessionDAO(redisSessionDAO());
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setGlobalSessionTimeout(3600000);
        return sessionManager;
    }


    public SimpleCookie rememberMeCookie() {
        // cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        // 记住我cookie生效时间30天 ,单位秒
        simpleCookie.setMaxAge(86400);
        return simpleCookie;
    }


    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        // rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }


}
