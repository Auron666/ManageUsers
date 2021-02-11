package ru.home.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;
import ru.home.model.User;
import ru.home.respository.UserRepository;



@Route(value = "index")
@Slf4j
public class MainView extends VerticalLayout {

    private final UserRepository userRepository;
    private final Grid<User> grid;
    private final TextField filterField = new TextField();

    private final FormAddView formAddView = new FormAddView(this);

    public MainView(UserRepository userRepository){
        this.userRepository = userRepository;
        this.grid = new Grid<>(User.class);

        HorizontalLayout mainContent = new HorizontalLayout(grid, formAddView);
        mainContent.setSizeFull();
        setSizeFull();

        add(grid, filterField, mainContent);

        createGrid();
        getAllList();
        filteredText();
    }

    void createGrid(){
        grid.setColumns("id", "firstname", "lastname", "birthday", "login", "about", "address");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);
    }

    void getAllList(){
        grid.setItems(userRepository.findAll());
    }

    void filteredText(){
        filterField.setPlaceholder("Filter by First Name");
        filterField.setValueChangeMode(ValueChangeMode.EAGER);
        filterField.addValueChangeListener(e -> getFilteredList());
    }

    void getFilteredList(){
        grid.setItems(userRepository.findByFirstnameStartsWithIgnoreCase(filterField.getValue()));
    }


}
