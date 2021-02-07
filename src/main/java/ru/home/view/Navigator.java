package ru.home.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinRequest;

public class Navigator extends UI {

    Navigator navigator;
    protected static final String MAINVIEW = "main";

    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("Navigation Example");


    }
}
