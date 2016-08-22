package model;

import control.Controller;
import control.ControllerLobby;
import control.ControllerLogin;
import control.Controllers;

/**
 * Created by User on 18.08.2016.
 */
public class LinksControll {
    private static Controllers controllers;

    private static Controller controllerChess;

    private static ControllerLobby controllerLobby;

    private static ControllerLogin controllerLogin;

    public static Controllers getControllers() {
        return controllers;
    }

    public static void setControllers(Controllers controllers) {
        LinksControll.controllers = controllers;
    }

    public static Controller getControllerChess() {
        return controllerChess;
    }

    public static void setControllerChess(Controller controllerChess) {
        LinksControll.controllerChess = controllerChess;
    }

    public static ControllerLobby getControllerLobby() {
        return controllerLobby;
    }

    public static void setControllerLobby(ControllerLobby controllerLobby) {
        LinksControll.controllerLobby = controllerLobby;
    }

    public static ControllerLogin getControllerLogin() {
        return controllerLogin;
    }

    public static void setControllerLogin(ControllerLogin controllerLogin) {
        LinksControll.controllerLogin = controllerLogin;
    }
}
