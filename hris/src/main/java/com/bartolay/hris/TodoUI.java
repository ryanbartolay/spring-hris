package com.bartolay.hris;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI
@Theme(value="valo")
public class TodoUI extends UI {

	private Layout layout;
	
	@Autowired
	private TodoList todoList;
	
	private static final long serialVersionUID = 1L;
	
	public TodoUI(TodoList todoList) {
		super();
		this.todoList = todoList;
	}

	@Override
	protected void init(VaadinRequest request) {
		setupLayout();
		addHeader();
		addForm();
		addTodoList();
		addActionButton();
	}

	private void addActionButton() {
		Button deleteButton = new Button("delete completed", click -> {
			todoList.deleteCompleted();
		});
		layout.addComponent(deleteButton);
	}

	private void addTodoList() {
		todoList.setWidth("80%");
		layout.addComponent(todoList);
	}

	private void addForm() {
		HorizontalLayout formLayout = new HorizontalLayout();
		formLayout.setSpacing(true);
		formLayout.setWidth("80%");
		TextField textField = new TextField();
		textField.setWidth("100%");
		Button addButton = new Button("");
		addButton.setIcon(VaadinIcons.PLUS);
		addButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
		
		formLayout.addComponentsAndExpand(textField);
		formLayout.addComponents(addButton);
		
		addButton.addClickListener(click -> {
			todoList.add(new Todo(textField.getValue()));
			textField.clear();
			textField.focus();
		});
		
		textField.focus();
		addButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		layout.addComponent(formLayout);
	}

	private void addHeader() {
		Label label = new Label("TODO");
		label.setStyleName(ValoTheme.LABEL_H1);
		label.setSizeUndefined();
		layout.addComponent(label);
	}

	private void setupLayout() {
		layout = new VerticalLayout();
		setContent(layout);
	}

}
