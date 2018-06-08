package fn.org.rapidpm.fnproject.helloworld.services.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fnproject.fn.testing.FnTestingRule;
import org.junit.Rule;
import org.junit.Test;
import org.rapidpm.fnproject.helloworld.api.login.Login;
import org.rapidpm.fnproject.helloworld.services.login.LoginServiceFN;
import org.rapidpm.frp.functions.CheckedFunction;
import org.rapidpm.frp.model.Triple;

import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;
import static org.rapidpm.frp.model.Triple.next;

public class LoginServiceFNTest {


  @Rule //junit4 !!
  public final FnTestingRule testing = FnTestingRule.createDefault();

  public static CheckedFunction<Login, String> toJson() {
    return (login) -> {
      final ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.writerFor(Login.class).writeValueAsString(login);
    };
  }

  private Consumer<Triple<String, String, String>> test() {
    return (triple) -> toJson()
        .apply(new Login(triple.getT1(), triple.getT2()))
        .ifAbsent(() -> {
          throw new RuntimeException("JSON Encoding failed for " + triple.getT1() + " - " + triple.getT2());
        })
        .ifPresent(success -> {
                     testing.givenEvent().withBody(success).enqueue();
                     testing.thenRun(LoginServiceFN.class, "checkLogin");
                     assertEquals(triple.getT3(), testing.getOnlyResult().getBodyAsString());
                   }
        );
  }

  @Test //junit4 !!
  public void checkLogin000() {
    test().accept(next("admin", "admin", "true"));
  }

  @Test //junit4 !!
  public void checkLogin001() {
    test().accept(next("admin", null, "false"));
  }

  @Test //junit4 !!
  public void checkLogin002() {
    test().accept(next("admin", "asas", "false"));
  }
}