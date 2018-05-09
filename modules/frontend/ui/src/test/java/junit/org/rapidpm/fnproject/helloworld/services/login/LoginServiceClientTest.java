package junit.org.rapidpm.fnproject.helloworld.services.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.rapidpm.fnproject.helloworld.services.login.LoginJson;
import org.rapidpm.fnproject.helloworld.services.login.LoginServiceClient;
import org.rapidpm.frp.functions.CheckedFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginServiceClientTest {

  @Test
  void test001() {

    final LoginJson reference = new LoginJson("login", "passwd");
    LoginServiceClient
        .toJson()
        .apply(reference)
        .ifPresentOrElse(
            json -> ((CheckedFunction<String, LoginJson>) s -> new ObjectMapper()
                .readerFor(LoginJson.class)
                .readValue(json))
                .apply(json)
                .ifPresentOrElse(
                    login -> assertEquals(reference, login),
                    Assertions::fail
                ),
            Assertions::fail
        );

  }
}
