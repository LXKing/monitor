package mmp.gps.logic.portal;

import mmp.gps.common.util.JsonMapper;
import mmp.gps.domain.app.AppRequest;
import mmp.gps.domain.app.AppResponse;
import mmp.gps.logic.Identity;
import mmp.gps.logic.cache.Authorizes;
import mmp.gps.logic.component.ServiceComponents;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class ServicePortal {

    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static Validator validator = factory.getValidator();


    public AppResponse execute(AppRequest request) {
        String token = request.getToken();
        Identity identity = Identity.parse(token);
        AppResponse response = new AppResponse();
        Object result = null;

        try {
            ServiceMethodAgent e = ServiceComponents.getMethod(request.getMethod());
            if (e == null) {
                throw new Exception("此服务方法不存在！");
            }

            if (!e.isAllowAnoumous()) {
                if (identity == null) {
                    throw new Exception("非法用户，请登录认证！");
                }

                if (!Authorizes.isInRole(request.getMethod(), identity.getRoles())) {
                    throw new Exception("操作未授权！");
                }
            }

            Class[] var21 = e.getParameterTypes();
            if (var21 != null && var21.length > 0) {
                ArrayList parameters = new ArrayList(var21.length);
                Class[] var9 = var21;
                int var10 = var21.length;

                for (int var11 = 0; var11 < var10; ++var11) {
                    Class type = var9[var11];
                    if (type == AppRequest.class) {
                        parameters.add(request);
                    } else if (type == Identity.class) {
                        if (identity == null) {
                            throw new Exception("非法用户，请登录认证！");
                        }

                        parameters.add(identity);
                    } else {
                        Object parameter = JsonMapper.convertValue(request.getParameter(), type);
                        if (!this.validate(response, parameter)) {
                            return response;
                        }

                        parameters.add(parameter);
                    }
                }

                result = e.getMethod().invoke(e.getInstance(), parameters.toArray());
            } else {
                result = e.getMethod().invoke(e.getInstance(), new Object[0]);
            }
        } catch (JsonParseException var14) {
            response.setErrorMessage("Json解析错误：" + var14.getMessage());
        } catch (JsonMappingException var15) {
            response.setErrorMessage("JsonMapping错误：" + var15.getMessage());
        } catch (IOException var16) {
            response.setErrorMessage("IO错误：" + var16.getMessage());
        } catch (IllegalAccessException var17) {
            response.setErrorMessage("非法存取错误：" + var17.getMessage());
        } catch (IllegalArgumentException var18) {
            response.setErrorMessage("无效参数错误：" + var18.getMessage());
        } catch (InvocationTargetException var19) {
            Throwable target = var19.getTargetException();
            if (target == null) {
                response.setErrorMessage(var19.getMessage());
            } else {
                response.setErrorMessage(target.getMessage());
            }
        } catch (Exception var20) {
            response.setErrorMessage(var20.getMessage());
        }

        response.setResult(result);
        return response;
    }

    private boolean validate(AppResponse response, Object obj) {
        Set constraintViolations = validator.validate(obj, new Class[0]);
        if (constraintViolations.isEmpty()) {
            return true;
        } else {
            StringBuilder builder = new StringBuilder();
            Iterator iterator = constraintViolations.iterator();

            while (iterator.hasNext()) {
                ConstraintViolation item = (ConstraintViolation) iterator.next();
                String property = item.getPropertyPath().toString();
                String error = item.getMessage();
                builder.append(property).append(":").append(error).append("\r\n");
            }

            response.setErrorMessage(builder.toString());
            return false;
        }
    }

}
