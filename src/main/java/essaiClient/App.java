package essaiClient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

/**
 * User: plouzeau
 * Date: 2013-03
 * Time: 09:17
 */

/**
 * @author Noël Plouzeau
 *         A simple test class for the Product manager mini RESTful server
 */
public class App {
	public static void main(String[] args) {

		Product nouveauProduit;
		List<Product> produits;
		
		// Get access to the service object
		ClientConfig config = new DefaultClientConfig();		
		
		Client client = Client.create(config);
		WebResource service = client.resource(getBaseURI());
		
		// Get all products
		
		produits = service.path("produits")
				.accept(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Product>>(){});            

		afficher(produits);
		
		// Create a new product for testing the server
		nouveauProduit = new Product("Wrap aux feuilles d'acacia", 1.4f);

		// Add a new product using the POST HTTP method.
		//  managed by the Jersey framework
		service.path("produits").type(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.post(new GenericType<Product>() {
				}, nouveauProduit);

		Product premierProduit;

		premierProduit = service.path("produits/first").accept(MediaType.APPLICATION_JSON)
				                 .get(new GenericType<Product>() {
				                 });

		service.path("produits/" + premierProduit.getUID())
				.delete();
		produits = service.path("produits")
				                   .accept(MediaType.APPLICATION_JSON)
				                   .get(new GenericType<List<Product>>() {
				                   });
		afficher(produits);
	}

	private static void afficher(List<Product> produits) {
		for(Product p : produits){
			System.err.println(p.getNom());
		}
	}

	private static URI getBaseURI() {
		URI uri =
		 UriBuilder.fromUri("http://localhost:8080/testServer1/webresources").build();
		return uri;
	}
}
