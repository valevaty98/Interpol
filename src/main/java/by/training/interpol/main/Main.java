package by.training.interpol.main;

import by.training.interpol.hash.EncodePasswordException;
import by.training.interpol.hash.HashGenerator;

public class Main {
    public static void main(String[] args) throws EncodePasswordException {
        System.out.println(HashGenerator.encodePassword("passvl"));
        System.out.println(HashGenerator.encodePassword("jdbc"));
        System.out.println(HashGenerator.encodePassword("qwe"));
        System.out.println(HashGenerator.encodePassword("bingo"));
        System.out.println(HashGenerator.encodePassword("maminapodruga"));

    }
}
