package bd.edu.seu.vaadindemo.ui;

import bd.edu.seu.vaadindemo.model.Student;
import bd.edu.seu.vaadindemo.service.StudentService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToLongConverter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Route(value = "student")
@Theme(value = Lumo.class, variant = Lumo.DARK)
public class StudentView extends AppLayout {
    @Autowired
    private StudentService studentService;
    private Binder<Student> studentBinder;
    private Grid<Student> grid;

    public StudentView(){
        studentBinder = new Binder<>();
        grid = new Grid<>();
        FormLayout form = new FormLayout();
        TextField idField = new TextField("ID Field", "Your Id/initials");
        TextField nameField = new TextField("NameField", "Your name");
        DatePicker datePicker = new DatePicker("BirthDate");
        Button submitButton = new Button("Submit");


        studentBinder.forField(idField)
                .asRequired()
                .withValidator(id -> id.length() == 3, "Id must be of length 3")
                .withConverter(new StringToLongConverter("id must't start with 0"))
                .bind(Student::getId, Student::setId);


        studentBinder.forField(nameField)
                .asRequired()
                .withValidator(str -> str.length() >= 3, "name must be at least 3 letters long")
                .bind(Student::getName, Student::setName);


        studentBinder.forField(datePicker)
                .asRequired()
                .bind(Student::getDob, Student::setDob);


        grid.addColumn(Student::getId)
                .setHeader("ID");

        grid.addColumn(Student::getName)
                .setHeader("Name");

        grid.addColumn(Student::getDob)
                .setHeader("Birthdate");


        submitButton.addClickListener(event -> {
            Student student = new Student();
            try{
                studentBinder.writeBean(student);
                Notification.show(student.toString());
                Student savedStudent = studentService.save(student);
                grid.setItems(studentService.findAll());
                Notification.show("saved " + savedStudent.getName());
            } catch (ValidationException e) {
                Notification.show(e.getMessage());
            } catch (Exception e){
                Notification.show(e.getMessage());
            }
        });


        grid.addItemClickListener(event -> {
            studentBinder.readBean(event.getItem());
        });


        Button logoutButton = new Button("Logout");
        logoutButton.addClickListener(event -> getUI().ifPresent(ui -> ui.navigate("login")));
        form.add(idField, nameField, datePicker, submitButton);

        Tab tab1 = new Tab("Tab one");
        Div page1 = new Div();
        page1.setVisible(false);
        page1.add(grid);

        Tab tab2 = new Tab("Tab two");
        Div page2 = new Div(form);
        page2.setVisible(false);

        Map<Tab, Component> tabsToPages = new HashMap<>();
        tabsToPages.put(tab1, page1);
        tabsToPages.put(tab2, page2);
        Tabs tabs = new Tabs(tab1, tab2);
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        Div pages = new Div(page1,  page2);
//        Set<Component> pagesShown = Stream.of(page1)
//                .collect(Collectors.toSet());

        tabs.addSelectedChangeListener(event -> {
//            pagesShown.forEach(page -> page.setVisible(false));
//            pagesShown.clear();
//            Component selectedPage = tabsToPages.get(tabs.getSelectedTab());
//            selectedPage.setVisible(true);
//            if (selectedPage.equals(page2)) {
//                page2.update();
//            }
            pages.setVisible(false);
            tabsToPages.get(tabs.getSelectedTab()).setVisible(true);
            setContent(tabsToPages.get(tabs.getSelectedTab()));
//            pagesShown.add(selectedPage);
        });

        addToNavbar(new DrawerToggle());
        addToDrawer(tabs, pages, logoutButton);
        setContent(tabsToPages.get(tabs.getSelectedTab()));
        tabsToPages.get(tabs.getSelectedTab()).setVisible(true);
        tabs.setFlexGrowForEnclosedTabs(1);

    }
}
