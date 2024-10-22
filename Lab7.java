interface ICommand {
    void execute();
    void undo();
}

class LightOnCommand implements ICommand {

    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}

class LightOffCommand implements ICommand {

    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}

class Light {

    public void on() {
        System.out.println("Свет включен.");
    }

    public void off() {
        System.out.println("Свет выключен.");
    }
}

class Television {

    public void on() {
        System.out.println("Телевизор включен.");
    }

    public void off() {
        System.out.println("Телевизор выключен.");
    }
}

class Conder {

    public void on() {
        System.out.println("Кондер включен.");
    }

    public void off() {
        System.out.println("Кондер выключен.");
    }

    public void econom() {
        System.out.println("Кондер в экономичном режиме.");
    }
}

class TelevisionOnCommand implements ICommand {

    private Television tv;

    public TelevisionOnCommand(Television tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.on();
    }

    @Override
    public void undo() {
        tv.off();
    }
}

class TelevisionOffCommand implements ICommand {

    private Television tv;

    public TelevisionOffCommand(Television tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.off();
    }

    @Override
    public void undo() {
        tv.on();
    }
}

class ConderOnCommand implements ICommand {

    private Conder conder;

    public ConderOnCommand(Conder conder) {
        this.conder = conder;
    }

    @Override
    public void execute() {
        conder.on();
    }

    @Override
    public void undo() {
        conder.off();
    }
}

class ConderOffCommand implements ICommand {

    private Conder conder;

    public ConderOffCommand(Conder conder) {
        this.conder = conder;
    }

    @Override
    public void execute() {
        conder.off();
    }

    @Override
    public void undo() {
        conder.on();
    }
}

class ConderEconomCommand implements ICommand {

    private Conder conder;

    public ConderEconomCommand(Conder conder) {
        this.conder = conder;
    }

    @Override
    public void execute() {
        conder.econom();
    }

    @Override
    public void undo() {
        conder.on();
    }
}

class RemoteControl {

    private ICommand onCommand;
    private ICommand offCommand;
    private ICommand economCommand;

    public void setCommands(ICommand onCommand, ICommand offCommand) {
        this.onCommand = onCommand;
        this.offCommand = offCommand;
    }

    public void setEconomCommand(ICommand economCommand) {
        this.economCommand = economCommand;
    }

    public void pressOnButton() {
        if (onCommand != null) onCommand.execute();
    }

    public void pressOffButton() {
        if (offCommand != null) offCommand.execute();
    }

    public void pressUndoButton() {
        if (onCommand != null) onCommand.undo();
    }

    public void pressEconomButton() {
        if (economCommand != null) economCommand.execute();
    }
}

public class Lab7 {

    public static void main(String[] args) {

        // Создаем устройства
        Light livingRoomLight = new Light();
        Television tv = new Television();
        Conder conder = new Conder();

        // Создаем команды для управления светом
        ICommand lightOn = new LightOnCommand(livingRoomLight);
        ICommand lightOff = new LightOffCommand(livingRoomLight);

        // Создаем команды для управления телевизором
        ICommand tvOn = new TelevisionOnCommand(tv);
        ICommand tvOff = new TelevisionOffCommand(tv);

        // Создаем команды для кондиционера
        ICommand conderOn = new ConderOnCommand(conder);
        ICommand conderOff = new ConderOffCommand(conder);
        ICommand conderEconom = new ConderEconomCommand(conder);

        // Создаем пульт и привязываем команды к кнопкам
        RemoteControl remote = new RemoteControl();

        // Управляем светом
        remote.setCommands(lightOn, lightOff);
        System.out.println("Управление светом:");
        remote.pressOnButton();
        remote.pressOffButton();
        remote.pressUndoButton();

        // Управляем телевизором
        remote.setCommands(tvOn, tvOff);
        System.out.println("\nУправление телевизором:");
        remote.pressOnButton();
        remote.pressOffButton();
        remote.pressUndoButton();

        // Управляем кондиционером
        remote.setCommands(conderOn, conderOff);
        System.out.println("\nУправление кондиционером:");
        remote.pressOnButton();
        remote.pressOffButton();
        remote.pressUndoButton();
        remote.setEconomCommand(conderEconom);
        remote.pressEconomButton();
    }
}
