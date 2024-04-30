import java.util.List;

import ma.projet.beans.Client;
import ma.projet.service.ClientService;

public class TestPresentation {
    public static void main(String[] args) {
        ClientService clientService = new ClientService();

        // Création de 5 clients
        clientService.create(new Client("SAFI", "ali"));
        clientService.create(new Client("ALAOUI", "widane"));
        clientService.create(new Client("RAMI", "amine"));
        clientService.create(new Client("ALAMI", "kamal"));
        clientService.create(new Client("SELAMI", "mohamed"));

        // Affichage du client dont id = 3
        Client clientById = clientService.findById(3);
        System.out.println("Client avec ID = 3 : " + clientById);

        // Suppression du client dont id = 3
        Client clientToDelete = clientService.findById(3);
        if (clientToDelete != null) {
            clientService.delete(clientToDelete);
            System.out.println("Client avec ID = 3 supprimé.");
        } else {
            System.out.println("Client avec ID = 3 non trouvé.");
        }

        // Modification du client dont id = 2
        Client clientToUpdate = clientService.findById(2);
        if (clientToUpdate != null) {
            clientToUpdate.setName("UpdatedName");
            clientService.update(clientToUpdate);
            System.out.println("Client avec ID = 2 mis à jour.");
        } else {
            System.out.println("Client avec ID = 2 non trouvé.");
        }

        // Affichage de la liste des clients
        List<Client> allClients = clientService.findAll();
        System.out.println("\nListe des clients :");
        for (Client client : allClients) {
            System.out.println(client);
        }
    }
}
