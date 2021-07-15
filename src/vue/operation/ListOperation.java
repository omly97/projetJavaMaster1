package vue.operation;

import java.util.ArrayList;

import model.entites.Operation;
import myUX.MyTable;

@SuppressWarnings("serial")
public class ListOperation extends MyTable {

	ArrayList<Operation> operations;

	public ListOperation() {
		super();
		operations = new ArrayList<Operation>();
		model.setColumnIdentifiers(new String[] {"NATURE", "TYPE", "MONTANT", "ETAT"});
	}
	
	public Operation getOperationAt(int index) {
		return operations.get(index);
	}
	
	public void setOperations(ArrayList<Operation> operations) {
		model.setRowCount(0);
		this.operations = operations;
		for (Operation o : this.operations)
			model.addRow(new Object[] {o.getNature(), o.getType(), o.getMontant(), o.getEtat()});
	}
}
