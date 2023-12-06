package org.projetEncheres.javaee.bll;

import java.util.List;

import org.projetEncheres.javaee.bo.*;
import org.projetEncheres.javaee.dal.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilisateurManager {

	private UtilisateurDAO utilisateur;
	private static final String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$";
	
	public UtilisateurManager() {
		this.utilisateur = DAOFactory.getUtilisateurDAO();
	}

	public Utilisateur login(String pseudo, String motDePasse) throws DALException {
		return this.utilisateur.login(pseudo, motDePasse);
	}

	public Utilisateur loginEmail(String email, String motDePasse) throws DALException {
		return this.utilisateur.login(email, motDePasse);
	}

	public Utilisateur selectByPseudo(String pseudo) throws DALException {
		return this.utilisateur.selectByPseudo(pseudo);
	}

	public Utilisateur selectByID(int id) throws DALException {
		return this.utilisateur.selectByID(id);
	}

	public List<Utilisateur> selectAll() throws DALException {
		return this.utilisateur.selectAll();
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

}
