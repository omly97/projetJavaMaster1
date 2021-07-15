package vue.user;

import java.util.ArrayList;

import model.entites.User;
import myUX.MyTable;

@SuppressWarnings("serial")
public class ListUser extends MyTable {

	private ArrayList<User> users;
	
	public ListUser() {
		super();
		users = new ArrayList<User>();
		model.setColumnIdentifiers(new String[] {"NOM", "ROLE", "login"});
	}
	
	public User getUserAt(int index) {
		return users.get(index);
	}
	
	public void setUsers(ArrayList<User> users) {
		model.setRowCount(0);
		this.users = users;
		for (User u : this.users)
			model.addRow(new Object[] {u.getNom(), u.getRole(), u.getLogin()});
	}
}
