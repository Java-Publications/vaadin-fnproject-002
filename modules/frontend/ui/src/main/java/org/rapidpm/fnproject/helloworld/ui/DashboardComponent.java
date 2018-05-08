package org.rapidpm.fnproject.helloworld.ui;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import org.rapidpm.dependencies.core.logger.HasLogger;

public class DashboardComponent extends Composite implements HasLogger {

  private final Label  message      = new Label();
  private final Button logoutButton = new Button();

  private Layout layout = new VerticalLayout(message, logoutButton);

  public DashboardComponent() {
    message.setValue("Login OK");
    logoutButton.setCaption("logout");

    setCompositionRoot(layout);
  }

  public Component postConstruct() {

    logoutButton.addClickListener(e -> {
      VaadinSession
          .getCurrent()
          .getUIs()
          .forEach(ui -> ui.access(() -> ui.getPage().setLocation("/")));
      getSession().close();
    });
    return this;
  }
}
