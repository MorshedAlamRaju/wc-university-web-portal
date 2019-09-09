package bd.edu.seu.vaadindemo.ui;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;


@Route(value = "login")
public class MainView extends Dialog {
    public MainView(){

        TextField textField = new TextField("Username", "Your username/studentId");
        PasswordField passwordField = new PasswordField("Password", "Password");
        Button loginButton = new Button("Login");
        loginButton.addClickShortcut(Key.ENTER);

        loginButton.addClickListener( event -> {
            getUI().ifPresent(ui -> ui.navigate("student"));
        });


        VerticalLayout verticalLayout = new VerticalLayout(textField, passwordField, loginButton);
        add(verticalLayout);
        setWidth("300px");
        setHeight("400px");
        open();
        setCloseOnOutsideClick(false);
    }
}
