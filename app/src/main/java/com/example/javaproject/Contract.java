package com.example.javaproject;

public interface Contract {

    interface Model{
        void loginChecker(String email, String password);
    }

    interface View{
        String getEmail();
        String getPassword();
        void displayEmailMessage(String message);
        void displayPasswordMessage(String message);
        void storeOwnerPage();
        void customerPage();
    }

    interface Presenter{
            void checkCredentials();
            void unsuccessful();
            void navigateStore();
            void navigateCustomer();
    }
}
