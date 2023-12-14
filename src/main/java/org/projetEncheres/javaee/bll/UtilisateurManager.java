package org.projetEncheres.javaee.bll;

import java.util.List;

import org.projetEncheres.javaee.bo.*;
import org.projetEncheres.javaee.dal.*;

public class UtilisateurManager {

	private UtilisateurDAO utilisateur;

	public UtilisateurManager() {
		this.utilisateur = DAOFactory.getUtilisateurDAO();
	}

	public Utilisateur login(String pseudo, String motDePasse) throws DALException, BLLException {
		try {
		return this.utilisateur.login(pseudo, motDePasse);
		} catch (DALException d){
			throw new BLLException ("Erreur dans le login by pseudo");
		}
	}

	public Utilisateur loginEmail(String email, String motDePasse) throws DALException, BLLException {
		try {
		return this.utilisateur.loginEmail(email, motDePasse);
		} catch (DALException d){
			throw new BLLException ("Erreur dans le login by email");
		}
	}

	public Utilisateur selectByPseudo(String pseudo) throws DALException, BLLException {
		try {
		return this.utilisateur.selectByPseudo(pseudo);
		} catch (DALException d){
			throw new BLLException ("Erreur dans la sélection par pseudo");
		}
	}

	public Utilisateur selectByID(int id) throws DALException,BLLException {
		try {
		return this.utilisateur.selectByID(id);
		} catch (DALException d){
			throw new BLLException ("Erreur dans la sélection par no_utilisateur");
		}
	}

	public List<Utilisateur> selectAll() throws DALException, BLLException {
		try {
		return this.utilisateur.selectAll();
		} catch (DALException d){
			throw new BLLException ("Erreur dans la sélection des utilisateurs");
		}
	}
	
	public void deleteUtilisateur(int id) throws DALException, BLLException {
		try {
			this.utilisateur.delete(id);
		} catch (DALException d){
			throw new BLLException ("Erreur dans la suppression de l'utilisateur");
		}
	}
	
	public void ajouterUtilisateur(Utilisateur u) throws DALException, BLLException{
		try {
			if (verificationEmail(u.getEmail()) && verificationPseudo(u.getPseudo()) && verificationPassword(u.getMotDePasse()) ) {
				this.utilisateur.insert(u);
			} else {
				throw new BLLException ("Le format de vos informations n'est pas adapté");
			}
		} catch (DALException d){
			throw new BLLException ("Erreur dans l'insertion de l'utilisateur");
		}
	}
	
	public void updateUtilisateur(Utilisateur u) throws DALException, BLLException {
		try {
			if (verificationEmail(u.getEmail()) && verificationPassword(u.getMotDePasse()) && verificationPseudo(u.getPseudo())) {
				this.utilisateur.update(u);
			} else {
				throw new BLLException ("Le format de vos informations n'est pas adapté");
			}
		} catch (DALException d){
			throw new BLLException ("Erreur dans la mise à jour de l'utilisateur");
		}
	}
	
	public Utilisateur selectByEmail(String email) throws DALException, BLLException {
		
		try {
			verificationEmail(email);
			return this.utilisateur.selectByEmail(email);
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("La sélection par email de l'utilisateur a échouée");
		}
		
	}

	public boolean verificationEmail(String email) {
		if (email.contains("@")) {
			return true;
		}

		return false;
	}
	
	

	public boolean verificationPassword(String motDePasse) {
		// Vérifier la longueur minimale
		boolean verif = false;
		if (motDePasse.length() < 8) {
			return false;
		}

		boolean hasDigit = false;
		boolean hasLowerCase = false;
		boolean hasUpperCase = false;

		// Parcourir chaque caractère du mot de passe
		for (char c : motDePasse.toCharArray()) {
			// Vérifier la présence d'au moins un chiffre
			if (Character.isDigit(c)) {
				hasDigit = true;
			}
			// Vérifier la présence d'au moins une lettre minuscule
			else if (Character.isLowerCase(c)) {
				hasLowerCase = true;
			}
			// Vérifier la présence d'au moins une lettre majuscule
			else if (Character.isUpperCase(c)) {
				hasUpperCase = true;

			}

		}
		if (hasDigit && hasLowerCase && hasUpperCase) {
			verif = true;
		}
		return verif;
	}
	
	public boolean verificationPseudo(String pseudo) {
		String pattern = "^[A-Za-z0-9@]+$";
		if (pseudo.matches(pattern)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void ajouterCredits (Utilisateur u, int credits) throws DALException {
		
		this.utilisateur.ajouterCredits(u, credits);
	}
		
	
	
}
