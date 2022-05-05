package com.robb.banking.services;

import com.robb.banking.daos.UserDao;

public class UserServices {

    private UserDao userDao = new UserDao();

    public void readUsers(){
        System.out.println("Start reading Users in our file database.");
        User[] users;

        try {

            users = userDao.findAll();
            System.out.println("Here are all the users found: \n");
                for (int = 0; i < users.length; i++) {
                    User user = users[i];
                    if(user != null) {
                        System.out.println(User);
                    }
                }

            User user = new User();

            Object user1 = new User ("Marilyn", "Monroe", "mm@hollywood.com", "passwordmm", "6/1/1926");

            User test = new Trainer();
            System.out.println(test.getLast_name());

            for(Object t:users ){
                if(t != null) {
                    System.out.println(User) t);
                }
            }

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public boolean validateEmailNotUsed(String email){
        userDao.checkEmail(email);
        return false;
    }

    public boolean registerUser(User newUser){
        System.out.println("New user trying to register: " + newUser);
        if(!validateUserInput(newUser)){
            System.out.println("User was not validated");
            throw new RuntimeException();
        }

        validateEmailNotUsed(newUser.getEmail());

        if(persistedUser == null){
            throw new RuntimeException();
        }
        System.out.println("User has been persisted: " + newUser);
        return true;
    }

    private boolean validateUserInput(User newUSer) {
        System.out.println("Validating User: " + newUser);
        if(newUser == null) return false;
        if(newUser.getFirst_name() == null || newUser.getFirstName().trim().equals("")) return false;
        if(newUser.getLast_name() == null || newUser.getLastName().trim().equals("")) return false;
        if(newUser.getEmail_address() == null || newUser.getEmail_address().trim().equals("")) return false;
        if(newUser.getUserpassword() == null || newUser.getUserpassword().trim().equals("")) return false;
        if(newUser.getDate_of_birth() == null || newUser.getDate_of_birth().trim().equals("")) return false;
    }
}
