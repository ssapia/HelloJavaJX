package net.ssapia.address.controller;

/**
 * Created by salvador on 25/04/15.
 */

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import net.ssapia.address.MainApp;
import net.ssapia.address.model.Person;
import net.ssapia.address.util.DateUtil;

public class PersonOverviewController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * O construtor.
     * O construtor é chamado antes do método inicialize().
     */
    public PersonOverviewController() {
    }

    /**
     * Inicializa a classe controller. Este método é chamado automaticamente
     *  após o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {

        // Inicializa a tabela de pessoas com duas colunas.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        // Limpa os detalhes da pessoa.
        showPersonDetails(null);

        // Detecta mudanças de seleção e mostra os detalhes da pessoa quando houver mudança.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));

    }

    /**
     * É chamado pela aplicação principal para dar uma referência de volta a si mesmo.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Adiciona os dados da observable list na tabela
        personTable.setItems(mainApp.getPersonData());
    }

    /**
     * PReenche todos os campos de texto para mostrar detalhes sobre a pessoa.
     * Se a pessoa especificada for null, todos os campos de texto são limpos.
     *
     * @param person a pessoa ou null
     */
    private void showPersonDetails(Person person) {
        if (person != null) {
            // Preenche as labels com informações do objeto person.
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
        } else {
            // Person é null, remove todo o texto.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }

    /**
     * Chamado quando o usuário clica no botão delete.
     */
    @FXML
    public void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        personTable.getItems().remove(selectedIndex);
    }

    @FXML
    public void handleNewPerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        System.out.println("Add" + selectedIndex);
    }
}