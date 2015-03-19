package com.read.test.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.concurrent.ConcurrentHashMap;

public class EmployeeCache {
    static private EmployeeCache cache;// һ��Cacheʵ��
    private ConcurrentHashMap employeeRefs;// ����Cache���ݵĴ洢
    private ReferenceQueue q;// ����Reference�Ķ���

    // �̳�SoftReference��ʹ��ÿһ��ʵ�������п�ʶ��ı�ʶ��
    // ���Ҹñ�ʶ������HashMap�ڵ�key��ͬ��
    private class EmployeeRef extends SoftReference {
        private String _key = "";

        public EmployeeRef(Employee em, ReferenceQueue q) {
            super(em, q);
            _key = em.getID();
        }
    }

    // ����һ��������ʵ��
    private EmployeeCache() {
        employeeRefs = new ConcurrentHashMap();
        q = new ReferenceQueue();
    }

    // ȡ�û�����ʵ��
    public static EmployeeCache getInstance() {
        if (cache == null) {
            cache = new EmployeeCache();
        }
        return cache;
    }

    // �������õķ�ʽ��һ��Employee�����ʵ���������ò����������
    private void cacheEmployee(Employee em) {
        cleanCache();// �����������
        EmployeeRef ref = new EmployeeRef(em, q);
        employeeRefs.put(em.getID(), ref);
    }

    // ������ָ����ID�ţ����»�ȡ��ӦEmployee�����ʵ��
    public Employee getEmployee(String ID) {
        Employee em = null;
        // �������Ƿ��и�Employeeʵ���������ã�����У�����������ȡ�á�
        if (employeeRefs.containsKey(ID)) {
            EmployeeRef ref = (EmployeeRef) employeeRefs.get(ID);
            em = (Employee) ref.get();
        }
        // ���û�������ã����ߴ��������еõ���ʵ����null�����¹���һ��ʵ����
        // �����������½�ʵ����������
        if (em == null) {
            em = new Employee(ID);
            System.out.println("Retrieve From EmployeeInfoCenter. ID=" + ID);
            this.cacheEmployee(em);
        }
        return em;
    }

    // �����Щ�������õ�Employee�����Ѿ������յ�EmployeeRef����
    private void cleanCache() {
        EmployeeRef ref;
        while ((ref = (EmployeeRef) q.poll()) != null) {
            employeeRefs.remove(ref._key);
        }
    }

    // ���Cache�ڵ�ȫ������
    public void clearCache() {
        cleanCache();
        employeeRefs.clear();
        System.gc();
        System.runFinalization();
    }

    class Employee {
        private String id;// ��Ա�ı�ʶ����
        private String name;// ��Ա����
        private String department;// �ù�Ա���ڲ���
        private String Phone;// �ù�Ա��ϵ�绰
        private int salary;// �ù�Աн��
        private String origin;// �ù�Ա��Ϣ����Դ

        // ���췽��
        public Employee(String id) {
            this.id = id;
            getDataFromlnfoCenter();
        }

        // �����ݿ���ȡ�ù�Ա��Ϣ
        private void getDataFromlnfoCenter() {
            // �����ݿ⽨�����Ӿ���ѯ�ù�Ա����Ϣ������ѯ�����ֵ
            // ��name��department��plone��salary�ȱ���
            // ͬʱ��origin��ֵΪ"From DataBase"
        }

        public String getID() {
            return id;
        }
    }
}