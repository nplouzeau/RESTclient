package essaiClient;


import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: plouzeau
 * Date: 2013-02-13
 * Time: 21:23
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class Product {


	private String nom;
	private float prix;
	private String UID;


	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}


	public Product() {
	}

	public Product(String nom, float prix) {
		this.nom = nom;
		this.prix = prix;
	}

	public String getUID() {
		return UID;
	}

	public void setUID(String UID) {
		this.UID = UID;
	}
}
