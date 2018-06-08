package junit.org.rapidpm.fnproject.helloworld.services.login;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.rapidpm.fnproject.helloworld.api.login.Login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.rapidpm.fnproject.helloworld.api.login.Login.fromJson;


public class LoginServiceClientTest {

  @Test
  void test001() {

    final Login reference = new Login("login", "passwd");
    Login
        .toJson()
        .apply(reference)
        .ifPresentOrElse(
            json -> fromJson()
                .apply(json)
                .ifPresentOrElse(
                    login -> assertEquals(reference, login),
                    Assertions::fail
                ),
            Assertions::fail
        );

  }
}
