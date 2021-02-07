package ru.home.view;

import com.vaadin.flow.router.Route;
import com.vaadin.ui.VerticalLayout;
import lombok.extern.slf4j.Slf4j;

@Route("adduser")
@Slf4j
public class CreateUserView extends VerticalLayout {

    public CreateUserView(){
        log.info("Add User");
    }
}
