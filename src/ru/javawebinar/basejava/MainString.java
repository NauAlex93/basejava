package ru.javawebinar.basejava;

public class MainString {
    public static void main(String[] args) {
        String[] strArray = new String[]{"1", "2", "3", "4", "5"};
        StringBuilder sb = new StringBuilder();

        String str1 = "abc";
        String str3 = "c";
        String str2 = ("ab" + str3).intern();
        System.out.println(str1 == str2);

        for (String str : strArray) {
            sb.append(str).append(", ");
        }
        System.out.println(sb);
    }
}
