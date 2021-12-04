package com.example.javaproject;

public class MyPresenter implements Contract.Presenter {
    private Contract.View view;
    private Contract.Model model;

    public MyPresenter(Contract.Model model, Contract.View view){
        this.model = model;
        this.view = view;
    }

    @Override
    public void checkCredentials() {
        String email = view.getEmail();
        String password = view.getPassword();
        if (email.equals("")){
            view.displayEmailMessage("Field is empty");
        }
        if (password.equals("")){
            view.displayPasswordMessage("Field is empty");
        }else if (password.length() < 6){
            view.displayPasswordMessage("Password is too short");
        }else{
            model = new MyModel(this);
            model.loginChecker(email, password);
        }
    }

    @Override
    public void unsuccessful(){
        view.displayEmailMessage("unsuccessful login");
    }

    @Override
    public void navigateStore() {
        view.storeOwnerPage();
    }

    @Override
    public void navigateCustomer(){
        view.customerPage();
    }

}