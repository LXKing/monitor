package com.rayton.gps.service;

import com.rayton.gps.dao.baseinfo.company.ICompanyDao;
import com.rayton.gps.dao.security.ISecurityDao;
import com.rayton.gps.dao.security.IdentifyDto;
import com.rayton.gps.dao.security.RoleAuthorize;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private ISecurityDao securityDao;
    @Autowired
    private ICompanyDao companyDao;
    @Autowired
    private SecurityService securityService;


    // 设置Realm名称
    @Override
    public void setName(String name) {
        super.setName("customRealm");
    }


    /**
     * 认证信息 认证回调函数 登录时调用
     * 首先根据传入的用户名获取User信息；
     * 如果user为空，那么抛出没找到帐号异常UnknownAccountException；
     * 如果user找到但锁定了抛出锁定异常LockedAccountException；
     * 最后生成AuthenticationInfo信息，交给间接父类AuthenticatingRealm使用CredentialsMatcher进行判断密码是否匹配；
     * 如果不匹配将抛出密码错误异常IncorrectCredentialsException；
     * 另外如果密码重试次数太多将抛出超出重试次数异常ExcessiveAttemptsException；
     * 在组装SimpleAuthenticationInfo信息时，需要传入：身份信息（用户名）、凭据（密文密码）、加密盐（username+salt），
     * CredentialsMatcher使用盐加密传入的明文密码和此处的密文密码进行匹配。
     */

    // 先认证 从数据库查询用户信息 也可以实现AuthenticationToken
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 这里访问一次，可以计数一次，计数大于x时，设置用户被锁定一小时DisabledAccountException

        UsernamePasswordToken uPToken = (UsernamePasswordToken) token;
        String account = uPToken.getUsername();
        String password = String.valueOf(uPToken.getPassword());
        IdentifyDto dto = null;
        try {
            dto = securityDao.login(account, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 这里不用这种方式
        // token是用户输入的，取出身份信息
        // String userName = (String) token.getPrincipal();
        // 密码
        // String password = new String((char[]) token.getCredentials());


        // 根据用户输入查询
        // User userInfo = userDao.getUserByName(userName);
        // 是否从数据库中查询到用户信息
        if (dto == null) {
            throw new UnknownAccountException();
        }

        // if (Boolean.TRUE.equals(userInfo.getLocked())) {
        //     throw new LockedAccountException();
        // }

        // IdentifyDto dto = securityDao.login(account, password);
        // if (dto == null) {
        //     throw new Exception("用户名或密码输入错误！");
        // }
        // 检测密码
        if (!dto.password.equals(password))
            throw new IncorrectCredentialsException("用户名或密码输入错误！");
        // 检查用户服务到期
        if (dto.serviceEndTime != null) {
            Date now = new Date();
            long from = dto.serviceEndTime.getTime();
            long to = now.getTime();
            if (from < to)
                throw new AuthenticationException("用户已过期！");
        }
        // 检查用户禁用否
        if (!dto.isEnable()) {
            throw new LockedAccountException("用户已停用！");
        }

        // 检查企业服务到期
        securityService.checkCompany(dto.companyId);
        //
        // Identity identity = new Identity();
        // identity.setAccount(dto.account);
        // identity.setId(dto.id);
        //
        // identity.setCompanyId(dto.companyId);
        // identity.setUnid(dto.unid);
        // identity.setName(dto.name);
        // identity.setKind(dto.kind);

        // 从数据库查询出来的账号名和密码与用户输入的账号和密码对比
        // 当用户执行登录时 在方法处理上要实现subject.login(token);
        // 然后会自动进入这个类进行认证

        // 通过realm将用户输入的信息和从数据库中查到的数据进行对比
        // 查不到返回null，查询到，返回认证信息AuthenticationInfo
        // 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，觉得不好可以自定义实现

        // 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息
        AuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(dto, // principal
                dto.getPassword(), // hashedCredentials
                // getCredentialsSalt
                // ByteSource.Util.bytes(username + userInfo.getSalt()), // salt=username+salt
                // ByteSource.Util.bytes(userInfo.getSalt()), // credentialsSalt
                this.getName() // realmName
        );
        return simpleAuthenticationInfo;
    }

    /**
     * 只有需要验证权限时才会调用, 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     * 在配有缓存的情况下，只加载一次
     */
    // 再授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        // 从principals获取主身份信息，上边认证填充到SimpleAuthenticationInfo
        IdentifyDto user = (IdentifyDto) principals.getPrimaryPrincipal();
        List<String> permissionStringList = new ArrayList<>();
        // 根据身份信息从数据库中获取权限数据
        if (user.kind == 1) {
            user.setRoles(new String[]{user.companyId});
        } else {
            List<String> roles = null;

            try {
                roles = securityDao.getRoles(user.id);
            } catch (Exception e) {
                e.printStackTrace();
            }

            user.setRoles(roles.toArray(new String[0]));

            List<RoleAuthorize> list = null;

            try {
                list = securityService.roleAuthorizes();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 系统管理员
            if (user.getKind() == 0) {
                list.forEach(roleAuthorize -> permissionStringList.add(roleAuthorize.getPermissionId()));
            } else {
                for (String role : roles) {
                    list.stream().filter(roleAuthorize -> roleAuthorize.getRoleId().equals(role)).forEach
                            (roleAuthorize -> permissionStringList.add(roleAuthorize.getPermissionId()));
                }
            }
        }


        // mmp 谁tm写的

        //
        // Map<String, List<String>> map = new Hashtable<String, List<String>>();
        //
        // list.forEach(roleAuthorize -> {
        //     List<String> roles = map.get(roleAuthorize.getPermissionId());
        //     if (roles == null) {
        //         roles = new ArrayList<>();
        //         map.put(roleAuthorize.getPermissionId(), roles);
        //     }
        //     roles.add(roleAuthorize.getRoleId());
        // });

        // List<String> mmp = map.get(permissionId);


        // List<Role> roleList = userRoleDao.listUserRole(user.getUserId());
        // List<String> roleStringList = new ArrayList<>();
        // roleList.forEach(role -> roleStringList.add(role.getRoleName()));


        // 返回授权信息
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        // 将上边查询到授权信息填充到simpleAuthorizationInfo对象中
        simpleAuthorizationInfo.addStringPermissions(permissionStringList);
        // simpleAuthorizationInfo.addRoles(roleStringList);
        return simpleAuthorizationInfo;
    }

    // 用户正常退出，缓存自动清空
    // 用户非正常退出，缓存自动清空
    // 如果修改了用户的权限，而用户不退出系统，修改的权限无法立即生效，需要手动清除缓存
    // 清除缓存，要放在service中调用
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }


    // 清除用户认证信息
    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principalCollection) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principalCollection, getName());
        super.clearCachedAuthenticationInfo(principals);
    }


    // 清除指定 principalCollection 的权限信息
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }

    // 清除当前用户认证信息
    public void clearCachedAuthenticationInfo() {
        PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principalCollection, getName());
        super.clearCachedAuthenticationInfo(principals);
    }

    // 清除当前用户权限信息
    public void clearCachedAuthorizationInfo() {
        PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }


    // 清除当前用户的认证和授权缓存信息
    public void clearAllCache() {
        clearCachedAuthorizationInfo();
        clearCachedAuthenticationInfo();
    }


}
