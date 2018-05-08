package org.rapidpm.fnproject.helloworld.api.login;

import java.util.Objects;

public class Login {

  private String login;
  private String password;

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Login)) return false;
    Login login1 = (Login) o;
    return Objects.equals(login, login1.login) &&
           Objects.equals(password, login1.password);
  }

  @Override
  public int hashCode() {

    return Objects.hash(login, password);
  }

  @Override
  public String toString() {
    return "Login{" +
           "login='" + login + '\'' +
           ", password='" + password + '\'' +
           '}';
  }
}
