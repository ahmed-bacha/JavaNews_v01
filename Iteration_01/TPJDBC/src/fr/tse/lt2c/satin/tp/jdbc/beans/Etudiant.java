package fr.tse.lt2c.satin.tp.jdbc.beans;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author Julien
 * 
 */
public class Etudiant {

	// Pour génération aléatoire
	private static long ONE_YEAR_AS_MILLISECONDS = 365L * 24L * 60L * 60L
			* 1000L;
	private final static String[] prenoms = { "Robert", "Gudule", "Marie-Alix",
			"Godefroy", "Michel", "Alderick", "Hedastine", "Foucault",
			"Leopoldine", "Victoire", "Gaspard" };
	private final static String[] noms = { "Martin", "Bernard", "Dubois",
			"Thomas", "Robert", "Richard", "Petit", "Durand", "Leroy",
			"Moreau", "Simon", "Laurent", "Lefebvre" };
	private final static String[] filieres = { "FI", "FA" };

	// Attributs
	private String nom;
	private String prenom;
	private Date naissance;
	private String filiere;
	private int annee;

	public Etudiant(String nom, String prenom, Date naissance, String filiere,
			int annee) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.naissance = naissance;
		this.filiere = filiere;
		this.annee = annee;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public Date getNaissance() {
		return naissance;
	}

	public String getFiliere() {
		return filiere;
	}

	public int getAnnee() {
		return annee;
	}

	@Override
	public String toString() {
		return "Etudiant [nom=" + nom + ", prenom=" + prenom + ", naissance="
				+ naissance + ", filiere=" + filiere + ", annee=" + annee + "]";
	}

	public static Set<Etudiant> etudiantsAleatoire(int combien) {
		Set<Etudiant> resultat = new HashSet<>(combien);
		for (int i = 0; i < combien; i++) {
			resultat.add(Etudiant.etudiantAleatoire());
		}
		return resultat;
	}

	private static Etudiant etudiantAleatoire() {
		return new Etudiant(noms[((int) (Math.random() * noms.length))],
				prenoms[((int) (Math.random() * prenoms.length))],
				new Date(System.currentTimeMillis()
						- (20 * ONE_YEAR_AS_MILLISECONDS + (long) (Math
								.random() * 10 * ONE_YEAR_AS_MILLISECONDS))),
				filieres[((int) (Math.random() * filieres.length))],
				(int) (Math.random() * 3)+1);
	}

}
