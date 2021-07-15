package vue.produit;

import java.util.ArrayList;

import model.entites.ProduitStat;
import myUX.MyTable;

@SuppressWarnings("serial")
public class ListProduit extends MyTable {

	private ArrayList<ProduitStat> produits;
	
	public ListProduit() {
		super();
		produits = new ArrayList<ProduitStat>();
		model.setColumnIdentifiers(new String[] {"NOM", "CATEGORIE", "QTE. STOCK"});
	}
	
	public ProduitStat getProduitAt(int index) {
		return produits.get(index);
	}

	public void setProduits(ArrayList<ProduitStat> produits) {
		model.setRowCount(0);
		this.produits = produits;
		for (ProduitStat p : this.produits)
			model.addRow(new Object[] {p.getNom(), p.getCategorie(), p.getQuantiteStock()});
	}
}
