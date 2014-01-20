package org.leo.proxy.person;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

//
/**
 * ʹ��cglib�ṩ�Ĵ������(���������Ҫʵ��MethodInterceptor�ӿڣ�Ŀ�������Ҫʵ�ֽӿ�)
 * ������cglib-nodep-2.2.3.jar
 * ע�⣺Ŀ�������ʵ�ֽӿڡ���Ϊ���ɵĴ������ʱĿ���������ࡣ
 * 
 * @author leo.li
 * Modify Time Dec 24, 2013 4:31:07 PM
 */
public class PersonProxyCglib implements MethodInterceptor {
  private Object targetObj;

  /**
   * ���ɵĴ��������ʵ����Ŀ����������
   * @param obj
   *            ���������
   * @return ��������ʵ��
   */
  public Object createProxyInstance(Object obj) {
      this.targetObj = obj;
      Enhancer enhancer = new Enhancer();// �������ɴ������
      enhancer.setSuperclass(this.targetObj.getClass());// ���ô������ĸ���
      enhancer.setCallback(this);// ���ô������Ļص��������Ǳ���
      return enhancer.create();// ���ɴ������
  }

  /*
   * ���������ķ���������ʱ���ͻ�ִ�иĴ������Ļص�������Ҳ����intercept���� ����ص��������մ�����󴫵����Ĳ���
   */
  @Override
  public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
      Object result = null;
      System.out.print("����#");
      result = proxy.invokeSuper(obj, args);
      System.out.print("#��");
      return result;
  }

}
