package org.rapidpm.fnproject.helloworld.services.login;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.rapidpm.fnproject.helloworld.api.login.Login;

public class LoginJson extends Login {

  /**
   * <p>Constructor for Pair.</p>
   *
   * @param login    a T1 object.
   * @param password a T2 object.
   */
  @JsonCreator
  public LoginJson(
      @JsonProperty("login") String login,
      @JsonProperty("password") String password) {
    super(login, password);
  }
}
