package com.bartolay.hris;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.vaadin.ui.VerticalLayout;

@Component
public class TodoList extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	private TodoRepository todoRepository;

	public TodoList(TodoRepository todoRepository) {
		super();
		this.todoRepository = todoRepository;
	}
	
	@PostConstruct
	void init() {
		setSpacing(true);
		update();	
	}

	private void update() {
		setTodos(todoRepository.findAll());
	}

	private void setTodos(Iterable<Todo> findAll) {
		removeAllComponents();
		System.err.println("xxxxxx");
		System.err.println(findAll);
		System.out.println(findAll);
		findAll.forEach(todo -> addComponent(new TodoLayout(todo)));
	}

	public void add(Todo todo) {
		todoRepository.save(todo);
		update();
	}
	
}
