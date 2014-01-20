package org.leo.proxy.person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ʹ��jdk�ṩ�Ĵ������(���������Ҫʵ��InvocationHandler�ӿڣ�Ŀ��������ʵ�������ӿ�)
 * ע�⣺Ŀ��������ʵ�ֽӿڡ���Ϊ��Ҫ�õ��ӿڵ����з�����Ȼ����е��˽ӿڵ����з�����
 * 
 * Modify Time Dec 24, 2013 4:29:14 PM
 */
public class PersonProxyJdk implements InvocationHandler {
    private Object targetObj;

    /**
     *
     * @param obj
     *            ���������
     * @return ��������ʵ��
     */
    public Object createProxyInstance(Object obj) {
        this.targetObj = obj;
        /*
         * ����˵�� ����һ��ClassLoader,�����������������
         * ��������Class<?>,������Ҫʵ�ֵĽӿ��б�
         * ��������InvocationHandler��ָ�ɷ������õĵ��ô������ ��ָ�������ĸ����invoke������
         */
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), this.targetObj.getClass().getInterfaces(), this);
    }

    /*
     * ���������ķ���������ʱ���ͻ�ִ�лص�����invoke������������ص�������ȥִ��Ŀ������ָ��������
     * ���һὫ���������յ��Ĳ������ݸ�Ŀ����롣 ����һ���ص�������
     * ע�⣺method args �����ɵ��˴�����������ġ�������ȷ���ġ�
     * ������������ص���������һЩ�ֽţ��������Ƶ��˻���������
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.print("����*");
        result = method.invoke(targetObj, args);
        System.out.println("*��");
        return result;
    }

}    
