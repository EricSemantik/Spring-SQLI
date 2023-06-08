package spring.formation.model;

public class Views {
	public static class ViewBasic {}
	
	public static class ViewProduit extends ViewBasic {}
	
	public static class ViewProduitWithFournisseur extends ViewProduit {}
	
	public static class ViewProduitWithCommentaires extends ViewProduit {}
	
	public static class ViewPersonne extends ViewBasic {}
	
	public static class ViewFournisseur extends ViewPersonne {}
}
