package java_hw2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные (в произвольном порядке):");
        String input = scanner.nextLine();
        scanner.close();

        try {
            processData(input);
            System.out.println("Данные обработаны и записаны в файл.");
        } catch (InvalidDataFormatException | DataFormatException | FileWriteException e) {
            System.out.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void processData(String input) throws InvalidDataFormatException, DataFormatException, FileWriteException {
        String[] parts = input.split("\\s+");
        if (parts.length != 6) {
            throw new InvalidDataFormatException("Неверное количество данных.");
        }

        String lastName = null, firstName = null, middleName = null, dob = null, phoneNumber = null, gender = null;

        for (String part : parts) {
            if (part.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
                if (dob != null) {
                    throw new DataFormatException("Дата рождения указана несколько раз.");
                }
                dob = part;
            } else if (part.matches("\\d+")) {
                if (phoneNumber != null) {
                    throw new DataFormatException("Номер телефона указан несколько раз.");
                }
                phoneNumber = part;
            } else if (part.equals("f") || part.equals("m")) {
                if (gender != null) {
                    throw new DataFormatException("Пол указан несколько раз.");
                }
                gender = part;
            } else {
                if (lastName == null) {
                    lastName = part;
                } else if (firstName == null) {
                    firstName = part;
                } else if (middleName == null) {
                    middleName = part;
                } else {
                    throw new DataFormatException("Неверный формат данных.");
                }
            }
        }

        if (lastName == null || firstName == null || dob == null || phoneNumber == null || gender == null) {
            throw new DataFormatException("Не все данные указаны.");
        }

        String fileName = lastName + ".txt";
        String data = lastName + firstName + middleName + dob + " " + phoneNumber + gender;

        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(data + "\n");
        } catch (IOException e) {
            throw new FileWriteException("Ошибка при записи в файл.", e);
        }
    }
}
