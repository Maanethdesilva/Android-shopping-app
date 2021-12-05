package com.example.javaproject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {
    @Mock
    MainActivity view;

    @Mock
    MyModel model;

    @Test
    public void testPresenterEmptyEmail(){
        when(view.getEmail()).thenReturn ("");
        when(view.getPassword()).thenReturn("");

        MyPresenter presenter = new MyPresenter(model, view);
        presenter.checkCredentials();

        verify(view).displayEmailMessage("Field is empty");
    }

    @Test
    public void testPresenterPasswordEmail(){
        when(view.getEmail()).thenReturn ("");
        when(view.getPassword()).thenReturn("");

        MyPresenter presenter = new MyPresenter(model, view);
        presenter.checkCredentials();

        verify(view).displayPasswordMessage("Field is empty");
    }

    @Test
    public void testPresenterShortPassword(){
        when(view.getEmail()).thenReturn ("person@gmail.com");
        when(view.getPassword()).thenReturn("123");

        MyPresenter presenter = new MyPresenter(model, view);
        presenter.checkCredentials();

        verify(view).displayPasswordMessage("Password is too short");
    }

    @Test
    public void testPresenterInvalidEmail(){
        MyPresenter presenter = new MyPresenter(model, view);
        presenter.unsuccessful();

        verify(view).displayEmailMessage("unsuccessful login");
    }

    @Test
    public void testPresenterCustomerLogin(){
        MyPresenter presenter = new MyPresenter(model, view);
        presenter.navigateCustomer();

        verify(view, times(1)).customerPage();
    }

    @Test
    public void testPresenterStoreOwnerLogin(){
        MyPresenter presenter = new MyPresenter(model, view);
        presenter.navigateStore();

        verify(view, times(1)).storeOwnerPage();
    }

//    @Test
//    public void testPresenterModelCalled(){
//        when(view.getEmail()).thenReturn ("timhortons@gmail.com");
//        when(view.getPassword()).thenReturn("password");
//        MyPresenter presenter = new MyPresenter(model, view);
//        presenter.checkCredentials();
//
//        verify(model).loginChecker("timhortons@gmail.com", "password");
//    }
}