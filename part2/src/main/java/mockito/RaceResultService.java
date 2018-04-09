package mockito;

import java.util.*;

class RaceResultService {
    private Map<Category, Set<Client>> clients;
    private RaceLogger raceLogger;

    RaceResultService(RaceLogger raceLogger) {
        this.raceLogger = raceLogger;
        clients = new EnumMap<>(Category.class);
    }

    void addSubscribers(Client client, Category category) {
        Set<Client> subscribers = clients.get(category);

        if (subscribers == null) {
            subscribers = new HashSet<>();
        }

        subscribers.add(client);
        clients.put(category, subscribers);
    }

    void send(Message message, Category category) {
        if (clients.get(category) != null) {
            for (Client client : clients.get(category)) {
                client.receive(message);
                raceLogger.log(client, message);
            }
        }
    }

    void removeSubscriber(Client firstClient, Category category) {
        if (clients.get(category) == null || !clients.get(category).contains(firstClient)) {
            throw new IllegalArgumentException("Not subscribed, unable to unsubscribe.");
        } else clients.get(category).remove(firstClient);
    }
}
