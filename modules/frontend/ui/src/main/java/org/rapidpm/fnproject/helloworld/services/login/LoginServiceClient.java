package org.rapidpm.fnproject.helloworld.services.login;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.rapidpm.fnproject.helloworld.api.login.Login;
import org.rapidpm.fnproject.helloworld.api.login.LoginService;
import org.rapidpm.frp.functions.CheckedBiFunction;
import org.rapidpm.frp.model.Result;

import static org.rapidpm.fnproject.helloworld.api.login.Login.toJson;


public class LoginServiceClient implements LoginService {

  public static final MediaType JSON
      = MediaType.parse("application/json; charset=utf-8");

  public static final String URL = "http://127.0.0.1:8080/r/vaadin-fnproject-002/service-login";

  //performs best if client will be shared
  private static final OkHttpClient client = new OkHttpClient();

  public CheckedBiFunction<String, String, String> request() {
    return (url, json) -> {
      final Request request = new Request.Builder()
          .url(url)
          .post(RequestBody.create(JSON, json))
          .build();
      return client
          .newCall(request)
          .execute()
          .body()
          .string();
    };
  }



  @Override
  public boolean checkLogin(Login login) {
    if (login == null) return false;
    return toJson()
        .apply(new Login(login.getLogin(), login.getPassword() ))
        .flatMap(loginAsJson ->
                     request()
                         .apply(URL, loginAsJson)
                         .or(() -> Result.success("false"))
        )
        .map(Boolean::parseBoolean)
        .getOrElse(() -> Boolean.FALSE);

  }
}
