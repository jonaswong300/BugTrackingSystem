package com.company;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Scanner;

class UserAccessDatabase {

    private HashMap<String, String> userAccountMap = new HashMap<>();

    public UserAccessDatabase(){
        generateUserAccount();

        viewUserAccountMap();
        readUserAccountFromFile();
    }

    public String generatePassword(){

        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < 8; i++){
            int randomIndex = random.nextInt(chars.length());
            builder.append(chars.charAt(randomIndex));
        }

        return builder.toString();
    }

    public void generateUserAccount(){
        String tempPassword ="", tempUserName = "";


        try{
            FileWriter fw = new FileWriter("userAccount.txt", true);

            for(int i = 1; i <= 50; i++){
                tempPassword = generatePassword();
                tempUserName = "user" + i;
                fw.write(tempUserName + "\t" + tempPassword + "\n");
                userAccountMap.put(tempUserName, tempPassword);
            }

            fw.close();

        }catch (IOException e){
            System.err.println(e);
            e.printStackTrace();
        }

    }

    public void viewUserAccountMap(){
        System.out.println("\nReading from hashmap");
        for(String s : userAccountMap.keySet()){
            System.out.println(s + "\t" + userAccountMap.get(s));
        }
    }

    public void readUserAccountFromFile(){
        try{
            System.out.println("\nReading from file userAccount.txt");
            FileReader fw = new FileReader("userAccount.txt");

            Scanner input = new Scanner(fw);

            while(input.hasNextLine()){
                System.out.println(input.nextLine());
            }

            fw.close();

        }catch (IOException e){
            System.err.println(e);
            e.printStackTrace();
        }
    }

}
