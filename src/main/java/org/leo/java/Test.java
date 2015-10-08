package org.leo.java;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		Animal felid = new Felid();
		Animal cat = new Cat();

		System.out.println(felid instanceof Animal);
		System.out.println(cat instanceof Animal);
		System.out.println(cat instanceof Cat);
		System.out.println("------------------------------------");
		System.out.println(Animal.class.isInstance(felid));
		System.out.println(Animal.class.isInstance(cat));
		System.out.println(Cat.class.isInstance(cat));		
		System.out.println("------------------------------------");
		System.out.println(Felid.class.isAssignableFrom(Animal.class));
		System.out.println(Cat.class.isAssignableFrom(Animal.class));
		System.out.println(Cat.class.isAssignableFrom(Felid.class));
		System.out.println("------------------------------------");
		System.out.println(Animal.class.isAssignableFrom(Felid.class));
		System.out.println(Animal.class.isAssignableFrom(Cat.class));
		System.out.println(Felid.class.isAssignableFrom(Cat.class));
		System.out.println("------------------------------------");
		System.out.println(DC.class.isAssignableFrom(Felid.class));
		System.out.println(DC.class.isAssignableFrom(Cat.class));
		System.out.println("------------------------------------");
		
		
		/**
		instanceof����� ֻ�����ڶ������ñ����������ߵı����Զ��� �ǲ��� �ұ����ӿڵ� ʵ������������������nullֵ������Խ������false�� 
		����أ�����ʵ��������ʵ�� instanceof ������  ����true 
		���� 
		*/
		String s1 = new String("javaisland"); 
		System.out.println(s1 instanceof String); //true 

		/**
		Class���isInstance(Object obj)������obj�Ǳ����ԵĶ������obj�ǵ������������class��ӿ� ��ʵ�����򷵻�true�����������instanceof������Ķ�̬�ȼۡ� 
		����أ�������.class.isInstance(����ʵ��������ʵ��)  ����true 
		����
		*/
		Object s2 = new String("javaisland"); 
		System.out.println(String.class.isInstance(s2)); //true 

		/**
		Class���isAssignableFrom(Class cls)����������������������class��ӿ� �� ����cls��ʾ�����ӿ���ͬ�������ǲ���cls��ʾ�����ӿڵĸ��࣬�򷵻�true�� 
		����أ�������.class.isAssignableFrom(�����������.class)  ����true 
		����		
		*/
		System.out.println(ArrayList.class.isAssignableFrom(Object.class));  //false 
		System.out.println(Object.class.isAssignableFrom(ArrayList.class));  //true

	}

}
