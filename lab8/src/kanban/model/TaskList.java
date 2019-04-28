package kanban.model;

import kanban.controller.Task;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TaskList<Task> extends ArrayList<kanban.controller.Task> implements Serializable {

	private kanban.controller.Task reconstructTask(String csv) {

		//parsing
		String[] data = csv.split(";");


		String title  = data[0];
		//priority;
		Priority priority;
		if(data[1].equals("LOW")) {
			priority = Priority.LOW;
		}
		else if(data[1].equals("MEDIUM")) {
			priority = Priority.MEDIUM;
		}
		else {
			priority = Priority.HIGH;
		}
		//deadline
		LocalDate deadline = LocalDate.parse(data[2]);
		//moreinfo
		String moreinfo = data[3];
		//priorityShort
		String priorityShort = data[4];
		//state
		State state;
		if(data[5].equals("TODO")) {
			state = State.TODO;
		}
		else if(data[5].equals("INPROGRESS")) {
			state = State.INPROGRESS;
		}
		else {
			state = State.DONE;
		}

		return new kanban.controller.Task(priority, deadline, title, moreinfo, state);
	}


	public void addReconstructedObjectToList(String in) {
		kanban.controller.Task task = reconstructTask(in);
		this.add(task);
	}


}
