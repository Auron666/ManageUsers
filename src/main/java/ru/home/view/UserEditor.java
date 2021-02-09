package ru.home.view;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import org.springframework.beans.factory.annotation.Autowired;

import ru.home.model.User;
import ru.home.respository.UserRepository;

@SpringComponent
@UIScope
public class UserEditor extends VerticalLayout implements KeyNotifier {

    private final UserRepository repository;
    private User user;

    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");
    TextField birthday = new TextField("Birthday");
    TextField login = new TextField("Login");
    TextField password = new TextField("Password");
    TextField about = new TextField("About");
    TextField address = new TextField("Address");

    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    Binder<User> binder = new Binder<>(User.class);

    private ChangeHandler changeHandler;

    @Autowired
    public UserEditor(UserRepository repository){
        this.repository = repository;

        add(firstName, lastName, birthday, login, password, about, address);

        binder.bindInstanceFields(this);

        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        save.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editUser(user));
        setVisible(false);
    }

    void delete(){
        repository.delete(user);
        changeHandler.onChange();
    }

    void save(){
        repository.save(user);
        changeHandler.onChange();
    }

    public final void editUser(User user){

        if(user == null){
            setVisible(false);
            return;
        }

        final boolean persisted = user.getId() != null;

        if (persisted) {
            user = repository.findById(user.getId()).get();
        } else {
            user = user;
        }

        cancel.setVisible(persisted);
        binder.setBean(user);
        setVisible(true);
        firstName.focus();
    }

    public void setChangeHandler(ChangeHandler cHandler){
        changeHandler = cHandler;
    }

}
