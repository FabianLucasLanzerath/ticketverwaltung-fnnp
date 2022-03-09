package de.bkbw.fnnp.controller;

import de.bkbw.fnnp.model.Session;
import de.bkbw.fnnp.model.User;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class SessionController {
  private static SessionController sessionController;
  private Session session;
  private Timer timer = new Timer();

  private SessionController() { }

  public static SessionController getInstance() {
      if (sessionController == null)
          sessionController = new SessionController();

      return sessionController;
  }

  public void setSession(Session session) {
      this.session = session;

      timer.scheduleAtFixedRate(new TimerTask(){
          @Override
          public void run(){
              if (!isSessionValid()) destroySession();
          }
      },0,5000);
  }

  public User getUserBySession() {
      return session.getUser();
  }

  public void destroySession() {
      this.session = null;
      this.timer.cancel();
  }

  private boolean isSessionValid() {
      return LocalDateTime.now().isBefore(session.getTtl());
  }
}