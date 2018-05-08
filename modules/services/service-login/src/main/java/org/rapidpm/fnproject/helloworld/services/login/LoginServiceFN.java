package org.rapidpm.fnproject.helloworld.services.login;

import org.rapidpm.fnproject.helloworld.api.login.Login;
import org.rapidpm.fnproject.helloworld.api.login.LoginService;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.rapidpm.frp.matcher.Case.match;
import static org.rapidpm.frp.matcher.Case.matchCase;
import static org.rapidpm.frp.model.Result.failure;
import static org.rapidpm.frp.model.Result.success;

public class LoginServiceFN implements LoginService {

  @Override
  public boolean checkLogin(Login login) {
    return match(
        matchCase(() -> success(FALSE)),
        matchCase(() -> login == null,
                  () -> failure("login should not be null")
        ),
        matchCase(() -> login.getLogin() == null ||
                        login.getPassword() == null,
                  () -> failure("login or passwd is null")
        ),
        matchCase(() -> login.getLogin().isEmpty() ||
                        login.getPassword().isEmpty(),
                  () -> failure("login or passwd is empty")
        ),
        matchCase(() -> login.getLogin().equals("admin") &&
                        login.getPassword().equals("admin"),
                  () -> success(TRUE)
        )
    )
        .ifAbsent(System.out::println) //logger to use
        .getOrElse(() -> FALSE);
  }
}
