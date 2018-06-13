package com.sh.wxa.servlet;

import com.sh.wxa.JsonMessage;
import com.sh.wxa.util.ModuleAssert;
import com.sh.wxa.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MethodInvocation<RT> {

    private static volatile InvocationTargetFactory targetFactory = null;

    private final Object target;

    private final Method method;

    private final Object param;

    private final Object[] extParams;

    private MethodInvocation(Object target, Method method, Object param, Object[] extParams) {
        super();
        this.target = target;
        this.method = method;
        this.param = param;
        this.extParams = extParams;
    }

    @SuppressWarnings("unchecked")
    public RT execute() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (this.param == SpecialCase.VOID_PARAM) {
            return (RT) this.method.invoke(target);
        }
        List<Object> paramList = new ArrayList<Object>();
        if (extParams != null) {
            for (Object extParam : this.extParams) {
                paramList.add(extParam); // null allowed to hold place
            }
        }
        if (this.param != SpecialCase.ONLY_EXT_PARAM) {
            paramList.add(0, this.param);
        }
        return (RT) this.method.invoke(target, paramList.toArray(new Object[0]));
    }

    public static <RT> MethodInvocation<RT> create(String module, String paramJson, Object... extParams) {

        ModuleAssert.assertTrue(!StringUtils.isEmpty(module), "module is empty");
        String[] tmp = module.split("\\.");
        ModuleAssert.assertTrue(tmp.length >= 2, "module format error:" + module);

        String targetName = tmp[0];
        final String targetMethodName = tmp[1];

        Object targetObj = targetFactory.create(targetName);
        ModuleAssert.assertNotNull(targetObj, "targetFactory.create result is null");
        Method method = findMethod(targetMethodName, targetObj.getClass());
        if (method == null) {
            return null;
        }
        Object paramObj = createParamObj(paramJson, method, extParams);

        return new MethodInvocation<RT>(targetObj, method, paramObj, extParams);
    }

    public static void setTargetFactory(InvocationTargetFactory targetFactory) {
        MethodInvocation.targetFactory = targetFactory;
    }

    private static Method findMethod(final String targetMethodName, Class<?> targetClass) {
        Method method = null;
        Method[] methods = targetClass.getMethods();
        for (Method m : methods) {
            if (m.getName().equalsIgnoreCase(targetMethodName)) {
                method = m;
                break;
            }
        }
        return method;
    }

    private static Object createParamObj(String paramJson, Method method, Object... extParams) {
        Class<?>[] paramTypes = method.getParameterTypes();
        if (paramTypes.length <= 0) {
            return SpecialCase.VOID_PARAM;
        }
        // Assume all the params are ext params.
        if (paramTypes.length == extParams.length) {
            assertTypeMatch(paramTypes, 0, extParams);
            return SpecialCase.ONLY_EXT_PARAM;
        }
        // Assume the first param need to be converted from params map.
        int expectExtParamNumber = paramTypes.length - 1;
        if (expectExtParamNumber != extParams.length) {
            throw new IllegalArgumentException("wrong number of ext params. method: " + method.getName()
                    + ", expect: " + expectExtParamNumber + ", but: " + extParams.length);
        } else if (extParams.length > 0) {
            assertTypeMatch(paramTypes, 1, extParams);
        }

        return JsonMessage.fromJsonString(paramJson, paramTypes[0]);
    }

    private static void assertTypeMatch(Class<?>[] paramTypes, int start, Object[] extParams) {
        for (int i = start; i < paramTypes.length; ++i) {
            int extParamIdx = i - start;
            if (extParams[extParamIdx] == null) {
                continue; // cannot check null object's type
            }
            if (paramTypes[i] != extParams[extParamIdx].getClass()) {
                throw new IllegalArgumentException("Param " + (i + 1) + " type mismatch, expect: "
                        + paramTypes[i].getName() + " but: " + extParams[extParamIdx].getClass().getName());
            }
        }
    }

    private enum SpecialCase {
        VOID_PARAM,
        ONLY_EXT_PARAM,
    }

}
