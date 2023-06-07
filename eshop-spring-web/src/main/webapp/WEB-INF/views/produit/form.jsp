<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- ETAPE 5 : Génération de la View avec les données du Model --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edition d'un produit</title>
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
<link rel="stylesheet" href="<c:url value="/css/bootstrap-icons.css"/>">
</head>
<body>


	<div class="container">
		<div class="card mt-3">
			<c:url value="/produit/save" var="saveUrl"/>
			<form action="${saveUrl}" method="post">
				<input type="hidden" name="id" value="${produit.id}" />
				<div class="card-header bg-primary text-white display-6">Edition d'un produit</div>
				<div class="card-body">
					<div class="form-group">
						<label for="libelle">Libellé:</label> <input type="text"
							class="form-control" id="libelle" name="libelle"
							value="${produit.libelle}" />
					</div>
					<div class="form-group">
						<label for="prixAchat">Prix d'achat:</label> <input type="number"
							class="form-control" id="prixAchat" name="prixAchat"
							value="${produit.prixAchat}" />
					</div>
					<div class="form-group">
						<label for="prixVente">Prix de vente:</label> <input type="number"
							class="form-control" id="prixVente" name="prixVente"
							value="${produit.prixVente}" />
					</div>
					<div class="form-group">
						<label for="reference">Référence:</label> <input type="text"
							class="form-control" id="reference" name="reference"
							value="${produit.reference}" />
					</div>
					<div class="form-group">
						<label for="modele">Modèle:</label> <input type="text"
							class="form-control" id="modele" name="modele"
							value="${produit.modele}" />
					</div>
					<div class="form-group">
						<label for="stock">Stock:</label> <input type="number"
							class="form-control" id="stock" name="stock"
							value="${produit.stock}" />
					</div>
					<div class="form-group">
						<label for="idFournisseur">Fournisseur:</label>
						<select class="form-control" id="idFournisseur" name="idFournisseur">
							<option value="">Sélectionner un Fournisseur</option>
							<c:forEach items="${fournisseurs}" var="fournisseur">
								<option value="${fournisseur.id}" ${produit.fournisseur.id == fournisseur.id ? 'selected' : ''}>${fournisseur.nom}</option>
							</c:forEach>
						</select> 
					</div>
				</div>
				<div class="card-footer">
					<c:url value="/produit/cancel" var="cancelUrl"/>
					<div class="btn-group btn-group-lg float-right">
						<button type="submit" class="btn btn-success"><i class="bi bi-check-square-fill"></i></button>
						<a href="${cancelUrl}" class="btn btn-warning"><i class="bi bi-backspace-fill"></i></a>
					</div>
				</div>
			</form>
		</div>
	</div>

	<script src="<c:url value="/js/bootstrap.bundle.js"/>"></script>
</body>
</html>