package junit.org.rapidpm.fnproject.helloworld.services.login;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.rapidpm.fnproject.helloworld.api.login.Login;
import org.rapidpm.fnproject.helloworld.services.login.LoginServiceFN;

class LoginServiceFNTest {

  @Test
  void checkLogin000() {
    final Login login = new Login();
    login.setLogin("admin");
    login.setPassword("admin");
    boolean checkLogin = new LoginServiceFN().checkLogin(login);
    Assertions.assertTrue(checkLogin);
  }

  @Test
  void checkLogin001() {
    final Login login = new Login();
    login.setLogin("admin");
    login.setPassword("");
    boolean checkLogin = new LoginServiceFN().checkLogin(login);
    Assertions.assertFalse(checkLogin);
  }

  @Test
  void checkLogin002() {
    final Login login = new Login();
    login.setLogin("admin");
    login.setPassword(null);
    boolean checkLogin = new LoginServiceFN().checkLogin(login);
    Assertions.assertFalse(checkLogin);
  }
}