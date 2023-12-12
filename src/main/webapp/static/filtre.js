//script des radio bouttons de filtre des enchères

// Récupérer les éléments du DOM
const radioAchat = document.getElementById("Achat");
const radioVentes = document.getElementById("Ventes");

const enchereOuvertes = document.getElementById("EnchereOuvertes");
const enchereEnCours = document.getElementById("EnchereEnCours");
const enchereRemportes = document.getElementById("EnchereRemportes");

const venteEnCours = document.getElementById("VenteEnCours");
const venteNonDebutes = document.getElementById("VenteNonDebutes");
const ventesTermines = document.getElementById("VentesTermines");

// Fonction pour activer/désactiver les champs
function MiseAJourInputDesactives() {
	enchereOuvertes.disabled = radioVentes.checked;
	enchereEnCours.disabled = radioVentes.checked;
	enchereRemportes.disabled = radioVentes.checked;

	venteEnCours.disabled = radioAchat.checked;
	venteNonDebutes.disabled = radioAchat.checked;
	ventesTermines.disabled = radioAchat.checked;

	// Désactiver les cases à cocher si elles sont désactivées
	if (radioVentes.checked) {
		enchereOuvertes.checked = false;
		enchereEnCours.checked = false;
		enchereRemportes.checked = false;
	}

	if (radioAchat.checked) {
		venteEnCours.checked = false;
		venteNonDebutes.checked = false;
		ventesTermines.checked = false;
	}
}

// Ajouter des écouteurs d'événements aux boutons radio
// prend deux param : le type d'event à écouter ("change")
// ainsi que l'action a effectuer (ici notre function MiseAJourInputDesactives)
radioAchat.addEventListener("change", MiseAJourInputDesactives);
radioVentes.addEventListener("change", MiseAJourInputDesactives);

// Appeler la fonction au chargement de la page avoir l'état initial
MiseAJourInputDesactives();
