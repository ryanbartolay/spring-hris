package com.bartolay.hris;

import com.vaadin.data.Binder;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class TodoLayout extends HorizontalLayout {

	private final TextField text;
	private final CheckBox done;
	private final Binder<Todo> binder;
	private static final long serialVersionUID = 1L;
	
	public TodoLayout(Todo todo, TodoRepository todoRepository) {
		setSpacing(true);
		setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		done = new CheckBox();
		text = new TextField();
		text.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
		
		done.addValueChangeListener(changed -> {
			todo.setDone(done.getValue());
			todoRepository.save(todo);
		});
		
		addComponents(done, text);
		
		binder = new Binder<>(Todo.class);
		binder.bindInstanceFields(this);
		binder.setBean(todo);
		
		
	}
}
