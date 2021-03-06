package ru.unn.agile.IntersectionFinder.view;

import ru.unn.agile.IntersectionFinder.viewmodel.ViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class IntersectionFinder {
    private final ViewModel viewModel;
    private JPanel mainPanel;
    private JTextField pointLine;
    private JTextField vectorLine;
    private JTextField pointPlane;
    private JTextField normalPlane;
    private JTextField result;
    private JButton findIntersectionButton;
    private JLabel errorLabel;

    private IntersectionFinder(final ViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();
        findIntersectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                IntersectionFinder.this.viewModel.findIntersection();
                backBind();
            }
        });
        KeyAdapter keyListener = new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                bind();
                IntersectionFinder.this.viewModel.parseInput();
                backBind();
            }
        };
        pointLine.addKeyListener(keyListener);
        vectorLine.addKeyListener(keyListener);
        pointPlane.addKeyListener(keyListener);
        normalPlane.addKeyListener(keyListener);
    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("Intersection Finder");
        frame.setContentPane(new IntersectionFinder(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void bind() {
        viewModel.setPointLine(pointLine.getText());
        viewModel.setVectorLine(vectorLine.getText());
        viewModel.setPointPlane(pointPlane.getText());
        viewModel.setNormalPlane(normalPlane.getText());
    }

    private void backBind() {
        findIntersectionButton.setEnabled(viewModel.isFinderButtonEnabled());
        result.setText(viewModel.getResult());
        errorLabel.setText(viewModel.getError());
    }
}
