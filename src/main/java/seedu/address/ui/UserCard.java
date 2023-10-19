package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

public class UserCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    public final Person user;

    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane freeTimes;
    @FXML
    private FlowPane tags;

    public UserCard(Person user) {
        super(FXML);
        this.user = user;
        name.setText(user.getName().fullName);
        phone.setText(user.getPhone().value);
        address.setText(user.getAddress().value);
        email.setText(user.getEmail().value);
        user.getFreeTimes().stream()
                .sorted(Comparator.comparing(freeTime -> freeTime.freeTimeString))
                .forEach(freeTime -> freeTimes.getChildren().add(new Label(freeTime.freeTimeString)));
        user.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

}
