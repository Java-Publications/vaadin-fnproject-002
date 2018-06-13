package org.rapidpm.fnproject.helloworld.ui;

import com.vaadin.ui.*;
import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.fnproject.helloworld.api.login.Login;
import org.rapidpm.fnproject.helloworld.services.login.LoginServiceClient;

public class LoginComponent extends Composite implements HasLogger {

  private final TextField     loginField    = new TextField();
  private final PasswordField passwordField = new PasswordField();
  private final Button        okButton      = new Button();
  private final Button        cancelButton  = new Button();
  private final FormLayout    formLayout    = new FormLayout(loginField,
                                                             passwordField,
                                                             new HorizontalLayout(okButton, cancelButton)
  );

  public LoginComponent() {

    loginField.setCaption("Username");
    passwordField.setCaption("Password");

    okButton.setCaption("OK");
    cancelButton.setCaption("Cancel");

    setCompositionRoot(formLayout);
  }

  public Component postConstruct() {
    okButton.addClickListener((Button.ClickListener) clickEvent -> {
      LoginServiceClient client = new LoginServiceClient();
      Login              login  = new Login(loginField.getValue(), passwordField.getValue());
      if (client.checkLogin(login)) {
        LoginComponent.this.getUI().setContent(new DashboardComponent().postConstruct());
      } else {
        cancelButton.click();
      }
    });

    cancelButton.addClickListener((Button.ClickListener) clickEvent -> {
      loginField.setValue("");
      passwordField.setValue("");
    });

    return this;
  }
}
