package ru.home.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import ru.home.model.User;
import ru.home.respository.UserRepository;

@Route(value = "index")
@Slf4j
public class MainView extends VerticalLayout {

    private final UserRepository userRepository;
    private final Button addNewUserButton;
    private final TextField filter;

    private final UserEditor userEditor;

    final Grid<User> grid;

    public MainView(UserRepository repository, UserEditor userEditor) {

        this.userRepository = repository;
        this.grid = new Grid<>(User.class);
        this.filter = new TextField();
        this.userEditor = userEditor;

        this.addNewUserButton = new Button("New user", VaadinIcon.PLUS.create());

        HorizontalLayout actions = new HorizontalLayout(filter, addNewUserButton);
        add(actions, grid, userEditor);

        grid.setHeight("500px");
        grid.setColumns("id", "firstname", "lastname", "birthday", "login", "about", "address");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

        filter.setPlaceholder("Filter by first name");
        filter.setValueChangeMode(ValueChangeMode.EAGER);

        grid.asSingleSelect().addValueChangeListener(e -> {
            userEditor.editUser(e.getValue());
        });

        addNewUserButton.addClickListener(e -> userEditor.editUser(new User("", "", "", "", "", "", "")));

        userEditor.setChangeHandler(() -> {
            userEditor.setVisible(false);
            listUsers(filter.getValue());
        });

        listUsers(null);
    }

    void listUsers(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            grid.setItems(userRepository.findAll());
        } else {
            grid.setItems(userRepository.findByFirstnameStartsWithIgnoreCase(filterText));
        }
    }

}
