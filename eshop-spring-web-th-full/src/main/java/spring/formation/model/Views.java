package spring.formation.model;

public class Views {
	public static class ViewBasic {}
	
	public static class ViewAdresse extends ViewBasic {}
	
	public static class ViewCommande extends ViewBasic {}
	
	public static class ViewCommandeDetail extends ViewBasic {}
	
	public static class ViewProduit extends ViewBasic {}
	
	public static class ViewProduitWithFournisseur extends ViewProduit {}
	
	public static class ViewProduitWithCommentaires extends ViewProduit {}
	
	public static class ViewPersonne extends ViewBasic {}
	
	public static class ViewFournisseur extends ViewPersonne {}
	
	public static class ViewClient extends ViewPersonne {}
	
	public static class ViewCommentaire extends ViewBasic {}
	
	public static class ViewReparateur extends ViewBasic {}
}
