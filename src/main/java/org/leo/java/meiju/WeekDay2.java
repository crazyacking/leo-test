package org.leo.java.meiju;

public enum WeekDay2 {
	
	MON(1), TUS(), WED(3), THI, FRI, SAT, SUN;// ���Լ�;Ҳ���Բ���
	
	private WeekDay2() {
		System.out.println("���������Ĺ��췽��!");
	}

	private WeekDay2(int i) {
		System.out.println("�������Ĺ��췽�� !" + i);
	}
}
