<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- ETAPE 5 : Génération de la View avec les données du Model --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des produits</title>
<link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>">
<link rel="stylesheet" href="<c:url value="/css/bootstrap-icons.css"/>">
</head>
<body>
	
	<div class="container">
		<div class="card mt-3">
			<div class="card-header bg-primary text-white display-6">Liste des produits</div>
			<div class="card-body">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Identifiant</th>
							<th>Libellé</th>
							<th>Prix d'achat</th>
							<th>Prix de vente</th>
							<th>Référence</th>
							<th>Modèle</th>
							<th>Stock</th>
							<th>Fournisseur</th>
							<th></th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${mesProduits}" var="prod">
							<c:url value="/produit/edit" var="editUrl">
								<c:param name="id" value="${prod.id}"/>
							</c:url>
							<c:url value="/produit/delete/${prod.id}" var="deleteUrl"/>
							<tr>
								<td>${prod.id}</td>
								<td>${prod.libelle}</td>
								<td>${prod.prixAchat}</td>
								<td>${prod.prixVente}</td>
								<td>${prod.reference}</td>
								<td>${prod.modele}</td>
								<td>${prod.stock}</td>
								<td>${prod.fournisseur.nom}</td>
								<td><div class="btn-group btn-group-sm">
								    <a href="${editUrl}" class="btn btn-primary"><i class="bi bi-pencil-square"></i></a>
								    <a href="${deleteUrl}" class="btn btn-danger"><i class="bi bi-trash"></i></a>
								 </div></td>
							</tr>
						</c:forEach>
					</tbody>

				</table>
			</div>
			<c:url value="/produit/add" var="addUrl"/>
			
			<div class="card-footer">
				<a href="${addUrl}" class="btn btn-success btn-lg"><i class="bi bi-plus-square"></i></a>
			</div>
		</div>

	</div>

	<script src="<c:url value="/js/bootstrap.bundle.js"/>"></script>
</body>
</html>