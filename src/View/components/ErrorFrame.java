package View.components;

import javax.swing.*;
import java.awt.*;

public class ErrorFrame extends JDialog {

    public ErrorFrame(Frame owner, String errorMessage) {
        super(owner, "Error", true); // Modalidad: bloquea la ventana padre
        initialize(errorMessage);
    }

    private void initialize(String errorMessage) {
        // Configuración del diálogo
        setSize(400, 220);
        setLocationRelativeTo(getOwner()); // Se centra respecto al padre (o pantalla si owner es null)
        setAlwaysOnTop(true); // Se mantiene al frente
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        // Icono de error (usa el icono de OptionPane)
        Icon errorIcon = UIManager.getIcon("OptionPane.errorIcon");
        JLabel iconLabel = new JLabel(errorIcon);
        iconLabel.setPreferredSize(new Dimension(64, 64));

        // Mensaje con estilo HTML para mayor impacto
        JLabel messageLabel = new JLabel(
            "<html><div style='text-align:center; font-size:18px; font-weight:bold; color:#B22222;'>"
            + errorMessage + "</div></html>"
        );
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Botón de cerrar
        JButton closeButton = new JButton("Cerrar");
        closeButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        closeButton.setBackground(new Color(220, 20, 60));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(_ -> dispose());

        // Panel para el botón
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);

        // Panel principal
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.add(iconLabel, BorderLayout.WEST);
        contentPanel.add(messageLabel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(contentPanel);
    }
}
