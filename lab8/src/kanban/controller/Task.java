package kanban.controller;

import kanban.model.Priority;
import kanban.model.State;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable {
	private String title;
	private Priority priority;
	private LocalDate deadline;
	private String moreinfo;
	private String priorityShort;
	private State state;

	public Task(Priority level, LocalDate deadline, String title, String moreinfo, State state) {
		this.title = title;
		this.priority = level;
		this.deadline = deadline;
		this.moreinfo = moreinfo;
		this.state = state;

		//Setting priority indicator
		if (level == Priority.MEDIUM) { priorityShort = "[M]"; }
		else if (level == Priority.HIGH) { priorityShort = "[H]"; }
		else { priorityShort = "[L]"; }

		//Default values
		if (title == null) { this.title = "Default title"; }
		if (deadline == null) { this.deadline = LocalDate.EPOCH; }
		if (level == null) { this.priority = Priority.LOW; }
		if (moreinfo == null)  { this.moreinfo = ""; }
		if(state == null) { this.state = State.DONE; }
	}


	//Add task item
	public String toString(){
		return this.title + " " + this.priorityShort;
	}

	public String getMoreinfo(){
		return this.moreinfo;
	}

	public String getTitle() {
		return title;
	}
	public Priority getPriority(){
		return priority;
	}
	public LocalDate getDeadline(){
		return deadline;
	}
	public State getState() {
		return state;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public void setDeadline(LocalDate deadline) {
		this.deadline = deadline;
	}

	public void setMoreinfo(String moreinfo) {
		this.moreinfo = moreinfo;
	}

	public void setState(State state) {
		this.state = state;
	}

	//gets CSV output
	public String getCsv() {
	String data = title + ";" + priority + ";" + deadline + ";" + moreinfo + ";" + priorityShort + ";" + state + "\n";
	return data;
	}



}
