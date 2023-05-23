/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.todolistapp;

/**
 *
 * @author ammar
 */import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.EmptyBorder;
import java.awt.Color;



public class ToDoListApp extends JFrame {
    private DefaultListModel<TodoItem> listModel;
    private JList<TodoItem> todoList;
    private JTextField textField;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton completeButton;

    private TodoList todoListObj;

    public ToDoListApp() {
        setTitle("Todo List Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(255, 0, 0));

        listModel = new DefaultListModel<>();
        todoList = new JList<>(listModel);
        todoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(todoList);
        add(scrollPane, BorderLayout.CENTER);

        textField = new JTextField();
        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        completeButton = new JButton("Priortize");

        Font buttonFont = new Font("Montserrat", Font.BOLD, 14);
        addButton.setFont(buttonFont);
        editButton.setFont(buttonFont);
        deleteButton.setFont(buttonFont);
        deleteButton.setForeground(Color.RED);
        completeButton.setFont(buttonFont);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(textField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);
        inputPanel.setBackground(new Color(221,255,187));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(completeButton);
        buttonPanel.setBackground(new Color(221,255,187));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTodoItem();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editTodoItem();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTodoItem();
            }
        });

        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markTodoItemAsCompleted();
            }
        });

        todoList.addListSelectionListener(e -> {
            boolean itemSelected = !todoList.isSelectionEmpty();
            editButton.setEnabled(itemSelected);
            deleteButton.setEnabled(itemSelected);
            completeButton.setEnabled(itemSelected);
        });

        todoListObj = new TodoList();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addTodoItem() {
        String description = textField.getText().trim();
        if (!description.isEmpty()) {
            TodoItem item = new TodoItem(description);
            todoListObj.addItem(item);
            listModel.addElement(item);
            textField.setText("");
        }
    }

    private void editTodoItem() {
        int selectedIndex = todoList.getSelectedIndex();
        if (selectedIndex >= 0) {
            TodoItem selectedItem = todoListObj.getItems().get(selectedIndex);
            String currentDescription = selectedItem.getDescription();
            String newDescription = JOptionPane.showInputDialog(
                    TodoListApp.this,
                    "Enter a new description:",
                    "Edit Item",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    currentDescription
            ).toString();

            if (newDescription != null && !newDescription.isEmpty()) {
                selectedItem.setDescription(newDescription);
                listModel.setElementAt(selectedItem, selectedIndex);
            }
        }
    }

    private void deleteTodoItem() {
        int selectedIndex = todoList.getSelectedIndex();
        if (selectedIndex >= 0) {
            todoListObj.removeItem(selectedIndex);
            listModel.removeElementAt(selectedIndex);
        }
    }

    private void markTodoItemAsCompleted() {
        int selectedIndex = todoList.getSelectedIndex();
        if (selectedIndex >= 0) {
            TodoItem selectedItem = todoListObj.getItems().get(selectedIndex);
            selectedItem.markAsCompleted();
            listModel.setElementAt(selectedItem, selectedIndex);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ToDoListApp app = new ToDoListApp();
                app.setSize(600, 400); // Set the initial size to a landscape dimension (width: 600, height: 400)
            }
        });
    }
}