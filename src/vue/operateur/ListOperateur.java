package vue.operateur;

import java.util.ArrayList;

import model.entites.Operateur;
import myUX.MyTable;

@SuppressWarnings("serial")
public class ListOperateur extends MyTable {

	ArrayList<Operateur> operateurs;

	public ListOperateur() {
		super();
		operateurs = new ArrayList<Operateur>();
		model.setColumnIdentifiers(new String[] {"NOM", "ADRESSE", "TELEPHONE", "TYPE"});
	}
	
	public Operateur getOperateurAt(int index) {
		return operateurs.get(index);
	}
	
	public void setOperateurs(ArrayList<Operateur> operateurs) {
		model.setRowCount(0);
		this.operateurs = operateurs;
		for (Operateur o : this.operateurs)
			model.addRow(new Object[] {o.getNom(), o.getAdresse(), o.getTelephone(), o.getType()});
	}
}
