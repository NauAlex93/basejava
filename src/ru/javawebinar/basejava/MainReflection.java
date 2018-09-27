package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws Exception {
        Resume resume = new Resume("AN");
        System.out.println(resume);

        Method method = resume.getClass().getMethod("toString");
        System.out.println(method.invoke(resume));
    }
}
