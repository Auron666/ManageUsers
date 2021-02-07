package ru.home.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Route(value = "main")
@PageTitle("Main")
public class MainView extends VerticalLayout {

    public MainView() {
        groupButton();
    }

    void groupButton(){
        Button createUserButton = new Button("Добавить пользователя", event -> UI.getCurrent().navigate("adduser"));

//        createUserButton.addClickListener(e -> {
//            log.info("Add User...");
//            UI.getCurrent().navigate("adduser");
//        });

        Button listUsersButton = new Button("Список пользователей");

        add(createUserButton, listUsersButton);
    }

}

