package com.example.gymfit;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class HelloController {
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido1;
    @FXML private TextField txtApellido2;
    @FXML private DatePicker dpFecha;
    @FXML private ToggleGroup tgSexo;
    @FXML private ComboBox<String> comboProvincia;
    @FXML private ToggleGroup tgCuota;
    @FXML private ComboBox<String> comboActividad;
    @FXML private TextField txtIban;
    @FXML private VBox lblAlerta;

    @FXML
    public void initialize() {

        lblAlerta.setVisible(false);
        lblAlerta.setManaged(false);
    }

    @FXML
    protected void onGuardarClick() {
        LocalDate fecha = dpFecha.getValue();
        String ibanLimpio = txtIban.getText().replace(" ", "").toUpperCase();


        boolean hayError = txtNombre.getText().isEmpty() ||
                !txtNombre.getText().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+") ||
                txtApellido1.getText().isEmpty() ||
                !txtApellido1.getText().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+") ||
                txtApellido2.getText().isEmpty() ||
                !txtApellido2.getText().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+") ||
                fecha == null || fecha.isAfter(LocalDate.now().minusYears(18)) ||
                tgSexo.getSelectedToggle() == null ||
                tgCuota.getSelectedToggle() == null ||
                comboProvincia.getValue() == null ||
                comboActividad.getValue() == null ||
                !ibanLimpio.matches("ES\\d{22}");

        if (hayError) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Formulario incompleto o incorrecto");
            alerta.setHeaderText("No se puede guardar el socio");
            alerta.setContentText("Por favor, revise que todos los campos estén rellenos y con el formato correcto.");

            DialogPane dialogPane = alerta.getDialogPane();
            dialogPane.setMinHeight(javafx.scene.layout.Region.USE_PREF_SIZE);
            dialogPane.setStyle(
                    "-fx-background-color: #FEE2E2; " +
                            "-fx-border-color: #DC2626; " +
                            "-fx-border-width: 2px;"
            );
            dialogPane.lookup(".header-panel").setStyle("-fx-background-color: #FECACA;");
            dialogPane.lookup(".header-panel .label").setStyle("-fx-text-fill: #991B1B; -fx-font-weight: bold;");
            dialogPane.lookup(".content.label").setStyle("-fx-text-fill: #7F1D1D;");

            alerta.showAndWait();
            return;
        }

        lblAlerta.setVisible(false);
        lblAlerta.setManaged(false);
    }

    @FXML
    protected void onLimpiarClick() {
        if (txtNombre != null) txtNombre.clear();
        if (txtApellido1 != null) txtApellido1.clear();
        if (txtApellido2 != null) txtApellido2.clear();
        if (dpFecha != null) dpFecha.setValue(null);
        if (tgSexo != null && tgSexo.getSelectedToggle() != null) {
            tgSexo.getSelectedToggle().setSelected(false);
        }
        if (comboProvincia != null) comboProvincia.getSelectionModel().clearSelection();
        if (tgCuota != null && tgCuota.getSelectedToggle() != null) {
            tgCuota.getSelectedToggle().setSelected(false);
        }
        if (comboActividad != null) comboActividad.getSelectionModel().clearSelection();
        if (txtIban != null) txtIban.clear();
    }
}
