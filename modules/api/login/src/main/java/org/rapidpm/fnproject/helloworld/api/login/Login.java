package org.rapidpm.fnproject.helloworld.api.login;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.rapidpm.frp.functions.CheckedFunction;
import org.rapidpm.frp.model.serial.Pair;

public class Login extends Pair<String, String> {

  /**
   * <p>Constructor for Pair.</p>
   *
   * @param login    a T1 object.
   * @param password a T2 object.
   */
  @JsonCreator
  public Login(
      @JsonProperty("login") String login,
      @JsonProperty("password") String password) {
    super(login, password);
  }

  public static CheckedFunction<Login, String> toJson() {
    return (login) -> {
      final ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.writerFor(Login.class).writeValueAsString(login);
    };
  }

  public static CheckedFunction<String, Login> fromJson() {
    return (json) -> new ObjectMapper()
        .readerFor(Login.class)
        .readValue(json);
  }

  public String getLogin() {
    return getT1();
  }

  public String getPassword() {
    return getT2();
  }

}
