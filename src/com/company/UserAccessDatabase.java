package com.company;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Scanner;

class UserAccessDatabase {

    private HashMap<String, String> userAccountMap = new HashMap<>();
    private HashMap<String, String> userRoleMap = new HashMap<>();

    public UserAccessDatabase(){
        readUserAccountFromFile();
        //viewUserAccountMap();
    }

    private String generatePassword(){

        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < 8; i++){
            int randomIndex = random.nextInt(chars.length());
            builder.append(chars.charAt(randomIndex));
        }

        return builder.toString();
    }

    private void generateUserAccount(){
        String tempPassword, tempUserName, role;

        String [] roleArray = new String [] {"Developer", "Reporter", "Reviewer", "Triager"};

        try{
            FileWriter fw = new FileWriter("userAccount.txt");

            for(int i = 1; i <= 50; i++){
                tempPassword = generatePassword();
                tempUserName = "user" + i;
                role = roleArray[(int)(Math.random() * 4)];
                fw.write(tempUserName + "\t" + tempPassword + "\t" + role + "\n");
                userAccountMap.put(tempUserName, tempPassword);
                userRoleMap.put(tempUserName, role);
            }

            fw.close();

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private void viewUserAccountMap(){
        System.out.println("\nDisplay user account");
        System.out.println("---------------------------------------------");
        for(String s : userAccountMap.keySet()){
            System.out.println(s + "\t" + userAccountMap.get(s));
        }

        System.out.println("\nDisplay user roles");
        System.out.println("---------------------------------------------");
        for(String s : userRoleMap.keySet()){
            System.out.println(s + "\t" + userRoleMap.get(s));
        }
    }

    private void readUserAccountFromFile(){
        String [] temp;
        try{
            System.out.println("\nStoring user accounts from file userAccount.txt");
            FileReader fw = new FileReader("userAccount.txt");

            Scanner input = new Scanner(fw);

            while(input.hasNextLine()){
                temp = input.nextLine().split("\t");
                userAccountMap.put(temp[0], temp[1]);
                userRoleMap.put(temp[0], temp[2]);
            }

            fw.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public HashMap<String, String> getUserAccountMap(){
        return userAccountMap;
    }

    public HashMap<String, String> getUserRoleMap(){
        return userRoleMap;
    }

}
