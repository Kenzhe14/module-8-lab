import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface IMediator {
    void sendMessage(String message, Colleague sender);
    void sendPrivateMessage(String message, Colleague sender, Colleague receiver);
    void registerColleague(Colleague colleague);
    void unregisterColleague(Colleague colleague);
    void logMessage(String message, Colleague sender);
}

abstract class Colleague {
    protected IMediator mediator;
    protected String name;

    public Colleague(IMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public abstract void receiveMessage(String message);

    public String getName() {
        return name;
    }
}

class ChatMediator implements IMediator {
    private Map<String, List<Colleague>> chatGroups;
    private List<String> messageLog;

    public ChatMediator() {
        chatGroups = new HashMap<>();
        messageLog = new ArrayList<>();
    }

    public void createChatGroup(String groupName) {
        chatGroups.put(groupName, new ArrayList<>());
    }

    public void joinChatGroup(String groupName, Colleague colleague) {
        if (chatGroups.containsKey(groupName)) {
            chatGroups.get(groupName).add(colleague);
        } else {
            throw new RuntimeException("Чат-группа не найдена: " + groupName);
        }
    }

    @Override
    public void registerColleague(Colleague colleague) {
        System.out.println(colleague.getName() + " зарегистрирован в чате.");
    }

    @Override
    public void unregisterColleague(Colleague colleague) {
        // Удаляем участника из всех групп
        for (List<Colleague> group : chatGroups.values()) {
            group.remove(colleague);
        }
        System.out.println(colleague.getName() + " покинул чат.");
    }

    @Override
    public void sendMessage(String message, Colleague sender) {
        for (List<Colleague> group : chatGroups.values()) {
            for (Colleague colleague : group) {
                if (colleague != sender) {
                    colleague.receiveMessage(message);
                }
            }
        }
        logMessage(message, sender);
    }

    @Override
    public void sendPrivateMessage(String message, Colleague sender, Colleague receiver) {
        receiver.receiveMessage("Приватное сообщение от " + sender.getName() + ": " + message);
        logMessage("Приватное сообщение от " + sender.getName() + " к " + receiver.getName() + ": " + message, sender);
    }

    @Override
    public void logMessage(String message, Colleague sender) {
        messageLog.add(sender.getName() + ": " + message);
    }

    public List<String> getMessageLog() {
        return messageLog;
    }
}

class User extends Colleague {

    public User(IMediator mediator, String name) {
        super(mediator, name);
    }

    public void send(String message) {
        System.out.println(name + " отправляет сообщение: " + message);
        mediator.sendMessage(message, this);
    }

    public void sendPrivate(String message, Colleague receiver) {
        System.out.println(name + " отправляет приватное сообщение " + receiver.getName() + ": " + message);
        mediator.sendPrivateMessage(message, this, receiver);
    }

    @Override
    public void receiveMessage(String message) {
        System.out.println(name + " получил сообщение: " + message);
    }
}

public class lab73 {
    public static void main(String[] args) {
        // Создаем посредника
        ChatMediator chatMediator = new ChatMediator();

        // Создаем участников
        User manat = new User(chatMediator, "Manat");
        User erko = new User(chatMediator, "Erko");
        User gani = new User(chatMediator, "Gani");

        // Создаем группу чата и регистрируем участников
        chatMediator.createChatGroup("General");
        chatMediator.joinChatGroup("General", manat);
        chatMediator.joinChatGroup("General", erko);
        chatMediator.joinChatGroup("General", gani);

        // Участники обмениваются сообщениями
        manat.send("Hiiii ");

        // Приватное сообщение
        erko.sendPrivate("Wsp Gani!", gani);

        // Вывод лога сообщений
        System.out.println("Istoriya Log");
        for (String log : chatMediator.getMessageLog()) {
            System.out.println(log);
        }

        // Отключение участника
        chatMediator.unregisterColleague(gani);
        manat.send("Gani joq.");
    }
}
