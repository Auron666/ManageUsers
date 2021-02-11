package ru.home.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import ru.home.model.User;
import ru.home.respository.UserRepository;

public class FormAddView extends FormLayout {

    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private DatePicker birthDate = new DatePicker("Birthdate");
    private TextField login = new TextField("Login");
    private TextField password = new TextField("Password");
    private TextField about = new TextField("About");
    private TextField address = new TextField("Address");

    private Button save;
    private Button delete;

    private Binder<User> binder = new Binder<>(User.class);

    private UserRepository repository;

    private MainView mainView;

    public FormAddView(MainView mainView){

        this.mainView = mainView;

        this.save = new Button("Save", VaadinIcon.PLUS.create());
        this.delete = new Button("Delete", VaadinIcon.TRASH.create());

        HorizontalLayout buttons = new HorizontalLayout(save, delete);

        add(firstName, lastName, birthDate, login, password, about, address, buttons);

        binder.bindInstanceFields(this);

        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
    }

    public void setUser(User user) {
        binder.setBean(user);

        if (user == null) {
            setVisible(false);
        } else {
            setVisible(true);
            firstName.focus();
        }
    }

    private void save() {
        User user = binder.getBean();
        repository.save(user);
        mainView.getAllList();
        setUser(null);
    }

    private void delete() {
        User customer = binder.getBean();
        repository.delete(customer);
        mainView.getAllList();
        setUser(null);
    }

}
